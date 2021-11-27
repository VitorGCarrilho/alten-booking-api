package com.alten.bookingservice.consumer;

import com.alten.bookingservice.dto.BookingDTO;
import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
import com.alten.bookingservice.producer.RequestedBookingEventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RequestedBookingEventListener {

    private static final Logger logger = LoggerFactory.getLogger(RequestedBookingEventListener.class);

    @KafkaListener(topics = "${topic.booking.request}", groupId = "${consumer.booking.service.group-id}")
    public void listen(BookingDTO bookingDTO) {
        logger.info("method=listen message={}", bookingDTO);
    }
}
