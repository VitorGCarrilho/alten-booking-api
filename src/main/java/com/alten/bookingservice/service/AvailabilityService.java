package com.alten.bookingservice.service;

import com.alten.bookingservice.dto.response.AvailabilityResponseDTO;
import com.alten.bookingservice.entity.BookingDayEntity;
import com.alten.bookingservice.entity.BookingDayEntityId;
import com.alten.bookingservice.repository.BookingDayCacheRepository;
import com.alten.bookingservice.repository.BookingDayRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);

    @Autowired
    private BookingDayRepository bookingDayRepository;

    @Autowired
    private BookingDayCacheRepository bookingDayCacheRepository;

    @HystrixCommand(
            commandKey = "getAvailabilityFromDB",
            fallbackMethod = "findCachedAvailability")
    public List<AvailabilityResponseDTO> getAvailability(int roomNumber, LocalDate from, LocalDate until) {
        logger.info("method=getAvailability roomNumber={} fromDate={} untilDate={}", roomNumber, from, until);

        var availabilityList = new ArrayList<AvailabilityResponseDTO>();
        var bookings = bookingDayRepository
                .findByRoomNumberAndBookingDateBetween(roomNumber, from, until)
                .stream()
                .map(BookingDayEntity::getBookId)
                .collect(Collectors.toList());

        for (LocalDate current = from; !current.isAfter(until); current = current.plusDays(1)) {
            var booked = bookings.contains(new BookingDayEntityId(current, roomNumber));
            availabilityList.add(new AvailabilityResponseDTO(current, booked));
        }

        bookingDayCacheRepository.saveAvailability(roomNumber, from, until, availabilityList);

        return availabilityList;
    }

    public List<AvailabilityResponseDTO> findCachedAvailability(int roomNumber, LocalDate from, LocalDate until) {
        logger.info("method=findCachedAvailability roomNumber={} fromDate={} untilDate={}", roomNumber, from, until);
        return bookingDayCacheRepository.getAvailability(roomNumber, from, until);
    }
}
