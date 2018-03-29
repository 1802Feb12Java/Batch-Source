package com.revature.flashcard.card2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FlashcardService2Application {

	public static void main(String[] args) {
		SpringApplication.run(FlashcardService2Application.class, args);
	}
}
