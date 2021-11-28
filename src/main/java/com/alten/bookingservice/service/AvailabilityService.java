package com.alten.bookingservice.service;

import com.alten.bookingservice.dto.response.AvailabilityResponseDTO;
import com.alten.bookingservice.entity.BookingDayEntity;
import com.alten.bookingservice.entity.BookingDayEntityId;
import com.alten.bookingservice.repository.BookingDayRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    @Autowired
    private BookingDayRepository bookingDayRepository;

    @HystrixCommand(
            commandKey = "getAvailabilityFromDB",
            fallbackMethod = "findCachedAvailability")
    public List<AvailabilityResponseDTO> getAvailability(int roomNumber, LocalDate from, LocalDate until) {
        var availabilityList = new ArrayList<AvailabilityResponseDTO>();
        var bookings = bookingDayRepository
                .findByRoomNumberAndBookingDateBetween(roomNumber, from, until)
                .stream()
                .map(BookingDayEntity::getBookId)
                .collect(Collectors.toList());

        var current = from;
        while (!current.isAfter(until)) {
            var booked = bookings.contains(new BookingDayEntityId(current, roomNumber));
            availabilityList.add(new AvailabilityResponseDTO(current, booked));
            current = current.plusDays(1);
        }

        return availabilityList;
    }

    public List<AvailabilityResponseDTO> findCachedAvailability(int roomNumber, LocalDate from, LocalDate until) {
        return null;
    }
}
