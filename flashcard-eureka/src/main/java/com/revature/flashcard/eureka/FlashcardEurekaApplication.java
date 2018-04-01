package com.revature.flashcard.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class FlashcardEurekaApplication {
	
	/*
	 *  Eureka Server
	 *  	-handles the microservice registery
	 *  	-handles the heartbeat(polling) of the microservice by returning the delta (changes of the list of ms)
	 *  
	 *  	Setup of Eureka:
	 *  		-@EnableEurekaServer
	 *  	then all services have to use @EnableEurekaClient
	 *  		-Default port: 8761
	 */

	public static void main(String[] args) {
		SpringApplication.run(FlashcardEurekaApplication.class, args);
	}
}
