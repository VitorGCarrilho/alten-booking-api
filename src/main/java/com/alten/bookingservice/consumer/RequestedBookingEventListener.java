package com.alten.bookingservice.consumer;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RequestedBookingEventListener {

    private static final Logger logger = LoggerFactory.getLogger(RequestedBookingEventListener.class);

    @Autowired
    private BookingService bookingService;

    @KafkaListener(topics = "${topic.booking.request.name}", groupId = "${consumer.booking.service.group-id}")
    public void listen(Booking booking) {
        logger.info("method=listen message={}", booking);
        bookingService.bookStay(booking);
    }
}
