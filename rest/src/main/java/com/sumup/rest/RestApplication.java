package com.sumup.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {
	// TODO: Let the custom exceptions be RuntimeExceptions; let them bubble up to the @ControllerAdvice and log them there;
	// TODO: More producer config properties
	// TODO: Use the callback from kafkaTemplate::send
	// TODO: Add tests
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
