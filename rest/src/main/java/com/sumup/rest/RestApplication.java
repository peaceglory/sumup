package com.sumup.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {
	// TODO: Load properties from config service
	// TODO: Program against interfaces
	// TODO: More producer config properties
	// TODO: Add validation on ArticleRequest
	// TODO: Use the callback from kafkaTemplate::send
	// TODO: Add tests
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
