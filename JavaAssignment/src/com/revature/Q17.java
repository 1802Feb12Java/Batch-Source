package com.revature;

import java.util.Scanner;

public class Q17 {
	/*
	 * Write a program that calculates the simple interest on the principal, rate of interest
and number of years provided by the user. Enter principal, rate and time through the
console using the Scanner class.
Interest = Principal* Rate* Time
	 */

	
	//calculate interest given input
	public static void interestCalculator(float principal, float rate, float years)
	{
		float interest = principal * rate * years;

		System.out.println("Interest: " +interest);
	}
	//print interest and get user input
	public static void printInterest()
	{
		float principle;
		float rate;
		float years;
		//scanner input
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter principle amount: ");
		principle = scanner.nextFloat();
		System.out.print("Enter interest rate: ");
		rate = scanner.nextFloat();
		System.out.print("Enter amount of years: ");
		years = scanner.nextFloat();
		interestCalculator(principle,rate,years);
		scanner.close(); //close scanner
	}
}
