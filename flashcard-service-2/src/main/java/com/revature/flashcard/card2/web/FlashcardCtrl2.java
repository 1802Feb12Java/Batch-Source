package com.revature.flashcard.card2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.flashcard.card2.bean.FlashCard;

@RestController
public class FlashcardCtrl2 {
	
	/*
	 * REST stuff
	 * @GET
	 * @Produces
	 * @Consumes
	 */
	@GetMapping("/getFc")
	public FlashCard getFC() {
		return new FlashCard(1, "fc2-question", "fc2-ansswer");
	}

}
