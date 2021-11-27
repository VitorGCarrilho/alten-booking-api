package com.alten.bookingservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "booking_day")
public class BookingDayEntity {
    @EmbeddedId
    private BookingDayId bookId;

    @ManyToOne
    @JoinColumn(name="id_booking")
    private BookingEntity booking;

    public BookingDayEntity(BookingDayId bookId) {
        this.bookId = bookId;
    }

    public BookingDayId getBookId() {
        return bookId;
    }
}
