package com.revature;

import java.util.Scanner;

public class QuestionSeventeen {

	// calculate simple interest using provided formula
	public double calculateInterest(double principal, double rate, double time) {
		return principal * rate * time;
	}

	public void printCalculatedInterest() {
		double principal, rate, time;

		// asks for input
		Scanner s = new Scanner(System.in);
		System.out.print("Enter principal:\t");
		principal = s.nextDouble();

		System.out.print("\nEnter rate:\t");
		rate = s.nextDouble() * 0.01;

		System.out.print("\nEnter time:\t");
		time = s.nextDouble();

		s.close();

		// prints simple interest
		System.out.println("\nSimple interest earned = " + calculateInterest(principal, rate, time));
	}

}