package com.alten.bookingservice.controller;

import com.alten.bookingservice.dto.request.CreateBookingRequestDTO;
import com.alten.bookingservice.dto.response.CreateBookingResponseDTO;
import com.alten.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("")
    public String helloWorld() {
        return "hello world";
    }

    @PostMapping
    public ResponseEntity<CreateBookingResponseDTO> createBooking(@RequestBody CreateBookingRequestDTO createBookingRequestDTO){
        var response = bookingService.createBooking(createBookingRequestDTO);
        return ResponseEntity.ok(response);
    }

}
