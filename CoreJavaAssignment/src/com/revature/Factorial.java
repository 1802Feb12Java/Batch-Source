package com.revature;

import java.util.Scanner;

public class Factorial {
	/*
	 * The purpose of this class is to compute n! and print the result to the console.
	 */

	public static void main(String[] args) {
		
		nFactorial(1);
		nFactorial(5);
		
		runFactorial();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/

	}
	
	public static void runFactorial() {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Please enter an integer less than 20: ");
		int n = userInput.nextInt();
		Factorial.nFactorial(n);
		
	}//end runFactorial method
	
	public static void nFactorial(int n) {
		if( n == 0) {
			System.out.println(0 + "! = " + 1);
			//0! is 1. This is a special case.
		} else {
			int result = 1;
			for(int i = 1; i <= n; i++) {
				//multiplies all numbers from 1 to n
				result = result * i;
			}
			System.out.println(n + "! = " + result);
			//makes the result look nice on the console
		}
	}//end nFactorial method

}//end class
