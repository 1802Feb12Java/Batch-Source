package com.revature.answers;

import java.util.Scanner;

public class Question17 {

	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		interest();
		
	}

	public static void interest() {
		double contents1;
		double contents2;
		double contents3;
		System.out.println("Please provide your principal balance.");
		contents1 = sc.nextDouble();									//grabs the input as a double
		System.out.println("Please provide your interest rate.");
		contents2 = sc.nextDouble();									//grabs the input as a double
		System.out.println("Please provide your timeframe in years.");
		contents3 = sc.nextDouble();									//grabs the input as a double
		System.out.println("Your interest rate is: $" + (contents1*contents2*contents3));
	}
	
}
