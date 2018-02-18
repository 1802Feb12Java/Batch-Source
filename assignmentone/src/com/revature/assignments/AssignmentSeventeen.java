package com.revature.assignments;

import java.util.Scanner;

public class AssignmentSeventeen {
	/**
	 * Write a program that calculates the simple interest on the principal, rate of
	 * interest and number of years provided by the user. Enter principal, rate and
	 * time through the console using the Scanner class. Interest = Principal* Rate*
	 * Time
	 * 
	 */
	public static double calculateInterest() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Principal");
		double principle = sc.nextDouble();
		System.out.println("Enter Interest Rate");
		double interestRate = sc.nextDouble();
		System.out.println("Enter Time");
		double time = sc.nextDouble();

		double interest = principle * interestRate * time;
		System.out.println("Your total simple interest is (principal*rate*time) = " + interest);

		return interest;
	}

}
