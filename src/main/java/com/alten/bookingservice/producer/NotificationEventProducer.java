package com.alten.bookingservice.producer;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventProducer extends AbstractProducer<Notification>{

    @Autowired
    public NotificationEventProducer(@Value("${topic.booking.notification.name}") String topicName, KafkaTemplate<String, Notification> kafkaTemplate) {
        super(topicName, kafkaTemplate);
    }
}
