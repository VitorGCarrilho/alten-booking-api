package com.alten.bookingservice.dto.response;

import com.alten.bookingservice.domain.Booking;

public class CreateBookingResponseDTO {
    private String id;

    public CreateBookingResponseDTO(Booking booking) {
        this.id = booking.getId();
    }

    public String getId() {
        return id;
    }
}
