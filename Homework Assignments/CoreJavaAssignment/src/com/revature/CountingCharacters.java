package com.revature;

import java.util.Scanner;

public class CountingCharacters {
	/*
	 * The purpose of this class is to convert a string
	 * to a character array and then count the number of
	 * characters
	 */

	public static void main(String[] args) {
		
		runningCountingCharacters();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
		
	}//end main

	public static int countingCharacters(String[] s) {
		return s.length;
	}//end countingCharacters method
	
	public static void runningCountingCharacters() {
		Scanner read = new Scanner(System.in);
		System.out.println("Please enter a string: ");
		String userString = read.nextLine();
		String[] strArray = userString.split("");
		System.out.println("The number of characters in your string (including spaces and symbols) is: "
				+ countingCharacters(strArray));
		
	}//end runningCountingCharacters method
	
	
	
}//end class
