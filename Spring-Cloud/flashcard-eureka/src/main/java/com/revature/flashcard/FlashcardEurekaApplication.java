package com.revature.flashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class FlashcardEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashcardEurekaApplication.class, args);
	}
}
