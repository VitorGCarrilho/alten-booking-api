package com.alten.bookingservice.consumer;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.service.CancelBookService;
import com.alten.bookingservice.service.UpdateBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class UpdateBookEventListener {

    private static final Logger logger = LoggerFactory.getLogger(UpdateBookEventListener.class);

    @Autowired
    private UpdateBookService updateBookService;

    @RetryableTopic(
            attempts = "10",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            autoCreateTopics = "false",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
    @KafkaListener(topics = "${topic.booking.update.name}", groupId = "${consumer.booking.service.group-id}")
    public void listen(Booking booking) {
        logger.info("method=listen message={}", booking);
        updateBookService.update(booking);
    }
}
