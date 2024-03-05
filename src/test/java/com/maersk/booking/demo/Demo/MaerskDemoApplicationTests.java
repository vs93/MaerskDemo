package com.maersk.booking.demo.Demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.booking.demo.Demo.controller.BookingController;
import com.maersk.booking.demo.Demo.model.Booking;
import com.maersk.booking.demo.Demo.booking.service.BookingService;
import com.maersk.booking.demo.Demo.booking.impl.BookingServiceImpl;
import com.maersk.booking.demo.Demo.validator.impl.BookingValidatorImpl;
import com.maersk.booking.demo.Demo.validator.service.BookingValidator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
class MaerskDemoApplicationTests {

	final String directory="src/test/resources/";
	private static final ObjectMapper objectMapper= new ObjectMapper();

	@Test
	void contextLoads() {
	}

	@Test
	public void testAvailableSpacePositiveFlow() throws IOException {
     Booking booking= objectMapper.readValue(new File(directory + "Booking.json"), new TypeReference<Booking>() {}
	 );

     Environment environment= Mockito.mock(Environment.class);
     BookingValidator bookingValidator= new BookingValidatorImpl();
     ReflectionTestUtils.setField(bookingValidator,"environment",environment);
     BookingService bookingService=new BookingServiceImpl();
     ReflectionTestUtils.setField(bookingService,"bookingValidator",bookingValidator);
     BookingController bookingController=new BookingController();
     ReflectionTestUtils.setField(bookingController,"bookingService",bookingService);
     String[] profile ={"test"};
     Mockito.doReturn(profile).when(environment).getActiveProfiles();
     ResponseEntity<?> availableSpaceResponse =bookingController.validateBooking(booking);
     Boolean value=(Boolean)((Map)availableSpaceResponse.getBody()).get("availableSpace");
     Assert.assertEquals(value,true);
	}

    @Test
    public void testAvailableSpaceNegativeFlow() throws IOException {
        Booking booking= objectMapper.readValue(new File(directory + "Booking1.json"), new TypeReference<Booking>() {}
        );

        Environment environment= Mockito.mock(Environment.class);
        BookingValidator bookingValidator= new BookingValidatorImpl();
        ReflectionTestUtils.setField(bookingValidator,"environment",environment);
        BookingService bookingService=new BookingServiceImpl();
        ReflectionTestUtils.setField(bookingService,"bookingValidator",bookingValidator);
        BookingController bookingController=new BookingController();
        ReflectionTestUtils.setField(bookingController,"bookingService",bookingService);
        String[] profile ={"test"};
        Mockito.doReturn(profile).when(environment).getActiveProfiles();
        ResponseEntity<?> availableSpaceResponse =bookingController.validateBooking(booking);
        Boolean value=(Boolean)((Map)availableSpaceResponse.getBody()).get("availableSpace");
        Assert.assertEquals(value,false);
    }

}
