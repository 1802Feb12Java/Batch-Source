package com.revature.corejavaassignment;

import java.util.Scanner;

public class Q17 {
/*Q17. Write a program that calculates the simple interest on the principal, 
 * rate of interest and number of years provided by the user. Enter principal, 
 * rate and time through the console using the Scanner class.
 * 
 * Interest = Principal* Rate* Time
 */
	public static void answer() {
		System.out.println("Q17. Write a program that calculates the simple interest on the principal, \r\n" + 
				" * rate of interest and number of years provided by the user. Enter principal, \r\n" + 
				" * rate and time through the console using the Scanner class.\r\n" + 
				" * \r\n" + 
				" * Interest = Principal* Rate* Time");
		simpleInterest();
	}
	/*
	 * this method calculates simple interest and takes its arguments as user input
	 * Simple interest is calculated as Interest = Principal* Rate* Time
	 */
	public static void simpleInterest() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the number of years");
		double time =  Double.parseDouble(in.nextLine());
		System.out.println("Please enter the principal");
		double principal = Double.parseDouble(in.nextLine());
		System.out.println("Please enter the interest rate");
		double interestRate = Double.parseDouble(in.nextLine());
		in.close();
		System.out.println("The interest accrued in " + time + " years at " + interestRate + " interest rate on the balance of\n$" + 
					principal + " is " + (principal*interestRate*time) + "\n");
	}

}
