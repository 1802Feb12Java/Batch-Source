package com.revature;

import java.util.Scanner; //one class to be used for inputs

public class Q17 {

	public void calcInterest() { //simple multiplication with User Inputs
		double principle;
		double rate;
		double time;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Principle: ");
		principle = sc.nextDouble(); //assigns user input to variable
		System.out.print("Enter Rate: ");
		rate = sc.nextDouble();
		System.out.print("Enter Time(years): ");
		time = sc.nextDouble();
		
		System.out.println("\nThe Interest is: " + principle*rate*time);
		sc.close(); //close for "safety"
	}
}
