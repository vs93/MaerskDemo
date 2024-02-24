package com.maersk.booking.demo.Demo.validator.impl;

import com.maersk.booking.demo.Demo.model.Booking;
import com.maersk.booking.demo.Demo.util.ContainerType;
import com.maersk.booking.demo.Demo.validator.service.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.EnumSet;

@Component
public class BookingValidatorImpl implements BookingValidator {

    @Autowired
    Environment environment;

    @Override
    public Boolean bookingPreProcessor(Booking booking) {
        Boolean isValidRequest=Boolean.TRUE;
        if(booking.getContainerSize()!=20&&booking.getContainerSize()!=40)
            isValidRequest= false;
        if(EnumSet.allOf(ContainerType.class).stream().noneMatch(ct->ct.equals(booking.getContainerType())))
            isValidRequest= false;
        if(booking.getOrigin().length()<5||booking.getOrigin().length()>20)
            isValidRequest= false;
        if(booking.getDestination().length()<5||booking.getDestination().length()>20)
            isValidRequest= false;
        if(booking.getQuantity()<1||booking.getQuantity()>100)
            isValidRequest= false;

        return isValidRequest;
    }

    @Override
    public Integer checkAvailableSpace(Booking booking) {
        String[] activeProfiles=environment.getActiveProfiles();
        if(("test").equals(Arrays.stream(activeProfiles).findFirst().orElse(null)))
        {
            if(ContainerType.DRY.equals(booking.getContainerType()))
                return 5;
            if(ContainerType.REEFER.equals(booking.getContainerType()))
                return 0;
        }
        else
        {
            if(ContainerType.DRY.equals(booking.getContainerType()))
                 return 5;
            if(ContainerType.REEFER.equals(booking.getContainerType()))
                return 6;
        }
        return null;
    }
}
