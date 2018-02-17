package com.revature.homeworkone;

import java.util.Scanner;

public class Question17 {

	//private String userInput;
	//private enum inputs { PRINCIPAL, RATE, TIME };
	
	
	public Question17() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void answer() {
		
		Scanner scanr = new Scanner(System.in);
		scanr.reset();
		System.out.println("\nQuestion #17 : Input principal, rate of interest, and number of years.");
		System.out.println("Enter Principal:");
		float input1 = scanr.nextFloat();
		System.out.println("Enter Rate:");
		float input2 = scanr.nextFloat();
		System.out.println("Enter Time:");
		float input3 = scanr.nextFloat();
		
		System.out.println("Interest = $" + (input1 * input2 * input3));
		
		scanr.close();
		
	}
	
	
	
	
	
	
	
	
	
}
