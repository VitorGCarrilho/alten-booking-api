package com.alten.bookingservice.domain;

import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
import com.alten.bookingservice.exception.AdvanceReservationException;
import com.alten.bookingservice.exception.OutOfRangeException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Booking implements Serializable {
    private String id;
    private LocalDate fromDate;
    private LocalDate untilDate;
    private int roomNumber;
    private String email;

    public Booking(CreateBookingRequestDTO createBookingRequestDTO) {

        if ((ChronoUnit.DAYS.between(createBookingRequestDTO.getFromDate(), createBookingRequestDTO.getUntilDate())+ 1) >3) {
            throw new OutOfRangeException("the stay can not be longer than 3 days");
        }

        if (ChronoUnit.DAYS.between(LocalDate.now(), createBookingRequestDTO.getFromDate()) > 30) {
            throw new AdvanceReservationException("can not be reserved more than 30 days in advance");
        }

        this.id = UUID.randomUUID().toString();
        this.fromDate = createBookingRequestDTO.getFromDate();
        this.untilDate = createBookingRequestDTO.getUntilDate();
        this.roomNumber = createBookingRequestDTO.getRoomNumber();
        this.email = createBookingRequestDTO.getEmail();
    }

    /**
     *  Needed by jackson to consume from topic
     */
    public Booking() {}

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
