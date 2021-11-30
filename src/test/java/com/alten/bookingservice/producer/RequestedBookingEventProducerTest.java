package com.alten.bookingservice.producer;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class RequestedBookingEventProducerTest {

    @Mock
    private KafkaTemplate<String, Booking> kafkaTemplate;

    @Test
    public void shouldProduceMessage() {
        // GIVEN
        var topicName = "topic";
        var bookingEventProducer = new RequestedBookingEventProducer(topicName, kafkaTemplate);
        var booking = SampleFactoryUtils.booking();

        // WHEN
        bookingEventProducer.produceEvent(booking, "123");

        // THEN
        Mockito.verify(kafkaTemplate).send(topicName, "123", booking);
    }

}