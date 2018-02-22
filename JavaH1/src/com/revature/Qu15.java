package com.revature;

public class Qu15 implements CustomCalc {
	
	private int result;

	@Override
	public void addition(int num1, int num2) {
		result = num1 + num2;
		System.out.println("Q15: " + num1 + " + " + num2 + " = " + result);
	}

	@Override
	public void subtraction(int num1, int num2) {
		result = num1 - num2;
		System.out.println("Q15: " + num1 + " - " + num2 + " = " + result);
	}

	@Override
	public void multiplication(int num1, int num2) {
		result = num1 * num2;
		System.out.println("Q15: " + num1 + " x " + num2 + " = " + result);
	}

	@Override
	public void division(int num1, int num2) {
		result = num1 / num2;
		System.out.println("Q15: " + num1 + " / " + num2 + " = " + result);
	}
	
	

}
