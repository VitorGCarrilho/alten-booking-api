package com.alten.bookingservice.consumer;

import com.alten.bookingservice.producer.RequestedBookingEventProducer;
import com.alten.bookingservice.service.BookingService;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RequestedBookingEventListenerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private RequestedBookingEventListener requestedBookingEventListener;

    @Test
    public void shouldBookStayWhenBookingRequestListenTest() {
        // GIVEN
        var booking = SampleFactoryUtils.booking();

        // WHEN
        requestedBookingEventListener.listen(booking);

        // THEN
        Mockito.verify(bookingService).bookStay(booking);
    }

}