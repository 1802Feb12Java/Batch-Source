package com.revature.core.java;

import java.util.Scanner;

/**
 * 
 * @author johne
 * Q17. Write a program that calculates the simple interest 
 * on the principal, rate of interest and number of years 
 * provided by the user. Enter principal, rate and time 
 * through the console using the Scanner class.
 * 		Interest = Principal* Rate* Time
 */

public class Q17_SimpleInterest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		/*
		 * Simple Interest = Principal * rate of interest * number of years
		 */
		System.out.print("Enter principal (Original balance): ");
		double principal = input.nextDouble();
		
		System.out.print("Enter rate of interest (Ex. entering 10 is 10 percent): ");
		double rate = input.nextDouble() / 100;
		
		System.out.print("Enter number of year(s): ");
		int numOfYear = input.nextInt();
		
		double simpleInterest = principal * (1 + rate * numOfYear);
		
		System.out.println("Ending balance added with Simple Interest is $" + simpleInterest);
	}
}
