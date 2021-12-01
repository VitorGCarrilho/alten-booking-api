package com.alten.bookingservice.producer;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.domain.Notification;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class NotificationEventProducerTest {

    @Mock
    private KafkaTemplate<String, Notification> kafkaTemplate;

    @Test
    public void shouldProduceMessage() {
        // GIVEN
        var topicName = "topic";
        var notificationProducer = new NotificationEventProducer(topicName, kafkaTemplate);
        var notification = SampleFactoryUtils.notification();

        // WHEN
        notificationProducer.produceEvent(notification, "123");

        // THEN
        Mockito.verify(kafkaTemplate).send(topicName, "123", notification);
    }

}