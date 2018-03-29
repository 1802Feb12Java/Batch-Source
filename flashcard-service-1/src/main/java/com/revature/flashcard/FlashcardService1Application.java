package com.revature.flashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class FlashcardService1Application {

	/*
	 * 		3 projects
	 * 	Create a FlashCard service 1
	 * 	Create a FlashCard Eureka
	 *  create a FlashCard Zuul
	 * 
	 * 		Each project has 2 config files
	 * 			-application.properties
	 * 			-bootstrap.yml
	 * 
	 * 		.yml vs .properties
	 * 			-properties: no spacing, key/value pairs, = 
	 * 			-yml: allows spacing, strict format, multiline properties
	 * 		
	 * 		bootstrap vs application
	 * 			-bootstrap loads first with the default configuration
	 * 			-application loads second and overwrites bootstrap
	 * 
	 * 		Spring Boot Actuator:
	 * 			Actuator endpoints let you monitor and interact with your application.
	 * 
	 * 		FlashCard Service 1 Configuration (Business Service)
	 * 			-create bootstrap.yml and provide spring application name
	 * 			-add @EnableEurekaClient
	 * 			-set server port
	 * 			-set eureka properties
	 * 			-create endpoints
	 * @Value
	 * 		FlashCard Eureka (Service Discovery)
	 * 			-create bootstrap.yml and provide spring application name
	 * 			-add @EableEurekaServer
	 * 			-set server port
	 * 			-set eureka properties
	 * 		
	 * 		FlashCard Zuul ( API-Gateway, single point of entry)
	 * 			-create bootstrap.yml and provide spring application name
	 * 			-add @EnableZuulProxy then @EnableEurekaClient
	 * 			-set server port
	 * 			-set eureka properties
	 * 		
	 * 		NEW
	 *		FlashCard-Config-Server
	 *			?
	 *
	 * 		Hystrix added
	 * 			?
	 * 
	 * 		Short-cuts
	 * 			alt + up/down = move line
	 * 			ctrl + alt + up/down = duplicate line
	 * 			ctrl + d = delete line
	 *			ctrl + / = comment/uncomment
	 *			ctrl + shift + f = auto format
	 * 			
	 * 			for pojos
	 * 			alt + shift + s then underlined letter for generating
	 * 				
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(FlashcardService1Application.class, args);
	}
}
