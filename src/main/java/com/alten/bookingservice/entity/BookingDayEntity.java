package com.alten.bookingservice.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "booking_day")
public class BookingDayEntity implements Serializable {
    @EmbeddedId
    private BookingDayEntityId bookId;

    @ManyToOne
    @JoinColumn(name="id_booking")
    private BookingEntity booking;

    public BookingDayEntity() {

    }

    public BookingDayEntity(BookingEntity booking, LocalDate bookedDate) {
        this.bookId = new BookingDayEntityId(bookedDate, booking.getRoomNumber(), booking.getId());
        this.booking = booking;
    }

    public BookingDayEntityId getBookId() {
        return bookId;
    }

    public BookingEntity getBooking() {
        return booking;
    }
}
