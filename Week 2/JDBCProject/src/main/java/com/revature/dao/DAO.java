package com.revature.dao;

import java.util.List;

import com.revature.pojo.FlashCard;

public interface DAO {
	
	/*
	 * We only want CRUD
	 * Do not place business logic in here
	 */
	
	//Save - insert a new flashcard
	public void insertNewFC(int id, String question, String answer);
	
	//update a flashcard
	public void updateAFC(FlashCard fc);
	
	//Pull flashcard(s)
	public void retrieveFCById();
	
	//Retrieve all flash cards
	public List<FlashCard>retrieveAllFc();
	
	//Retrieve using a Cursor
	public List<FlashCard> retrieveAllFlashCards();
	
	//Delete Flashcard(s)
	public void deleteFCById();

}
