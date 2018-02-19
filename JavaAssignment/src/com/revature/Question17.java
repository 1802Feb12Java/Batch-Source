/* This is a class file for calculating the simple interest
 * 
 * @author Dominic Nguyen
 */

package com.revature;

import java.util.Scanner;

public class Question17 {

	// constructor
	public Question17() {
		
	}
	
	public static Scanner scan = new Scanner(System.in);
	
	/*  This is a method for calculating the simple interest
	 *  @param args - command line argument of type string
	 */
	public void simpleInterest(String[] args) {
		double principal = 0;
		double rate = 0;
		double time = 0;
		double interest = 0;
		
		System.out.println("Q17: Simple Interest Calculation");
		System.out.println("Enter the principal amount: ");
		
		// parses the string arguments to type double
		args[1] = scan.nextLine();
		principal = Double.parseDouble(args[1]);
		//System.out.println(principal);
		//scan.nextLine();
		
		System.out.println("Enter the rate: ");
		args[2] = scan.nextLine();
		rate = Double.parseDouble(args[2]);
		//System.out.println(rate);
		//scan.nextLine();
		
		System.out.println("Enter the time in years: ");
		args[3] = scan.nextLine();
		time = Double.parseDouble(args[3]);
		//System.out.println(time);
		//scan.nextLine();
		
		// calculates the simple interest
		interest = principal * (rate / 100) * time;
		
		// displays the simple interest
		System.out.println("The simple interest rate is " + interest);
		
	}
}
