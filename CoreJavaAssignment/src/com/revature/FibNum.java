package com.revature;

//Q2. Write a program to display the first 25 Fibonacci numbers beginning at 0.

public class FibNum {
	
	static private int first = 0;
	static private int second = 1;
	
	//print up to Kth fib numbers
	public static void printToK(int k) {
		//create a array 
		//***can be a problem if don't have enough space with a high value of K***
		int [] fibSeq = new int[k];
		//default first two in the array
		if(k > 1)
			fibSeq[0] = first;
		if(k > 2)
			fibSeq[1] = second;
		
		for(int i = 2; i < k; i++) {
			fibSeq[i] = fibSeq[i-1] + fibSeq[i-2];
		}
		System.out.println(java.util.Arrays.toString(fibSeq));
	}
}
