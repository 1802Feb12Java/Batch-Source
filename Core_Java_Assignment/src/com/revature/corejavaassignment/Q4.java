package com.revature.corejavaassignment;

public class Q4 {
/*
 * Q4. Write a program to compute N factorial.
 */
	public static void answer() {
		System.out.println("\nQ4.\tWrite a program to compute N factorial.\n");
		factorial(10);
	}
	/*
	 * this method takes an int and computes its factorial
	 */
	public static void factorial(int n) {
		int product =1;
		for(int i=n; i>0; i--) {
			product *=i;
		}
		if (n==0)
			System.out.println(n + " Factorial is " + 0+ "\n");
		else
			System.out.println(n + " Factorial is " + product+ "\n");
	}
}
