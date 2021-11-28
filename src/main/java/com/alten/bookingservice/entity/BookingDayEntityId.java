package com.alten.bookingservice.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class BookingDayEntityId implements Serializable {
    private LocalDate bookingDate;
    private int roomNumber;

    public BookingDayEntityId(){}

    public BookingDayEntityId(LocalDate bookingDate, int roomNumber, String bookingId) {
        this.bookingDate = bookingDate;
        this.roomNumber = roomNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "BookingDayId{" +
                "bookingDate=" + bookingDate +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
