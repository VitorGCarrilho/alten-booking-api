package com.alten.bookingservice.entity.factory;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
import com.alten.bookingservice.entity.BookingEntity;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingDayEntityFactoryTest {

    @InjectMocks
    private BookingDayEntityFactory bookingDayEntityFactory;

    @Test
    void getBookingDays() {

        // GIVEN
        CreateBookingRequestDTO createBookingRequestDTO = new CreateBookingRequestDTO();
        createBookingRequestDTO.setFromDate(LocalDate.now().plusDays(15));
        createBookingRequestDTO.setUntilDate(LocalDate.now().plusDays(17));
        BookingEntity booking = new BookingEntity(new Booking(createBookingRequestDTO));

        // WHEN
        var bookingDayEntities = bookingDayEntityFactory.getBookingDays(booking);

        // THEN
        assertEquals(3, bookingDayEntities.size());
        assertEquals(LocalDate.now().plusDays(15) ,bookingDayEntities.get(0).getBookId().getBookingDate());
        assertEquals(LocalDate.now().plusDays(16) ,bookingDayEntities.get(1).getBookId().getBookingDate());
        assertEquals(LocalDate.now().plusDays(17) ,bookingDayEntities.get(2).getBookId().getBookingDate());
    }
}