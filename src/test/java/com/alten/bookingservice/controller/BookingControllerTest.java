package com.alten.bookingservice.controller;

import com.alten.bookingservice.domain.Booking;
import com.alten.bookingservice.dto.response.BookingResponseDTO;
import com.alten.bookingservice.dto.response.CreateBookingResponseDTO;
import com.alten.bookingservice.service.BookingService;
import com.alten.bookingservice.service.CancelBookService;
import com.alten.bookingservice.service.GetBookingService;
import com.alten.bookingservice.service.UpdateBookService;
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

    @Mock
    private UpdateBookService updateService;

    @Mock
    private CancelBookService cancelBookService;

    @Mock
    private GetBookingService getBookingService;

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

    @Test
    public void shouldUpdate() {
        // GIVEN
        var id = "123";
        var update = SampleFactoryUtils.validCreateBookingRequestDTO();
        var createBookingResponse = new CreateBookingResponseDTO(new Booking(update));

        // WHEN
        var response = bookingController.updateBook(id, update);

        // THEN
        Mockito.verify(updateService).updateBookEvent(id, update);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void shouldCancel() {
        // GIVEN
        var id = "123";
        var update = SampleFactoryUtils.validCreateBookingRequestDTO();
        var createBookingResponse = new CreateBookingResponseDTO(new Booking(update));

        // WHEN
        var response = bookingController.cancelBook(id);

        // THEN
        Mockito.verify(cancelBookService).cancelBookEvent(id);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void shouldGet() {
        // GIVEN
        var id = "123";
        var update = SampleFactoryUtils.validCreateBookingRequestDTO();
        BookingResponseDTO bookingResponseDTO = SampleFactoryUtils.bookingResponseDTO();

        Mockito.when(getBookingService.getBooking(id)).thenReturn(bookingResponseDTO);

        // WHEN
        var response = bookingController.getBooking(id);

        // THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookingResponseDTO, response.getBody());
    }

}