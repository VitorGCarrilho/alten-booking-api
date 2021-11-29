package com.alten.bookingservice.repository;

import com.alten.bookingservice.dto.response.AvailabilityResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingDayCacheRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookingDayCacheRepository.class);

    @Autowired
    private RedisTemplate<String, List<AvailabilityResponseDTO>> redisTemplate;

    public List<AvailabilityResponseDTO> getAvailability(int roomNumber, LocalDate from, LocalDate until) {
        logger.info("method=getAvailability roomNumber={} fromDate={} untilDate={}", roomNumber, from, until);
        return redisTemplate.opsForValue().get(getId(roomNumber, from, until));
    }

    public void saveAvailability(int roomNumber, LocalDate from, LocalDate until, List<AvailabilityResponseDTO> availabilityResponseDTOList) {
        try {
            redisTemplate.opsForValue().set(getId(roomNumber, from, until), availabilityResponseDTOList);
        } catch (Exception e) {
            logger.error("method=saveAvailability status=cant save cache in redis", e);
        }
    }

    private String getId(int roomNumber, LocalDate from, LocalDate until) {
        return String.format("%d:%s:%s", roomNumber, from, until);
    }
}
