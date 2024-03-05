package com.maersk.booking.demo.Demo.booking.impl;

import com.maersk.booking.demo.Demo.model.Booking;
import com.maersk.booking.demo.Demo.repository.BookingMongoRepository;
import com.maersk.booking.demo.Demo.booking.service.BookingService;
import com.maersk.booking.demo.Demo.validator.service.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Transactional
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
                Booking recentBooking=bookingRepository.findAll(Sort.by("bookingRefNumber").descending()).stream().findFirst().orElse(null);
                if(recentBooking==null)
                {
                    bookingRefNumber="957000001";
                }
                else
                {
                    BigDecimal refNumber= new BigDecimal(recentBooking.getBookingRefNumber());
                    refNumber=refNumber.add(new BigDecimal(1));
                    bookingRefNumber=refNumber.toString();
                }
                booking.setBookingRefNumber(bookingRefNumber);
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
