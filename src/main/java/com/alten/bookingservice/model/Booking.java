package com.alten.bookingservice.model;

import com.alten.bookingservice.model.enumeration.BookingStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "id_booking")
    private String id;

    private LocalDateTime bookingDate;

    private LocalDateTime updatedAt;

    private BookingStatus bookingStatus;

    @OneToMany(mappedBy = "booking")
    private List<BookingDay> bookingDays;

    public Booking(String id, LocalDateTime bookingDate, int roomNumber, List<BookingDay> bookingDays) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.bookingDays = bookingDays;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public List<BookingDay> getBookingDays() {
        return bookingDays;
    }
}
