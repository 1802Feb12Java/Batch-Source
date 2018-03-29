package com.revature.FootballBat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SadController {

	@GetMapping("/sad")
	@ResponseBody
	public static String imSad() {
		return "boohoo";
	}
	
}
