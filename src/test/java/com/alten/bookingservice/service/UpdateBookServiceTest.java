package com.alten.bookingservice.service;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.domain.Notification;
import com.alten.bookingservice.entity.BookingEntity;
import com.alten.bookingservice.entity.factory.BookingDayEntityFactory;
import com.alten.bookingservice.producer.NotificationEventProducer;
import com.alten.bookingservice.producer.UpdateBookingEventProducer;
import com.alten.bookingservice.repository.BookingDayRepository;
import com.alten.bookingservice.repository.BookingRepository;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UpdateBookServiceTest {

    @Mock
    private UpdateBookingEventProducer updateBookingEventProducer;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingDayRepository bookingDayRepository;

    @Mock
    private BookingDayEntityFactory bookingDayEntityFactory;

    @Mock
    private NotificationEventProducer notificationEventProducer;

    @InjectMocks
    private UpdateBookService updateBookService;

    @Test
    public void shouldCreateUpdateEvent() {
        var id = "123";
        var bookingRequest = SampleFactoryUtils.validCreateBookingRequestDTO();

        updateBookService.updateBookEvent(id, bookingRequest);

        Mockito.verify(updateBookingEventProducer).produceEvent(new Booking(id, bookingRequest)
                , String.valueOf(bookingRequest.getRoomNumber()));
    }

    @Test
    public void shouldUpdate() {
        var booking = SampleFactoryUtils.booking();
        var bookingEntity = new BookingEntity(booking);
        var bookingDays = Arrays.asList(SampleFactoryUtils.bookingDayEntity());
        bookingEntity.updated();

        Mockito.when(bookingDayEntityFactory.getBookingDays(any())).thenReturn(bookingDays);

        Mockito.when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(bookingEntity));

        updateBookService.update(booking);

        Mockito.verify(bookingRepository).save(any());
        Mockito.verify(bookingDayRepository).updateBooking(bookingEntity.getId(), bookingDays);
        Mockito.verify(notificationEventProducer).produceEvent(new Notification(booking, Notification.NotificationType.UPDATE_BOOKED)
                , String.valueOf( booking.getRoomNumber()));

    }
}