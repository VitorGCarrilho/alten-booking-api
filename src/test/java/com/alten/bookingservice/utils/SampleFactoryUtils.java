package com.alten.bookingservice.utils;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.domain.Notification;
import com.alten.bookingservice.dto.request.BookingRequestDTO;
import com.alten.bookingservice.dto.response.AvailabilityResponseDTO;
import com.alten.bookingservice.dto.response.BookingResponseDTO;
import com.alten.bookingservice.entity.BookingDayEntity;
import com.alten.bookingservice.entity.BookingEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

    public static Notification notification() {
        return new Notification(booking(), Notification.NotificationType.BOOKED);
    }

    public static BookingResponseDTO bookingResponseDTO() {
        return new BookingResponseDTO(bookingEntity());
    }

    public static List<AvailabilityResponseDTO> availabilityResponseDTOS() {
        return Arrays.asList(new AvailabilityResponseDTO(LocalDate.of(2021, 01,1), false));
    }
}
