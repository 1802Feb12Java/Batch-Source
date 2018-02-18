package com.revature;

import java.util.Scanner;


public class Interest {
	
	//Calculates the interest using the formula p*r*t
	private static double calculate(double principal, double rate, double time) {
		return principal * rate * time;
	}
	
	//asking for user input
	public static void input() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Principal: ");
		double principal = sc.nextDouble();
		
		System.out.print("Enter Rate: ");
		double rate = sc.nextDouble();
		
		System.out.print("Enter time: ");
		double time = sc.nextDouble();
		
		//closing scanner
		sc.close();
		
		System.out.println("Interest: " + calculate(principal, rate, time));
	}
}
