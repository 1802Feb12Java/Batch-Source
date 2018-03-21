package com.revature.pojo;

public class FlashCard {

	private int fcId;
	private String fcQuestion;
	private String fcAnswer;
	
	public FlashCard() {}
	
	public FlashCard(int fcId, String fcQuestion, String fcAnswer) {
		super();
		this.fcId = fcId;
		this.fcQuestion = fcQuestion;
		this.fcAnswer = fcAnswer;
	}
	
	public int getFcId() {
		return fcId;
	}
	public void setFcId(int fcId) {
		this.fcId = fcId;
	}
	public String getFcQuestion() {
		return fcQuestion;
	}
	public void setFcQuestion(String fcQuestion) {
		this.fcQuestion = fcQuestion;
	}
	public String getFcAnswer() {
		return fcAnswer;
	}
	public void setFcAnswer(String fcAnswer) {
		this.fcAnswer = fcAnswer;
	}
	
	@Override
	public String toString() {
		return "FlashCard [fcId=" + fcId + ", fcQuestion=" + fcQuestion + ", fcAnswer=" + fcAnswer + "]";
	}
	
}
