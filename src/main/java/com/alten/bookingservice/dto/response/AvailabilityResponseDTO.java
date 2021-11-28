package com.alten.bookingservice.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

public class AvailabilityResponseDTO implements Serializable {

    private LocalDate localDate;
    private boolean available;

    public AvailabilityResponseDTO(LocalDate localDate, boolean booked) {
        this.localDate = localDate;
        this.available = !booked;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public boolean isAvailable() {
        return available;
    }
}
