package com.revature;

class MathUtils {
	public static long factorial(long n) {
		long fact = n;
		
		// Test for 0!
		if(n == 0) return 1;
		
		// Calculate factorial.
		for(;n-->1;) fact *= n;		
		
		return fact;
	}
}

public class Q4 implements Runnable {
	public final int N;
	
	public Q4(int n) {
		N = n;
	}
	
	@Override
	public void run() {
		System.out.println("Question 4: Factorial");
		System.out.println("    " + N + "! = " + MathUtils.factorial(N));
	}

}
