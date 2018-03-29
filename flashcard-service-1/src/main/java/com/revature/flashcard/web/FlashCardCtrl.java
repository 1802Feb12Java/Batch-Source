package com.revature.flashcard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.flashcard.bean.FlashCard;

@Configuration
@RestController
public class FlashCardCtrl {
 
	@Value("${fc.rest.endpoints}")
	private String fcRestEndpoints;
	
	@GetMapping("/")
	public String home(){
		return "test";
	}
	
	/*
	 * 	Have 2 Microservices talking to eachother
	 * 		Using REST, Send a Rest Request in
	 * 			Spring using
	 * 		RestTemplates
	 * 
	 *  We do not want to hardcode the URL to the other
	 *  	microservice...we want to inject it so we 
	 *  	don't have to worry about changing it later
	 */
	@LoadBalanced
	@Bean() //strict method name: buildRestTemplate
	public RestTemplate buildRestTemplate(RestTemplateBuilder restTemplateBuilder){
		return restTemplateBuilder.build();
	}
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping("/fc")
	@HystrixCommand(fallbackMethod="fcBackUp")
	public FlashCard fc(){
		System.out.println("fcRestEndpoints: " + fcRestEndpoints);
		String url = "http://" + fcRestEndpoints + "/getFc";
		FlashCard fc = restTemplate.getForObject(url, FlashCard.class);
		return fc;
	}
	
	public FlashCard fcBackUp(){
		return new FlashCard(1, "cached-Q", "cached-A");
	}
}
