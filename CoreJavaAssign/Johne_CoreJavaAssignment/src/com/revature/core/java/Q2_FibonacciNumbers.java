package com.revature.core.java;

import java.util.Arrays;

/**
 * 
 * @author Johne Vang
 * Q2 Fibonacci numbers 
 * Write a program to display the first 25 Fibonacci numbers beginning at 0.
 * 
 */
public class Q2_FibonacciNumbers {

	public static void main(String[] args) {
		
		/*
		 * initialize an array and set index zero to be zero and index 1 to be 1
		 */
		int[] FibonacciArray = new int[25];	//size of loop
		FibonacciArray[0] = 0;
		FibonacciArray[1] = 1;
		
		/*
		 * The current number is found adding two numbers before the current number
		 */
		for(int i = 2; i < FibonacciArray.length; i++) {
			FibonacciArray[i] = FibonacciArray[i - 1] + FibonacciArray[i - 2];
		}
		
		//display array elements
		System.out.println(Arrays.toString(FibonacciArray));
		
	}	//end of main
}	//end of class
