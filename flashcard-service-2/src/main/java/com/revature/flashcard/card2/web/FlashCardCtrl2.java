package com.revature.flashcard.card2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.flashcard.card2.bean.FlashCard;

@RestController
public class FlashCardCtrl2 {

	@GetMapping("/getFc")
	public FlashCard getFc(){
		System.out.println("/getFc");
		return new FlashCard(1, "fc2-question", "fc2-answer");
	}
	
}
