package com.maersk.booking.demo.Demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("Boooking")
public class Booking {
        @PrimaryKey
        private String bookingRefNumber;
        private String name;
        // Getters and setters
}
