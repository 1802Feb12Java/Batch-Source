package com.revature.corejava;

import java.util.Scanner;

/*Question 17: Write a program that calculates the simple interest on the principal, rate of interest 
and number of years provided by the user. Enter principal, rate and time through the console using the Scanner class.
Interest = Principal* Rate* Time*/

public class Q17CalculateInterest {
	public static void payMe() {
		double principal = 0.0;
		double rate = 0.0;
		int numberOfYears = 0;
		Scanner scanner = new Scanner(System.in);
		
		//get the input
		System.out.println("Enter the principal: ");
		principal = scanner.nextDouble();
		System.out.println("Enter the rate: ");
		rate = scanner.nextDouble();
		System.out.println("Enter the number of years: ");
		numberOfYears = scanner.nextInt();
		
		//print the output
		System.out.println("The total interest on " + principal + " at a rate of " + rate 
				+ " over " + numberOfYears + " is $" + (principal * rate * numberOfYears));
		
		//close the scanner object
		scanner.close();
	}
}
