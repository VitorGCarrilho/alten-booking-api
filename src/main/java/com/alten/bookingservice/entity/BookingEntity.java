package com.alten.bookingservice.entity;

import com.alten.bookingservice.entity.enumeration.BookingStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @Column(name = "id_booking")
    private String id;

    private LocalDateTime bookingDate;

    private LocalDateTime updatedAt;

    private BookingStatus bookingStatus;

    @OneToMany(mappedBy = "booking")
    private List<BookingDayEntity> bookingDays;

    public BookingEntity(String id, LocalDateTime bookingDate, int roomNumber, List<BookingDayEntity> bookingDays) {
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

    public List<BookingDayEntity> getBookingDays() {
        return bookingDays;
    }
}
