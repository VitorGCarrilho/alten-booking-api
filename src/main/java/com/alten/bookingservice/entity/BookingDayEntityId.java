package com.alten.bookingservice.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class BookingDayEntityId implements Serializable {
    private LocalDate bookingDate;
    private int roomNumber;

    public BookingDayEntityId(){}

    public BookingDayEntityId(LocalDate bookingDate, int roomNumber) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDayEntityId that = (BookingDayEntityId) o;
        return roomNumber == that.roomNumber && Objects.equals(bookingDate, that.bookingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingDate, roomNumber);
    }

    @Override
    public String toString() {
        return "BookingDayId{" +
                "bookingDate=" + bookingDate +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
