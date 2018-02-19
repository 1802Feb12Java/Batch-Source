package com.revature.corejavaassignment;

public class Q10 {
/*
 * Q10. Find the minimum of two numbers using ternary operators.
 */
	public static void answer() {
		System.out.println("Q10.\tFind the minimum of two numbers using ternary operators.\n");
		min(10, 20);
	}
	/*
	 * this method takes in 2 int values and returs the minimum using ternary operators
	 */
	public static void min(int a, int b) {
		System.out.println("Checking minimum value between " + a + " and "+ b);
		int c = a < b? a:b;
		System.out.println("The minimum value is " + c);
	}
}
