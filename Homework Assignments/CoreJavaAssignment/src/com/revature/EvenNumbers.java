package com.revature;

import java.util.Scanner;

public class EvenNumbers {
	/*
	 * The purpose of this class is to determine whether an integer is even or odd.
	 */

	public static void main(String[] args) {
		
		int evenNum = 4;
		int oddNum = 7;
		
		System.out.println("evenNum is even: " + isEven(evenNum));
		System.out.println("oddNum is even: " + isEven(oddNum));
		
		runEvenNumbers();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/

	}
	
	public static void runEvenNumbers() {
		Scanner read = new Scanner(System.in);
		System.out.print("Please enter an integer: ");
		int userInt = read.nextInt();
		if(isEven(userInt)) {
			System.out.println("Your number is even.");
		} else {
			System.out.println("Your number is odd.");
		}
	}//end runEvenNumbers method
	
	
	public static Boolean isEven(int n) {
		//This method is used to determine whether an integer is even or not
		int newInt =  n / 2;
		newInt = newInt * 2;
		
		if(newInt == n) {
			return true;
		} else {
			return false;
		}
		
	}//end isEven method

}//end class
