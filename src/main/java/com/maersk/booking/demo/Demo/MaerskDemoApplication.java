package com.maersk.booking.demo.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication

@EnableMongoRepositories("com.maersk.booking.demo.Demo.repository")
@ComponentScan(basePackages = "com.maersk.booking.demo.Demo")
public class MaerskDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaerskDemoApplication.class, args);
	}

}
