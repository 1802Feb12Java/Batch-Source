package com.revature.homeworkone;

import java.util.ArrayList;

public class Question12 {

	//the arraylist that holds the 1 to 100.
	//Isn't filled until answer() or constructor is called.
	private static ArrayList<Integer> aList= new ArrayList<>();
	
	
	//if you create a Question12 object will fill the array
	public Question12() {
		super();
		fillArray();
	}

	public static void fillArray() {
		for (int i = 1; i < 101; i++) {
			
			aList.add(i);
		}
	}
	
	public static ArrayList<Integer> answer(){
		ArrayList<Integer> oList = new ArrayList<>();
		fillArray();
		//here is the enhanced for loop to satisfy the question
		for (int i : aList) {
			//reusing Question6 to find even
			if(Question6.isEven(i)) {
				//a print statement here would print them out to console
				/*
				 * System.out.println(i);
				 */
				//but for readability i am adding to an arraylist
				//and calling ArrayList.toString() in HWDriver.
				oList.add(i);
			}
			
		}
		
		return oList;
		
	}
	
	
	
	
}
