package com.revature;

import java.util.Scanner;

public class Qu17 {
	
	public Qu17() {
		
		// Instantiate new Scanner object
		Scanner sc = new Scanner(System.in);
		
		// Take in user input for principal and store as int
		System.out.println("Enter principal amount:");
		int principal = sc.nextInt();
		
		// Take in user input for rate and store as double
		System.out.println("Enter rate interest rate as a percentage:");
		double rate = sc.nextDouble();
		
		// Take in user input for time in years and store as int
		System.out.println("Enter time in years:");
		int time = sc.nextInt();
		
		// Calculate simple interest and print to console
		double simpleInterest = principal*(rate/100)*time;
		System.out.println("Your simple interest is: $" + simpleInterest);
		
		// Close scanner
		sc.close();
	}

}
