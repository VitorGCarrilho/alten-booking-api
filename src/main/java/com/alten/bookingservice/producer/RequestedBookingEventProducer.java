package com.alten.bookingservice.producer;

import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
import com.alten.bookingservice.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RequestedBookingEventProducer extends AbstractProducer<CreateBookingRequestDTO>{

    @Autowired
    public RequestedBookingEventProducer(@Value("${topic.booking.request}") String topicName, KafkaTemplate<String, CreateBookingRequestDTO> kafkaTemplate) {
        super(topicName, kafkaTemplate);
    }
}
