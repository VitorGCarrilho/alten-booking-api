package com.alten.bookingservice.dto.response;

import com.alten.bookingservice.entity.BookingEntity;
import com.alten.bookingservice.entity.enumeration.BookingStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingResponseDTO that = (BookingResponseDTO) o;
        return roomNumber == that.roomNumber && Objects.equals(id, that.id) && Objects.equals(fromDate, that.fromDate) && Objects.equals(untilDate, that.untilDate) && Objects.equals(email, that.email) && bookingStatus == that.bookingStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromDate, untilDate, roomNumber, email, bookingStatus);
    }
}
