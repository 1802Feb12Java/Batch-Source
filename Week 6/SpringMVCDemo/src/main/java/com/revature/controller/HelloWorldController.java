package com.revature.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.model.Person;

@Controller
@RequestMapping(value="/home")
public class HelloWorldController {
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String goIndex() {
		return "/pages/index.html";
	}
	
	@GetMapping("/login")
	public String goLogin() {
		return "/pages/login.html";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String doLogin(String name, String pass) {
		Person p = new Person(name, pass);
		if (p.getName().equals("admin")) {
			return "redirect:index";
		} else {
			return "redirect:login";
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<String> newPerson(@PathVariable("name") String name) {
		return ResponseEntity.ok(name);
	}
	
	@GetMapping("/{name}/{password}")
	public ResponseEntity<String> insecure(@PathVariable("name") String name, 
			@PathVariable("password") String password) {
		return ResponseEntity.ok(name + " " + password);
	}
	
	@GetMapping("/hidden")
	public String goSecret() {
		return "secret";
	}
}
