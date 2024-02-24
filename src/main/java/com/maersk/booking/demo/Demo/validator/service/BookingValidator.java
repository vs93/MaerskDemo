package com.maersk.booking.demo.Demo.validator.service;

import com.maersk.booking.demo.Demo.model.Booking;

public interface BookingValidator {

    Boolean bookingPreProcessor(Booking booking);

    Integer checkAvailableSpace(Booking booking);

}
