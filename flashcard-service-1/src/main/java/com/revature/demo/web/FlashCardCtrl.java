package com.revature.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.demo.bean.FlashCard;

@RestController
public class FlashCardCtrl {

	 @GetMapping("/")
	    public String home() {
		return "test";
	}
	 
	 /*
	  * Have 2 microservices talking to each other
	  * 	using REST, send a Rest Request in Spring using RestTemplates
	  * 
	  * We do not want to hardcode the url to the other microservice
	  * 	we want to inject it so we don't worry about it later
	  */
	 
	 @LoadBalanced
	 @Bean
	 public RestTemplate makeTemp(RestTemplateBuilder builder) {
		
		 return builder.build();
		 
	 }
	 
	 @Autowired
	 private RestTemplate rt;
	 
	 @GetMapping("/fc")
	 public FlashCard fc() {
		 System.out.println("fc");
		 String url = "http://flashcard-service-2/getFc";
		 FlashCard fc = rt.getForObject(url, FlashCard.class);
		return fc;
		 
	 }
}
