package com.alten.bookingservice.consumer;

import com.alten.bookingservice.domain.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RequestedBookingEventListener {

    private static final Logger logger = LoggerFactory.getLogger(RequestedBookingEventListener.class);

    @KafkaListener(topics = "${topic.booking.request}", groupId = "${consumer.booking.service.group-id}")
    public void listen(Booking booking) {
        logger.info("method=listen message={}", booking);
    }
}
