package com.revature;

import java.util.Scanner;

public class Q17 {
	//Interest = Principal* Rate* Time
	
	public static void run() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter your principal: ");
		double principal = scan.nextDouble();
		System.out.print("Enter your rate: ");
		double rate = scan.nextDouble();
		System.out.print("Enter the amount of years (whole number only, please): ");
		int years = scan.nextInt();
									//format the string to print to 2 decimal places because it's money
		System.out.println("Your interest is: $" + String.format("%.2f", principal*rate*years));
		
		scan.close();	//closing the scanner in classes before this closed it in other classes too apparently... pretty weird
	}
}
