package com.maersk.booking.demo.Demo.controller;


import com.maersk.booking.demo.Demo.model.Booking;
import com.maersk.booking.demo.Demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public Flux<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Booking> getBookingById(@PathVariable String id) {
        return bookingRepository.findById(id);
    }

    @PostMapping
    public Mono<Booking> createBooking(@RequestBody Booking Booking) {
        return bookingRepository.save(Booking);
    }

    @PutMapping("/{id}")
    public Mono<Booking> updateBooking(@PathVariable String id, @RequestBody Booking booking) {
        booking.setBookingRefNumber(id);
        return bookingRepository.save(booking);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBooking(@PathVariable String id) {
        return bookingRepository.deleteById(id);
    }
}
