package com.revature.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FlashcardService1Application {
	
	/*
	 * Create a flashcard service 1
	 * Create a flashcard eureka
	 * Create a flashcard zuul
	 * Create a flashcard service 2
	 * 
	 * 		Each project has 2 config files
	 * 			-application.properties
	 * 			-bootstrap.yml
	 * 
	 * 		.yml vs .properties
	 * 			-properties: no spacing, key/value pairs, = 
	 * 			-yml: allows spacing, strict format
	 * 
	 * 		bootstrap vs application
	 * 			-bootstrap loads first with default config
	 * 			-application loads second and overrides bootstrap
	 * 
	 * 		Flashcard Service 1 config (business service)
	 * 			-create bootstrap.yml and provide spring application name
	 * 			-add @EnableEurekaClient
	 * 			-set server port
	 * 			-set eureka properties
	 * 			-create endpoints
	 * 
	 * 		Flashcard Eureka (service discovery)
	 * 			-create bootstrap.yml and provide spring application name
	 * 			-add @EnableEurekaServer
	 * 			-set server port
	 * 			-set eureka properties
	 * 
	 * 		Flashcard Zuul( API-Gateway, single point of entry)
	 * 			-create bootstrap.yml and provide spring application name
	 * 			-add @EnableZuulProxy
	 * 			-add @EnableEurekaClient
	 * 			-set server properties
	 * 			-set eureka properties
	 * 
	 * 		Flashcard Service 2 config (business service)
	 * 			-create bootstrap.yml and provide spring application name
	 * 			-add @EnableEurekaClient
	 * 			-set server port
	 * 			-set eureka properties
	 * 			-create endpoints
	 */

	public static void main(String[] args) {
		SpringApplication.run(FlashcardService1Application.class, args);
	}
}
