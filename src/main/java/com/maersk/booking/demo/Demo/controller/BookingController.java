package com.maersk.booking.demo.Demo.controller;


import com.maersk.booking.demo.Demo.model.Booking;
import com.maersk.booking.demo.Demo.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/bookings/")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping("create")
    public ResponseEntity<?> createBooking(@RequestBody Booking Booking) {
        String bookingNumber= bookingService.saveBooking(Booking);
        Map<String,String> responseMap=new HashMap<>();
        responseMap.put("bookingRef",bookingNumber);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @PostMapping("validate")
    public ResponseEntity<?> validateBooking(@RequestBody Booking Booking) {
        Boolean availableSpace= bookingService.validateBooking(Booking);
        Map<String,Boolean> responseMap=new HashMap<>();
        responseMap.put("availableSpace",availableSpace);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

}
