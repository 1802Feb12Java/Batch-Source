package com.revature;

import java.util.Scanner;

public class InterestCalculator {
	/*
	 * The purpose of this class is to calculate simple interest
	 * based on user input.
	 */
	public static void main(String[] args) {
		
		runInterestCalculator();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
	}//end main

	public static void runInterestCalculator() {
		Scanner read = new Scanner(System.in);
		System.out.println("Please enter the principal value: ");
		double principal = read.nextDouble();
		System.out.println("Please enter the interest rate: ");
		double ir = read.nextDouble();
		System.out.println("Please enter the number of years: ");
		int years = read.nextInt();
		double interest = principal*ir*years;
		System.out.println("The amount of interest accrued: $" + interest);
	
	}//end runInterestCalculator method
	
	
}//end class
