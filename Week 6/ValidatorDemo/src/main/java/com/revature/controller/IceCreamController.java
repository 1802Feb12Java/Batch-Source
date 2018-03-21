package com.revature.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.revature.model.IceCream;

@Controller
public class IceCreamController {
	@GetMapping("/")
	public String showForm(IceCream iceCream) {
		return "form";
	}
	
	@PostMapping("/")
	public String validateIceCream(
			@Valid IceCream iceCream, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		}
		
		return "redirect:/order";
	}
	
	@GetMapping("/hello")
	public String helloForm(Model model) {
		model.addAttribute("iceCream", new IceCream("vanilla", 2));
		return "hello";
	}
	
	@PostMapping("/hello")
	public String helloSubmit(@ModelAttribute IceCream iceCream) {
		return "hello";
	}
}
