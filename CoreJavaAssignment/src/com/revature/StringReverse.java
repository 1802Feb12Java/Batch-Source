package com.revature;

import java.util.Scanner;

public class StringReverse {
	/*
	 * The purpose of this class is to create a method to reverse a string
	 * without using a temporary variable or a pre-existing reversal method.
	 */
	public static void main(String[] args) {

		runStringReverse();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
	}
	
	public static void runStringReverse() {
		Scanner read = new Scanner(System.in);
		System.out.print("Please enter the string you would like to reverse: ");
		String userString = read.nextLine();
		System.out.println("Your reversed string is: " + reverseString(userString));
		
	}//end runStringReverse method
	
	public static String reverseString(String s) {
		
		int length = s.length();//set the starting length for reuseability
		for(int i = length-1; i >= 0; i--) { //traverse the string backwards
			s = s.concat(s.split("")[i]);
		}
		return s.substring(length);
	} //end reverseString method

}//end class
