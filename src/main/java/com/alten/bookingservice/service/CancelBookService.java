package com.alten.bookingservice.service;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.domain.Notification;
import com.alten.bookingservice.producer.CancelBookingEventProducer;
import com.alten.bookingservice.producer.NotificationEventProducer;
import com.alten.bookingservice.repository.BookingDayRepository;
import com.alten.bookingservice.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelBookService {

    private static final Logger logger = LoggerFactory.getLogger(CancelBookService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingDayRepository bookingDayRepository;

    @Autowired
    private CancelBookingEventProducer cancelBookingEventProducer;

    @Autowired
    private NotificationEventProducer notificationEventProducer;

    public void cancelBookEvent(String id) {
        logger.info("method=cancelBookEvent id={}", id);
        var bookingToCancel = new Booking(id);
        cancelBookingEventProducer.produceEvent(bookingToCancel, id);
    }

    public void cancelBook(Booking booking) {
        logger.info("method=cancelBook id={}", booking.getId());
        var optBooking = bookingRepository.findById(booking.getId());
        if (optBooking.isPresent()) {
            var loadedBooking = optBooking.get();
            loadedBooking.cancel();
            bookingRepository.save(loadedBooking);
            bookingDayRepository.removeBooking(loadedBooking.getId());

            var notification = new Notification(new Booking(booking.getId(), loadedBooking)
                    , Notification.NotificationType.BOOK_CANCELLED);
            notificationEventProducer.produceEvent(notification, String.valueOf( booking.getRoomNumber()));
        }
    }
}
