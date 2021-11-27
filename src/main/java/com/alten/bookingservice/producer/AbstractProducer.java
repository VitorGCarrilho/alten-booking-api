package com.alten.bookingservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class AbstractProducer<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractProducer.class);

    private String topicName;

    private KafkaTemplate<String, T> kafkaTemplate;

    public AbstractProducer(String topicName, KafkaTemplate<String, T> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceEvent(T message, String key) {
        logger.info("method=produceEvent message={}", message);
        kafkaTemplate.send(topicName, key, message);
    }
}
