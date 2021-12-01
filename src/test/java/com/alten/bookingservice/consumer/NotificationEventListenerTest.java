package com.alten.bookingservice.consumer;

import com.alten.bookingservice.service.CancelBookService;
import com.alten.bookingservice.service.NotificationService;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NotificationEventListenerTest {
    @Mock
    private NotificationService service;

    @InjectMocks
    private NotificationEventListener listener;

    @Test
    public void shouldListen() {
        // GIVEN
        var notification = SampleFactoryUtils.notification();

        // WHEN
        listener.listen(notification);

        // THEN
        Mockito.verify(service).notify(notification);
    }
}