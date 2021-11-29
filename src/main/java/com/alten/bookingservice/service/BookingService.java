package com.alten.bookingservice.service;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
import com.alten.bookingservice.dto.response.CreateBookingResponseDTO;
import com.alten.bookingservice.entity.BookingEntity;
import com.alten.bookingservice.entity.factory.BookingDayEntityFactory;
import com.alten.bookingservice.exception.AlreadyBookedException;
import com.alten.bookingservice.producer.RequestedBookingEventProducer;
import com.alten.bookingservice.repository.BookingDayRepository;
import com.alten.bookingservice.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingDayRepository bookingDayRepository;

    @Autowired
    private BookingDayEntityFactory bookingDayEntityFactory;

    @Autowired
    private RequestedBookingEventProducer requestedBookingEventProducer;

    public CreateBookingResponseDTO createBookingEvent(CreateBookingRequestDTO createBookingRequestDTO) {
        logger.info("method=createBookingEvent");
        var booking = new Booking(createBookingRequestDTO);
        logger.info("method=createBookingEvent bookingId={}", booking.getId());
        requestedBookingEventProducer.produceEvent(booking, String.valueOf(booking.getRoomNumber()));
        return new CreateBookingResponseDTO(booking);
    }

    @Transactional
    public void bookStay(Booking booking) {
        logger.info("method=bookStay id={}", booking.getId());
        var bookingEntity = new BookingEntity(booking);
        bookingRepository.save(bookingEntity);

        try {
            var bookingDays = bookingDayEntityFactory.getBookingDays(bookingEntity);
            bookingDayRepository.saveAll(bookingDays);
            bookingEntity.accept();
            bookingRepository.save(bookingEntity);

            logger.info("method=bookStay id={} status=booked", booking.getId());
            // TODO NOTIFY SUCCESS
        } catch (AlreadyBookedException e) {
            logger.info("method=bookStay status=AlreadyBookedException");
            bookingEntity.deny();
            bookingRepository.save(bookingEntity);

            logger.info("method=bookStay id={} status=denied", booking.getId());
            // TODO NOTIFY FAIL
        }

    }
}
