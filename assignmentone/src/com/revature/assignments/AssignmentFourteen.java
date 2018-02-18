package com.revature.assignments;

import java.util.Arrays;

public class AssignmentFourteen {
	/*
	 * Write a program that demonstrates the switch case. Implement the following
	 * functionalities in the cases:java Case 1: Find the square root of a number
	 * using the Math class method. Case 2: Display today’s date. Case 3: Split the
	 * following string and store it in a string array. “I am learning Core Java”
	 * 
	 */

	public static void switchTest(int i) {
		switch (i) {
		case 1:
			System.out.println("\tSquare root of 4 = " + java.lang.Math.sqrt(4.0));
			break;
		case 2:
			System.out.println("\tToday's date is " + java.time.LocalDate.now());
			break;
		case 3:
			String splitThis = "I am learning Core Java";
			System.out.println("\tSplitting String into array...");
			String[] splitted = splitThis.split("\\s");
			System.out.println("\tSplitted: " + Arrays.toString(splitted));
			break;
		default:
			System.out.println("\tPlease select a valid case (1-3)");
		}
	}
}
