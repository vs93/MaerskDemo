package com.maersk.booking.demo.Demo.repository;

import com.maersk.booking.demo.Demo.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Repository
public interface BookingMongoRepository extends MongoRepository<Booking, Integer> {

    @Query("{ 'bookingRefNumber': ?0 }")
    Booking findByName(final String name);

    @Query("{ 'bookingRefNumber': ?0 }")
    void deleteByName(final String ref);

}
