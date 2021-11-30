package com.alten.bookingservice.controller;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.dto.response.CreateBookingResponseDTO;
import com.alten.bookingservice.service.BookingService;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @Test
    public void shouldBookingStay() {
        // GIVEN
        var createBooking = SampleFactoryUtils.validCreateBookingRequestDTO();
        var createBookingResponse = new CreateBookingResponseDTO(new Booking(createBooking));

        Mockito.when(bookingService.createBookingEvent(createBooking)).thenReturn(createBookingResponse);

        // WHEN
        var response = bookingController.createBooking(createBooking);

        // THEN
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(createBookingResponse, response.getBody());
    }

}