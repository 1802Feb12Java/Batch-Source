package com.revature;

public class Q4 {
	public void factorial(int n) {
		int n_factorial = 1;
		for(int i = 2; i<=n; i++) {
			n_factorial *= i; //multiplies itself with next number which gives factorial
		}
		System.out.println("The factorial of " + n + " is " + n_factorial);
	}
}
