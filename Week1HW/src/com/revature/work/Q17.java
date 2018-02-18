package com.revature.work;

import java.util.Scanner;

	public class Q17 {
	
		public static Scanner sc = new Scanner(System.in);

		public void loan() {
			// declaring variables
			int prin;
			double interest;
			int year;
			double sum;
			
			// Prompt for user to read and input their answers
			System.out.println("What is your loan principal?");
			prin = sc.nextInt();
			System.out.println("What is your rate of Interest? (4.5% is 0.045)");
			interest = sc.nextDouble();
			System.out.println("What is your loan term?");
			year = sc.nextInt();
			// simple interest only calculation
			sum = prin*interest*year;
			System.out.println("The simple interest for your loan is "+sum);
		}
		
}
