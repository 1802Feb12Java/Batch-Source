package com.revature.assignments;

/**
 * Assignment 2: Write a program to display the first 25 Fibonacci numbers
 * beginning at 0
 */
public class AssignmentTwo {

	/**
	 * Returns the xth number in the Fibonacci sequence
	 * 
	 * @param x
	 * @return
	 */
	public static int fibonacci(int x) {
		if (x == 0 || x == 1)
			return x;
		else {
			return fibonacci(x - 1) + fibonacci(x - 2);
		}

	}

	/**
	 * Prints out the the first xth numbers starting at 0 of the Fibonacci Sequence
	 * 
	 * @param x
	 */
	public static void printFibonacci(int x) {
		for (int i = 0; i <= x; i++)
			System.out.println(fibonacci(i));
	}

}
