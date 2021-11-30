package com.alten.bookingservice.consumer;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.domain.Notification;
import com.alten.bookingservice.service.CancelBookService;
import com.alten.bookingservice.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventListener {

    private static final Logger logger = LoggerFactory.getLogger(NotificationEventListener.class);

    @Autowired
    private NotificationService notificationService;

    @RetryableTopic(
            attempts = "10",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            autoCreateTopics = "false",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
    @KafkaListener(topics = "${topic.booking.notification.name}", groupId = "${consumer.booking.service.group-id}")
    public void listen(Notification notification) {
        logger.info("\n\n\n\n\n\n\nmethod=listen message={}", notification);
        notificationService.notify(notification);
    }
}
