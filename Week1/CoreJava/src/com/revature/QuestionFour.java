package com.revature;

public class QuestionFour {

	// calculates n factorial for given value n
	public int nFactorial(int n) {
		int finalValue = 1;

		for (int i = 1; i <= n; i++) {
			finalValue *= i;
		}

		return finalValue;
	}

}