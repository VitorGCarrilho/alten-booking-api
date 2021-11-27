package com.alten.bookingservice.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class BookingDayId implements Serializable {
    private LocalDate bookingDate;
    private int roomNumber;

    public BookingDayId(LocalDate bookingDate, int roomNumber) {
        this.bookingDate = bookingDate;
        this.roomNumber = roomNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
