package com.alten.bookingservice.domain;

import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
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
        CreateBookingRequestDTO createBookingRequestDTO = new CreateBookingRequestDTO();
        createBookingRequestDTO.setFromDate(LocalDate.of(2021, 01, 01));
        createBookingRequestDTO.setUntilDate(LocalDate.of(2021, 01, 05));

        // WHEN
        assertThrows(OutOfRangeException.class, () -> {
            Booking booking = new Booking(createBookingRequestDTO);
        });
    }

    @Test
    public void canNotBeReservedMoreThan30DaysInAdvanceTest() {
        // GIVEN
        CreateBookingRequestDTO createBookingRequestDTO = new CreateBookingRequestDTO();
        createBookingRequestDTO.setFromDate(LocalDate.now().plusDays(31));
        createBookingRequestDTO.setUntilDate(LocalDate.now().plusDays(32));
        // WHEN
        assertThrows(AdvanceReservationException.class, () -> {
            Booking booking = new Booking(createBookingRequestDTO);
        });
    }

    @Test
    public void shouldCreateDomainObject() {
        // GIVEN
        CreateBookingRequestDTO createBookingRequestDTO = SampleFactoryUtils.validCreateBookingRequestDTO();

        // WHEN
        Booking booking = new Booking(createBookingRequestDTO);

        // THEN
        assertEquals(createBookingRequestDTO.getFromDate(), booking.getFromDate());
        assertEquals(createBookingRequestDTO.getUntilDate(), booking.getUntilDate());
        assertEquals(createBookingRequestDTO.getEmail(), booking.getEmail());
        assertEquals(createBookingRequestDTO.getRoomNumber(), booking.getRoomNumber());
        assertNotNull(booking.getId());
    }
}