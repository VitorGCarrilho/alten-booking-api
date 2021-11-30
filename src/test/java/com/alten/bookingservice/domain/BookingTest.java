package com.alten.bookingservice.domain;

import com.alten.bookingservice.dto.request.BookingRequestDTO;
import com.alten.bookingservice.exception.AdvanceReservationException;
import com.alten.bookingservice.exception.OutOfRangeException;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingTest {

    @Test
    public void theStayCanNotBeLongerThan3DaysTest() {
        // GIVEN
        BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
        bookingRequestDTO.setFromDate(LocalDate.of(2021, 01, 01));
        bookingRequestDTO.setUntilDate(LocalDate.of(2021, 01, 04));

        // WHEN
        assertThrows(OutOfRangeException.class, () -> {
            Booking booking = new Booking(bookingRequestDTO);
        });
    }

    @Test
    public void canNotBeReservedMoreThan30DaysInAdvanceTest() {
        // GIVEN
        BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
        bookingRequestDTO.setFromDate(LocalDate.now().plusDays(31));
        bookingRequestDTO.setUntilDate(LocalDate.now().plusDays(32));
        // WHEN
        assertThrows(AdvanceReservationException.class, () -> {
            Booking booking = new Booking(bookingRequestDTO);
        });
    }

    @Test
    public void shouldCreateDomainObject() {
        // GIVEN
        BookingRequestDTO bookingRequestDTO = SampleFactoryUtils.validCreateBookingRequestDTO();

        // WHEN
        Booking booking = new Booking(bookingRequestDTO);

        // THEN
        assertEquals(bookingRequestDTO.getFromDate(), booking.getFromDate());
        assertEquals(bookingRequestDTO.getUntilDate(), booking.getUntilDate());
        assertEquals(bookingRequestDTO.getEmail(), booking.getEmail());
        assertEquals(bookingRequestDTO.getRoomNumber(), booking.getRoomNumber());
        assertNotNull(booking.getId());
    }
}