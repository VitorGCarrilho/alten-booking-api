package com.alten.bookingservice.service;

import com.alten.bookingservice.dto.response.AvailabilityResponseDTO;
import com.alten.bookingservice.repository.BookingDayCacheRepository;
import com.alten.bookingservice.repository.BookingDayRepository;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AvailabilityServiceTest {

    @Mock
    private BookingDayRepository bookingDayRepository;

    @Mock
    private BookingDayCacheRepository cacheRepository;

    @InjectMocks
    private AvailabilityService availabilityService;

    @Test
    void getAvailability() {
        var room = 1;
        var from = LocalDate.now();
        var until = LocalDate.now().plusDays(30);
        var list = Arrays.asList(SampleFactoryUtils.bookingDayEntity());

        Mockito.when(bookingDayRepository.findByRoomNumberAndBookingDateBetween(room, from, until)).thenReturn(list);

        // WHEN
        var response = availabilityService.getAvailability(room, from, until);

        // THEN
        assertEquals(31, response.size());
    }

    @Test
    void findCachedAvailability() {

        // GIVEN
        var room = 1;
        var from = LocalDate.now();
        var until = LocalDate.now();
        var list = Arrays.asList(new AvailabilityResponseDTO(LocalDate.now(), true));

        Mockito.when(cacheRepository.getAvailability(room, from, until)).thenReturn(list);

        // WHEN
        var response = availabilityService.findCachedAvailability(room, from, until);

        // THEN
        assertEquals(list, response);
    }
}