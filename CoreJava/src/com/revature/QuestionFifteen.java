package com.revature;

public class QuestionFifteen implements Q15Interface {

	// add a plus b
	@Override
	public int addition(int a, int b) {
		return a + b;
	}

	// subtract b from a
	@Override
	public int subtraction(int a, int b) {
		return a - b;
	}

	// multiply a times b
	@Override
	public int multiplication(int a, int b) {
		return a * b;
	}

	// divide a by b
	@Override
	public int division(int a, int b) {
		return a / b;
	}

}