package com.revature.corejava;

//Question 4: Write a program to compute N factorial.

public class Q4Factorial {
	public static int factorial(int n) {
		//as this is a recursive function, check for the base case of 1
		if (n == 1) {
			//if the base case has been reached, return 1, as factorial 1 = 1
			return 1;
		}
		
		//if the base case has not been reached, call factorial(n-1) and return the result
		else {
			return n * factorial(n - 1);	
		}			
	}
}
