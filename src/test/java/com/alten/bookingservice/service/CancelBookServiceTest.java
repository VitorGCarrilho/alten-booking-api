package com.alten.bookingservice.service;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.producer.CancelBookingEventProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CancelBookServiceTest {

    @Mock
    private CancelBookingEventProducer cancelBookingEventProducer;

    @InjectMocks
    private CancelBookService cancelBookService;

    @Test
    public void shouldCreateCancelEvent() {

        // GIVEN
        var id = "123";

        // WHEN
        cancelBookService.cancelBookEvent(id);

        // THEN
        Mockito.verify(cancelBookingEventProducer).produceEvent(new Booking(id), id);

    }
}