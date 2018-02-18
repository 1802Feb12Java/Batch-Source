package com.revature.corejava;

//Q2. Write a program to display the first 25 Fibonacci numbers beginning at 0.
public class Q2Fibonacci {
	public static int [] generateFibonacciSequence(int n) {
		int [] fibArray = new int[n];  //array to hold the Fibonacci sequence
		
		//assign f0 and f1, as the sequence needs f(n-1) and f(n-2) to begin calculation
		fibArray[0] = 0;
		fibArray[1] = 1;
		
		//generate the rest of the sequence for n elements, beginning at the third 
		//element in the sequence
		for (int index = 2; index < n; index++) {
			fibArray[index] = fibArray[index-1] + fibArray[index-2];
		}
		
		//return the array
		return fibArray;
	}
}
