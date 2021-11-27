package com.alten.bookingservice.dto.response;

import com.alten.bookingservice.dto.BookingDTO;

public class CreateBookingResponseDTO {
    private String id;

    public CreateBookingResponseDTO(BookingDTO bookingDTO) {
        this.id = bookingDTO.getId();
    }

    public String getId() {
        return id;
    }
}
