package com.revature.answers;

import java.time.LocalDate;

public class Question14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		switcher(1);
		switcher(2);
		switcher(3);
		switcher(500);
		
	}
	
	public static void switcher(int n) {
		switch(n) {
		case 1: System.out.println(Math.sqrt(49));			//Calls the squareroot method from Math class
			break;
		case 2: LocalDate today = LocalDate.now();			//Making a local date instance to get the date
				System.out.println(today);
			break;
		case 3: System.out.println("I am learning core java.");
			break;
		default: System.out.println("You did not present a vaild case.");
		}
	}

}
