package com.alten.bookingservice.service;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
import com.alten.bookingservice.dto.response.CreateBookingResponseDTO;
import com.alten.bookingservice.producer.RequestedBookingEventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private RequestedBookingEventProducer requestedBookingEventProducer;

    public CreateBookingResponseDTO createBookingEvent(CreateBookingRequestDTO createBookingRequestDTO) {
        logger.info("method=createBookingEvent");
        var booking = new Booking(createBookingRequestDTO);
        logger.info("method=createBookingEvent bookingId={}", booking.getId());
        requestedBookingEventProducer.produceEvent(booking, String.valueOf(booking.getRoomNumber()));
        return new CreateBookingResponseDTO(booking);
    }
}
