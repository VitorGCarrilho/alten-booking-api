package com.alten.bookingservice.controller;

import com.alten.bookingservice.dto.response.AvailabilityResponseDTO;
import com.alten.bookingservice.service.AvailabilityService;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AvailabilityControllerTest {

    @Mock
    private AvailabilityService availabilityService;

    @InjectMocks
    private AvailabilityController availabilityController;

    @Test
    public void shouldGetAvailability() {
        // GIVEN
        var from = LocalDate.now();
        var until = LocalDate.now();
        var roomNumber = 1;

        var list = Arrays.asList(new AvailabilityResponseDTO(LocalDate.now(), true));
        Mockito.when(availabilityService.getAvailability(roomNumber, from, until)).thenReturn(list);

        // WHEN
        var response = availabilityController.getAvailability(from, until, roomNumber);

        // THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }

}