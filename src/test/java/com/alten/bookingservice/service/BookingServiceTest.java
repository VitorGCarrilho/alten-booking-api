package com.alten.bookingservice.service;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.producer.RequestedBookingEventProducer;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private RequestedBookingEventProducer requestedBookingEventProducer;

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
}