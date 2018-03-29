package com.revature.flashcard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.flashcard.bean.FlashCard;

/**
 * 2 Microservices talking to each other
 * 			using REST, Send a Rest Request in Spring
 * 			using RestTemplates
 * 
 * 		We do not want to hardcode the URL to the other
 * 			microservice...we want to inject it so we don't
 * 			have to worry about changing it later
 *
 */
@RestController
public class FlashCardCtrl {
	@GetMapping("/")
	public String home() {
		return "test";
	}
	
	@LoadBalanced
	@Bean("FlashCard")
	public RestTemplate restTemplateBuilder(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/fc")
	public FlashCard fc() {
		System.out.println("fc");
		
		String url = "http://flashcard-service-2/getFc";
		FlashCard fc = restTemplate.getForObject(url,  FlashCard.class);
		return fc;
	}
	

	
}
