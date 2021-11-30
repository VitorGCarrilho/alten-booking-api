package com.alten.bookingservice.domain;

import com.alten.bookingservice.dto.request.BookingRequestDTO;
import com.alten.bookingservice.entity.BookingEntity;
import com.alten.bookingservice.exception.AdvanceReservationException;
import com.alten.bookingservice.exception.OutOfRangeException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

public class Booking implements Serializable {
    private String id;
    private LocalDate fromDate;
    private LocalDate untilDate;
    private int roomNumber;
    private String email;

    public Booking(BookingRequestDTO bookingRequestDTO) {

        if ((ChronoUnit.DAYS.between(bookingRequestDTO.getFromDate(), bookingRequestDTO.getUntilDate())+ 1) >3) {
            throw new OutOfRangeException("the stay can not be longer than 3 days");
        }

        if (ChronoUnit.DAYS.between(LocalDate.now(), bookingRequestDTO.getFromDate()) > 30) {
            throw new AdvanceReservationException("can not be reserved more than 30 days in advance");
        }

        this.id = UUID.randomUUID().toString();
        this.fromDate = bookingRequestDTO.getFromDate();
        this.untilDate = bookingRequestDTO.getUntilDate();
        this.roomNumber = bookingRequestDTO.getRoomNumber();
        this.email = bookingRequestDTO.getEmail();
    }

    /**
     *  Needed by jackson to consume from topic
     */
    public Booking() {}

    public Booking(String id) {
        this.id = id;
    }

    public Booking(String id, BookingRequestDTO bookingRequestDTO) {
        this(bookingRequestDTO);
        this.id = id;
    }

    public Booking(String id, BookingEntity booking) {
        this.id = id;
        this.fromDate = booking.getFromDate();
        this.untilDate = booking.getUntilDate();
        this.roomNumber = booking.getRoomNumber();
        this.email = booking.getEmail();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return roomNumber == booking.roomNumber && id.equals(booking.id) && Objects.equals(fromDate, booking.fromDate) && Objects.equals(untilDate, booking.untilDate) && Objects.equals(email, booking.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromDate, untilDate, roomNumber, email);
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id='" + id + '\'' +
                ", fromDate=" + fromDate +
                ", untilDate=" + untilDate +
                ", roomNumber=" + roomNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
