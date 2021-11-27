package com.alten.bookingservice.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking_day")
public class BookingDay {
    @EmbeddedId
    private BookingDayId bookId;

    @ManyToOne
    @JoinColumn(name="id_booking")
    private Booking booking;

    public BookingDay(BookingDayId bookId) {
        this.bookId = bookId;
    }

    public BookingDayId getBookId() {
        return bookId;
    }
}
