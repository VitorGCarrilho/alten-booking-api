package com.alten.bookingservice.utils;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
import com.alten.bookingservice.entity.BookingDayEntity;
import com.alten.bookingservice.entity.BookingEntity;

import java.time.LocalDate;

public class SampleFactoryUtils {

    public static CreateBookingRequestDTO validCreateBookingRequestDTO() {
        CreateBookingRequestDTO createBookingRequestDTO = new CreateBookingRequestDTO();
        createBookingRequestDTO.setFromDate(LocalDate.now().plusDays(15));
        createBookingRequestDTO.setUntilDate(LocalDate.now().plusDays(17));
        createBookingRequestDTO.setEmail("email@gmail.com");
        createBookingRequestDTO.setRoomNumber(8);
        return createBookingRequestDTO;
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
