package com.alten.bookingservice.producer;

import com.alten.bookingservice.domain.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RequestedBookingEventProducer extends AbstractProducer<Booking>{

    @Autowired
    public RequestedBookingEventProducer(@Value("${topic.booking.request}") String topicName, KafkaTemplate<String, Booking> kafkaTemplate) {
        super(topicName, kafkaTemplate);
    }
}
