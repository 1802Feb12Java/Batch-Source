package com.revature.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Pizza;

//@Controller
@RestController
@CrossOrigin
@RequestMapping("/api")
public class PizzaController {
	
	//@ResponseBody
	@GetMapping("/hello") 
	public String hello() {
		return "Hello";
	}
	
	@GetMapping(value="/pizza/{slices}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Pizza getPizza(@PathVariable("slices") Long slices) {
		String[] toppings = {"Coffee", "Chicken", "Cheetos"};
		return new Pizza(toppings, slices, "Alfredo");
	}
	
	@PostMapping(value="/pizza", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Pizza postPizza(@RequestBody Pizza pizza) {
		System.out.println(pizza);
		return pizza;
	}

}
