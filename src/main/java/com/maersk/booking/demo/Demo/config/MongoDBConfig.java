package com.maersk.booking.demo.Demo.config;

//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories(basePackages = "com.maersk.booking.demo.Demo.repository")
@Slf4j
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "dbname";
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://admin:password@localhost:27017/bookingDb?authSource=admin&authMechanism=SCRAM-SHA-1");
    }
}
