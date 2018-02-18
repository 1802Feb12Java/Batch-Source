package com.revature;

import java.util.Arrays;

public class Q12 implements Runnable {
	private final int LOWER_BOUND, UPPER_BOUND;
	
	// Range: [lowerBound, upperBound]
	public Q12(int lowerBound, int upperBound) {
		LOWER_BOUND = lowerBound;
		UPPER_BOUND = upperBound;
	}
	
	@Override
	public void run() {
		System.out.println("Question 12: Print out Even numbers");
		int[] ary = new int[UPPER_BOUND-LOWER_BOUND+1];
		
		for(int i = ary.length; i-->0;) {
			ary[i] = LOWER_BOUND+i;
		}
		
		System.out.println("    Array: " + Arrays.toString(ary));
		System.out.print("    Even numbers: ");
		
		for(int n : ary)
			if((n & 1) == 0) System.out.print(n + " ");
		System.out.println();
	}

}
