package com.alten.bookingservice.service;

import com.alten.bookingservice.dto.BookingDTO;
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

    public CreateBookingResponseDTO createEventBooking(CreateBookingRequestDTO createBookingRequestDTO) {

        var bookingDTO = new BookingDTO(createBookingRequestDTO);


        requestedBookingEventProducer.produceEvent(bookingDTO, String.valueOf(bookingDTO.getRoomNumber()));

        return new CreateBookingResponseDTO(bookingDTO);
    }
}
