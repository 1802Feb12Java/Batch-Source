package com.revature.corejavaassignment;

public class Q2 {
/*
 * Q2. Write a program to display the first 25 Fibonacci numbers beginning at 0.
 */
	public static void answer() {
		System.out.println("\nQ2.\tWrite a program to display the first 25 Fibonacci numbers beginning at 0.\n");
		fib25();
	}
	/*
	 * This method prints the first 25 fibonacci numbers.
	 */
	public static void fib25() {
		int previous =0, current=1, fib;
		System.out.println("0: " + previous);
		System.out.println("1: " + current);
		for (int index =2; index <=25; index++) {
			fib=current+previous;
			previous=current;
			current=fib;
			System.out.println((index) +": " + current);
		}
	}
}
