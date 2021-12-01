package com.alten.bookingservice.dto.response;

import com.alten.bookingservice.entity.BookingEntity;
import com.alten.bookingservice.entity.enumeration.BookingStatus;

import java.io.Serializable;
import java.time.LocalDate;

public class BookingResponseDTO implements Serializable {
    private String id;
    private LocalDate fromDate;
    private LocalDate untilDate;
    private int roomNumber;
    private String email;
    private BookingStatus bookingStatus;

    public BookingResponseDTO(){}

    public BookingResponseDTO(BookingEntity book) {
        this.id = book.getId();
        this.fromDate = book.getFromDate();
        this.untilDate = book.getUntilDate();
        this.roomNumber = book.getRoomNumber();
        this.email = book.getEmail();
        this.bookingStatus = book.getBookingStatus();
    }

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

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
}
