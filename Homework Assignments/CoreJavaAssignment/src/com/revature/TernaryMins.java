package com.revature;

import java.util.Scanner;

public class TernaryMins {
	/*
	 * The purpose of this class is to demonstrate how to use a ternary
	 * operator to do a simple comparison.
	 */
	public static void main(String[] args) {
		
		runTernaryMins();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
	}//end main
	
	public static void runTernaryMins() {
		Scanner read = new Scanner(System.in);
		System.out.print("Please type an integer: ");
		int a = read.nextInt();
		System.out.print("Please type another integer: ");
		int b = read.nextInt();
		int min = findMin(a,b);
		System.out.println("The minimum of " + a + " and " + b + " is " + min);

	}//end runTernaryMins method
	
	public static int findMin(int a, int b) {
		int minimum = (a < b) ? a:b;
		return minimum;
	}//end findMin method

}//end class
