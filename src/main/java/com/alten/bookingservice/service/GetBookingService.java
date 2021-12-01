package com.alten.bookingservice.service;

import com.alten.bookingservice.dto.response.BookingResponseDTO;
import com.alten.bookingservice.exception.ServiceCantGetDataException;
import com.alten.bookingservice.exception.NotFoundException;
import com.alten.bookingservice.repository.BookingCacheRepository;
import com.alten.bookingservice.repository.BookingRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBookingService {

    private static final Logger logger = LoggerFactory.getLogger(GetBookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingCacheRepository bookingCacheRepository;

    @HystrixCommand(
            commandKey = "getBookingFromDB",
            fallbackMethod = "findCachedBooking",
            ignoreExceptions = {NotFoundException.class})
    public BookingResponseDTO getBooking(String id) {
        logger.info("method=getBooking id={}", id);
        var book = bookingRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id +" not found"));

        return new BookingResponseDTO(book);
    }

    public BookingResponseDTO findCachedBooking(String id) {
        logger.info("method=findCachedBooking id={}", id);
        var book = bookingCacheRepository.getBooking(id);
        if (book == null) {
            throw new ServiceCantGetDataException("we are having issues trying to get the " + id);
        }
        return book;
    }
}
