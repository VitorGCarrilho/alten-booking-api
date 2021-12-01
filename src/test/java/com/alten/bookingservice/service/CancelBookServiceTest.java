package com.alten.bookingservice.service;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.domain.Notification;
import com.alten.bookingservice.entity.BookingEntity;
import com.alten.bookingservice.producer.CancelBookingEventProducer;
import com.alten.bookingservice.producer.NotificationEventProducer;
import com.alten.bookingservice.repository.BookingDayRepository;
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
class CancelBookServiceTest {

    @Mock
    private CancelBookingEventProducer cancelBookingEventProducer;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingDayRepository bookingDayRepository;

    @Mock
    private NotificationEventProducer notificationEventProducer;

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

    @Test
    public void shouldCancelBook() {

        // GIVEN
        var booking  = SampleFactoryUtils.booking();
        Mockito.when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(new BookingEntity(booking)));

        // WHEN
        cancelBookService.cancelBook(booking);

        // THEN
        Mockito.verify(bookingDayRepository).removeBooking(booking.getId());

        Mockito.verify(notificationEventProducer)
                .produceEvent(new Notification(booking, Notification.NotificationType.BOOK_CANCELLED)
                        , String.valueOf( booking.getRoomNumber()));

    }
}