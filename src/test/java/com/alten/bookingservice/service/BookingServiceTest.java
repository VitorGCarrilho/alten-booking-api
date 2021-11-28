package com.alten.bookingservice.service;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.entity.BookingEntity;
import com.alten.bookingservice.entity.factory.BookingDayEntityFactory;
import com.alten.bookingservice.producer.RequestedBookingEventProducer;
import com.alten.bookingservice.repository.BookingDayRepository;
import com.alten.bookingservice.repository.BookingRepository;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingDayRepository bookingDayRepository;

    @Mock
    private RequestedBookingEventProducer requestedBookingEventProducer;

    @Mock
    private BookingDayEntityFactory bookingDayEntityFactory;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void createBookingEvent() {
        // GIVEN
        var bookingRequest = SampleFactoryUtils.validCreateBookingRequestDTO();

        // WHEN
        var response  = bookingService.createBookingEvent(bookingRequest);

        // THEN
        assertNotNull(response.getId());
        Mockito.verify(requestedBookingEventProducer).produceEvent(any(), anyString());
    }

    @Test
    void bookStay() {
        // GIVEN
        var booking = SampleFactoryUtils.booking();
        var bookingEntity = new BookingEntity(booking);
        var bookingDays = Arrays.asList(SampleFactoryUtils.bookingDayEntity());

        Mockito.when(bookingDayEntityFactory.getBookingDays(bookingEntity)).thenReturn(bookingDays);

        // WHEN
        bookingService.bookStay(booking);

        // THEN
        Mockito.verify(bookingRepository, Mockito.times(2)).save(any());
        Mockito.verify(bookingDayRepository).saveAll(bookingDays);
    }
}