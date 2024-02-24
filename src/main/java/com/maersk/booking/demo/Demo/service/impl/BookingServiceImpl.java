package com.maersk.booking.demo.Demo.service.impl;

import com.maersk.booking.demo.Demo.model.Booking;
import com.maersk.booking.demo.Demo.repository.BookingMongoRepository;
import com.maersk.booking.demo.Demo.service.api.BookingService;
import com.maersk.booking.demo.Demo.validator.service.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingValidator bookingValidator;

    @Autowired
    private BookingMongoRepository bookingRepository;

    @Override
    public Boolean validateBooking(Booking booking) {
        Boolean isValidRequest=bookingValidator.bookingPreProcessor(booking);
        if(isValidRequest!=null&& isValidRequest)
        {
          Integer availableSpace = bookingValidator.checkAvailableSpace(booking);
          if(availableSpace==0)
              isValidRequest=Boolean.FALSE;
        }

        return isValidRequest;
    }

    @Override
    public String saveBooking(Booking booking) {
        String bookingRefNumber = null;
        Boolean isValidRequest=bookingValidator.bookingPreProcessor(booking);
        if(isValidRequest!=null&& isValidRequest)
        {
            try {
                final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_ORDINAL_DATE;
                LocalDateTime ldt = LocalDateTime.now();
                String formattedDateTime = ldt.format(ISO_FORMATTER);
                booking.setCreatedTimeStamp(formattedDateTime);
                Booking savedBooking = bookingRepository.save(booking);
                bookingRefNumber = savedBooking.getBookingRefNumber();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return bookingRefNumber!=null?bookingRefNumber:null;
    }
}
