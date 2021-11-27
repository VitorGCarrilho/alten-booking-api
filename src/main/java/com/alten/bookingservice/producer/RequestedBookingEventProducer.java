package com.alten.bookingservice.producer;

import com.alten.bookingservice.dto.BookingDTO;
import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
import com.alten.bookingservice.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RequestedBookingEventProducer extends AbstractProducer<BookingDTO>{

    @Autowired
    public RequestedBookingEventProducer(@Value("${topic.booking.request}") String topicName, KafkaTemplate<String, BookingDTO> kafkaTemplate) {
        super(topicName, kafkaTemplate);
    }
}
