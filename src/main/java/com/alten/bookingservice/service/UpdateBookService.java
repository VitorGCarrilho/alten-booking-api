package com.alten.bookingservice.service;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.domain.Notification;
import com.alten.bookingservice.dto.request.BookingRequestDTO;
import com.alten.bookingservice.entity.BookingEntity;
import com.alten.bookingservice.entity.factory.BookingDayEntityFactory;
import com.alten.bookingservice.exception.AlreadyBookedException;
import com.alten.bookingservice.producer.NotificationEventProducer;
import com.alten.bookingservice.producer.UpdateBookingEventProducer;
import com.alten.bookingservice.repository.BookingDayRepository;
import com.alten.bookingservice.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdateBookService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateBookService.class);

    @Autowired
    private UpdateBookingEventProducer updateBookingEventProducer;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingDayRepository bookingDayRepository;

    @Autowired
    private BookingDayEntityFactory bookingDayEntityFactory;

    @Autowired
    private NotificationEventProducer notificationEventProducer;

    public void updateBookEvent(String id, BookingRequestDTO bookingRequestDTO) {
        logger.info("method=updateBookEvent id={}", id);
        var booking = new Booking(id, bookingRequestDTO);
        updateBookingEventProducer.produceEvent(booking, String.valueOf(booking.getRoomNumber()));
    }

    public void update(Booking booking) {
        logger.info("method=update id={}", booking.getId());
        var optBooking = bookingRepository.findById(booking.getId());

        if (optBooking.isPresent()) {

            var newBooking = new BookingEntity(booking);
            newBooking.updated();

            try {
                bookingRepository.save(newBooking);
                var newBookingDays = bookingDayEntityFactory.getBookingDays(newBooking);
                bookingDayRepository.updateBooking(newBooking.getId(), newBookingDays);
                var notification = new Notification(booking, Notification.NotificationType.UPDATE_BOOKED);
                notificationEventProducer.produceEvent(notification, String.valueOf( booking.getRoomNumber()));
            } catch (AlreadyBookedException e) {
                logger.info("method=update status=AlreadyBookedException");
                newBooking.denyUpdate();
                bookingRepository.save(newBooking);
                var notification = new Notification(booking, Notification.NotificationType.UPDATE_ROOM_ALREADY_BOOKED);
                notificationEventProducer.produceEvent(notification, String.valueOf( booking.getRoomNumber()));
            }
        }
    }
}
