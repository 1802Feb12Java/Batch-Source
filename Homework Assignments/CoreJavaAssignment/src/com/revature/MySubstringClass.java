package com.revature;

import java.util.Scanner;

public class MySubstringClass {
	/*
	 * The purpose of this class is to create a substring
	 * method without using pre-existing substring methods.
	 */
	
	public static void main(String[] args) {
		
		runMySubstring();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/

	}
	public static void runMySubstring() {
		Scanner read = new Scanner(System.in);
		System.out.print("What string would you like to use? ");
		String userString = read.nextLine();
		System.out.println();
		System.out.print("How many characters would you like to be in your substring? ");
		int subStrLength = read.nextInt();
		System.out.println("Your string is: " + userString);
		System.out.println("Your substring is: " + mySubstringMethod(userString, subStrLength));
	}//end runMySubstring method
	
	public static String mySubstringMethod(String str, int idx) {
		String subString = "";
		for(int i = 0; i < idx; i++) {
			subString = subString.concat(String.valueOf(str.charAt(i)));
		}
		return subString;
	}//end mySubstringMethod method

}//end class
