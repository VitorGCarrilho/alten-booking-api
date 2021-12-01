package com.alten.bookingservice.consumer;

import com.alten.bookingservice.service.BookingService;
import com.alten.bookingservice.service.CancelBookService;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CancelBookingEventListenerTest {

    @Mock
    private CancelBookService service;

    @InjectMocks
    private CancelBookingEventListener listener;

    @Test
    public void shouldListen() {
        // GIVEN
        var booking = SampleFactoryUtils.booking();

        // WHEN
        listener.listen(booking);

        // THEN
        Mockito.verify(service).cancelBook(booking);
    }
}