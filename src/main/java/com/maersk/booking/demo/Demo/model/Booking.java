package com.maersk.booking.demo.Demo.model;

import com.maersk.booking.demo.Demo.util.ContainerType;
import lombok.*;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table("Boooking")
@Document(collection = "Booking")
//@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Booking {

        @Id
        private String bookingRefNumber;
        private String name;
        private Integer containerSize;
        private ContainerType containerType;
        private Integer quantity;
        private String origin;
        private String destination;
        private String createdTimeStamp;
        // Getters and setters
}
