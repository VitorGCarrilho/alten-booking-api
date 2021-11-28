package com.alten.bookingservice.entity;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.entity.enumeration.BookingStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @Column(name = "id_booking")
    private String id;

    private LocalDateTime bookingDate;

    private LocalDate fromDate;

    private LocalDate untilDate;

    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private int roomNumber;

    private String email;

    public BookingEntity(Booking booking) {
        this.id = booking.getId();
        this.bookingDate = LocalDateTime.now();
        this.fromDate = booking.getFromDate();
        this.untilDate = booking.getUntilDate();
        this.bookingStatus = BookingStatus.BOOKING_REQUESTED;
        this.roomNumber = booking.getRoomNumber();
        this.email = booking.getEmail();
    }

    public void accept() {
        bookingStatus = BookingStatus.BOOKED;
    }

    public void deny() {
        bookingStatus = BookingStatus.DENIED;
    }

    public BookingEntity() {}

    public String getId() {
        return id;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getUntilDate() {
        return untilDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return roomNumber == that.roomNumber && id.equals(that.id) && fromDate.equals(that.fromDate) && untilDate.equals(that.untilDate) && bookingStatus == that.bookingStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromDate, untilDate, bookingStatus, roomNumber);
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id='" + id + '\'' +
                ", bookingDate=" + bookingDate +
                ", fromDate=" + fromDate +
                ", untilDate=" + untilDate +
                ", updatedAt=" + updatedAt +
                ", bookingStatus=" + bookingStatus +
                ", roomNumber=" + roomNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
