package com.revature;

public class Q15 implements Q15Interface{
	/*
	 *15.  Write a program that defines an interface having the following methods: addition,
subtraction, multiplication, and division. Create a class that implements this interface
and provides appropriate functionality to carry out the required operations. Hard code
two operands in a test class having a main method that calls the implementing class.
	 */

	@Override
	public int addition(int a, int b) {
		return(a + b);
		
	}

	@Override
	public int substraction(int a, int b) {
		return(a - b);
		
	}

	@Override
	public int multiplication(int a, int b) {
		return(a * b);
		
	}

	@Override
	public int division(int a, int b) {
		return(a / b);
	}
}
