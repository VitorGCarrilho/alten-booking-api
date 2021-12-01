package com.alten.bookingservice.service;

import com.alten.bookingservice.dto.response.BookingResponseDTO;
import com.alten.bookingservice.exception.NotFoundException;
import com.alten.bookingservice.exception.ServiceCantGetDataException;
import com.alten.bookingservice.repository.BookingCacheRepository;
import com.alten.bookingservice.repository.BookingRepository;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetBookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingCacheRepository bookingCacheRepository;

    @InjectMocks
    private GetBookingService getBookingService;

    @Test
    public void shoudGetFromDB(){
        // GIVEN
        var bookingEntity = SampleFactoryUtils.bookingEntity();

        Mockito.when(bookingRepository.findById("123")).thenReturn(Optional.of(bookingEntity));

        // WHEN
        var response = getBookingService.getBooking("123");

        // THEN
        assertEquals(new BookingResponseDTO(bookingEntity), response);
    }

    @Test
    public void shoudGetNotFoundFromDB(){
        // GIVEN
        Mockito.when(bookingRepository.findById("123")).thenReturn(Optional.empty());

        // WHEN
        assertThrows(NotFoundException.class, () -> {
            var response = getBookingService.getBooking("123");
        });
    }

    @Test
    public void shoudGetCacheDB(){
        // GIVEN
        var bookingEntity = SampleFactoryUtils.bookingEntity();

        Mockito.when(bookingCacheRepository.getBooking("123")).thenReturn(new BookingResponseDTO(bookingEntity));

        // WHEN
        var response = getBookingService.findCachedBooking("123");

        // THEN
        assertEquals(new BookingResponseDTO(bookingEntity), response);
    }

    @Test
    public void shoudThrowServiceCantGetDataException(){
        // GIVEN
        var bookingEntity = SampleFactoryUtils.bookingEntity();

        Mockito.when(bookingCacheRepository.getBooking("123")).thenReturn(null);

        // WHEN
        assertThrows(ServiceCantGetDataException.class, () -> {
            var response = getBookingService.findCachedBooking("123");
        });
    }

}