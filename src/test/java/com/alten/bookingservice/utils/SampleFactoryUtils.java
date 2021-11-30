package com.alten.bookingservice.utils;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.dto.request.BookingRequestDTO;
import com.alten.bookingservice.entity.BookingDayEntity;
import com.alten.bookingservice.entity.BookingEntity;

import java.time.LocalDate;

public class SampleFactoryUtils {

    public static BookingRequestDTO validCreateBookingRequestDTO() {
        BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
        bookingRequestDTO.setFromDate(LocalDate.now().plusDays(15));
        bookingRequestDTO.setUntilDate(LocalDate.now().plusDays(17));
        bookingRequestDTO.setEmail("email@gmail.com");
        bookingRequestDTO.setRoomNumber(8);
        return bookingRequestDTO;
    }

    public static Booking booking() {
        return new Booking(validCreateBookingRequestDTO());
    }

    public static BookingEntity bookingEntity() {
        return new BookingEntity(booking());
    }

    public static BookingDayEntity bookingDayEntity() {
        return new BookingDayEntity(bookingEntity(), LocalDate.now());
    }
}
