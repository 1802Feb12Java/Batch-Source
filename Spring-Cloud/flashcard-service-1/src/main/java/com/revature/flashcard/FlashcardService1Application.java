package com.revature.flashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FlashcardService1Application {

	/*
	 * 2 projects
	 * Create a Flashcard service 1
	 * Create a Flashcard Eureka
	 * Create a Flashcard Zuul
	 * Create a Flashcard service 2
	 * 
	 * NOTE:  In application properties, include a comment (uses # instead of //) and indicate
	 * which application.properties file it is for future reference
	 * 
	 * 		Each project has 2 config files
	 * 			application.properties
	 * 			bootstrap.yml
	 * 
	 * 		.yml vs .properties
	 * 			properties: no spacing, key value pairs
	 * 			yml: indentation is important, can use dot notation to get javadocs
	 * 					allows spacing, strict format, multiline properties
	 * 
	 * 		bootstrap vs application
	 *				bootstrap loads first with the default configuration
	 *				application loads second and overwrites bootstrap
	 *
	 *			Spring Boot Actuator:
	 *				Actuator endpoints let you monitor and interact with your apaplication
	 *
	 *
	 *			FlashCard Service 1 Configuration (Business Service)
	 *				create bootstrap.yml and provide spring application name
	 *				add @EnableEurekaClient
	 *				set server port
	 *				set Eureka properties
	 *				create endpoints
	 *
	 *			FlashCard Eureka  (Service Discovery)
	 *				create bootstrap.yml and provide spring application name
	 *				add @EnableEurekaServer
	 *				set server port
	 *				set Eureka properties
	 *
	 *			FlashCard Zuul (API-Gateway)  Single point of entry
	 *				create bootstrap.yml and provide spring application name
	 *				add @EnableZuulProxy then @EnableEurekaClient
	 *				set server port
	 *				set eureka properties
	 *
	 *			Short-cuts
	 *				alt + up/down = move line
	 *				vtrl + alt + up/down = duplicate line
	 *				ctrl + d = delete line
	 *				ctrl + / = comments
	 *				alt + shift + s = constructors/setters
	 *				
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(FlashcardService1Application.class, args);
	}
}
