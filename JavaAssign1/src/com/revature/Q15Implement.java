package com.revature;

public class Q15Implement implements Q15Interface{ //creates functionality for methods of interface

	@Override
	public void addition(int a, int b) {
		System.out.println(a+b);
	}

	@Override
	public void subtraction(int a, int b) {
		System.out.println(a-b);
	}

	@Override
	public void multiplication(int a, int b) {
		System.out.println(a*b);
	}

	@Override
	public void division(int a, int b) {
		System.out.println((double)a/(double)b);
	}

}
