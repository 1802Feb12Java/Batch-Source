package com.revature.corejava;

public class Question4 {
	public static int computeFactorial (int numberToCompute) {
		if (numberToCompute > 19) {
			System.out.println("Error! Numbers greater than 19 will cause integer overflow!");
			return -1;
		}
		int result = 1;
		for (int i = 1; i <= numberToCompute; i++) {
			result *= i;
		}
		return result;
	}
}
