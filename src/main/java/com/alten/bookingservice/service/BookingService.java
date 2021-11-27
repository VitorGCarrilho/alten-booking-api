package com.alten.bookingservice.service;

import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
import com.alten.bookingservice.dto.response.CreateBookingResponseDTO;
import com.alten.bookingservice.producer.RequestedBookingEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private RequestedBookingEventProducer requestedBookingEventProducer;

    public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO createBookingRequestDTO) {

        requestedBookingEventProducer.produceEvent(createBookingRequestDTO, String.valueOf(createBookingRequestDTO.getRoomNumber()));

        return null;
    }
}
