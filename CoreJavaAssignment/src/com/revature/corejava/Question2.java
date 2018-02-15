package com.revature.corejava;

import java.util.Arrays;

public class Question2 {
	public static void displayFibonacciNumbers() {
		int firstIndex = 0, secondIndex = 1; // iterators for readability
		int[] fibonacciNumbers = new int[25]; //array to hold result
		fibonacciNumbers[0] = 0; //create first two base cases
		fibonacciNumbers[1] = 1;
		for (int i = 2; i < fibonacciNumbers.length; i++) {
			fibonacciNumbers[i] = fibonacciNumbers[firstIndex] + fibonacciNumbers[secondIndex];
			firstIndex++;
			secondIndex++;
		}
		System.out.println(Arrays.toString(fibonacciNumbers));
	}
}
