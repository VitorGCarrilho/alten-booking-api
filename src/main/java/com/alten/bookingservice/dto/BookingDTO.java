package com.alten.bookingservice.dto;

import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class BookingDTO implements Serializable {
    private String id;
    private LocalDate fromDate;
    private LocalDate untilDate;
    private int roomNumber;
    private String email;

    public BookingDTO(CreateBookingRequestDTO createBookingRequestDTO) {
        this.id = UUID.randomUUID().toString();
        this.fromDate = createBookingRequestDTO.getFromDate();
        this.untilDate = createBookingRequestDTO.getUntilDate();
        this.roomNumber = createBookingRequestDTO.getRoomNumber();
        this.email = createBookingRequestDTO.getEmail();
    }

    public BookingDTO() {}

    public String getId() {
        return id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getUntilDate() {
        return untilDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id='" + id + '\'' +
                ", fromDate=" + fromDate +
                ", untilDate=" + untilDate +
                ", roomNumber=" + roomNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
