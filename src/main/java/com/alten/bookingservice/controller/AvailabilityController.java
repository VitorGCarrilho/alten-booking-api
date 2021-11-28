package com.alten.bookingservice.controller;

import com.alten.bookingservice.dto.response.AvailabilityResponseDTO;
import com.alten.bookingservice.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityController;

    @GetMapping
    public ResponseEntity<List<AvailabilityResponseDTO>> getAvailability(
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate untilDate,
            @RequestParam(required = false, defaultValue = "1") int roomNumber){
        var response = availabilityController.getAvailability(roomNumber, fromDate, untilDate);
        return ResponseEntity.ok(response);
    }
}
