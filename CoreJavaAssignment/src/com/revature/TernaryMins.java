package com.revature;

public class TernaryMins {

	public static void main(String[] args) {
		
		int a = 2;
		int b = 5;
		
		int min = findMin(a,b);
		System.out.println("The minimum of " + a + " and " + b + " is " + min);

	}
	
	public static int findMin(int a, int b) {
		int minimum = (a < b) ? a:b;
		return minimum;
	}

}
