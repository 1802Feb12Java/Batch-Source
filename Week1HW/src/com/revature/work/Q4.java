package com.revature.work;

public class Q4 {

	int total = 1;
	// multiplies by itself each iteration till it reaches the num passed to create its factorial
	public void factorial (int num) {
		for (int x = 1; x <= num; x++) {
			total = total * x;
		}
		
		System.out.println("The factorial of "+num+" is "+total);
	}
	
}
