package com.revature;

public class Q15 implements Q15b{
	@Override
	public double add(double a, double b) {	//override all the methods in the interface with expected functionality
		return a+b;
	}

	@Override
	public double sub(double a, double b) {
		return a-b;
	}

	@Override
	public double mult(double a, double b) {
		return a*b;
	}

	@Override
	public double div(double a, double b) {
		return a/b;
	}
}
