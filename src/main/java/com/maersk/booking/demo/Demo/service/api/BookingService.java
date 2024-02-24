package com.maersk.booking.demo.Demo.service.api;

import com.maersk.booking.demo.Demo.model.Booking;

public interface BookingService {

    Boolean validateBooking(Booking booking);

    String saveBooking(Booking booking);
}
