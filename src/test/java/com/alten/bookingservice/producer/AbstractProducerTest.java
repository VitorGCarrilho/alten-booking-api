package com.alten.bookingservice.producer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AbstractProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void shouldProduceMessage() {
        // GIVEN
        var topicName = "topic";
        var abstractProducer = new AbstractProducer<String>(topicName, kafkaTemplate);

        // WHEN
        abstractProducer.produceEvent("message", "123");

        // THEN
        Mockito.verify(kafkaTemplate).send(topicName, "123", "message");
    }

}