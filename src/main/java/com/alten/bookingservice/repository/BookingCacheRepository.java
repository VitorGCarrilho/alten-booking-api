package com.alten.bookingservice.repository;

import com.alten.bookingservice.dto.response.AvailabilityResponseDTO;
import com.alten.bookingservice.dto.response.BookingResponseDTO;
import com.alten.bookingservice.exception.ServiceCantGetDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingCacheRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookingCacheRepository.class);

    @Autowired
    private RedisTemplate<String, BookingResponseDTO> redisTemplate;

    public BookingResponseDTO getBooking(String id) {
        logger.info("method=getBooking id={}", id);
        try {
            return redisTemplate.opsForValue().get(id);
        } catch (Exception e) {
            throw new ServiceCantGetDataException("Cant load the book "+ id);
        }
    }

    public void saveBooking(BookingResponseDTO bookingResponseDTO) {
        try {
            redisTemplate.opsForValue().set(bookingResponseDTO.getId(), bookingResponseDTO);
        } catch (Exception e) {
            logger.error("method=saveBooking status=cant save cache in redis", e);
        }
    }
}
