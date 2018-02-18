package com.revature;

class Math2 {
	public static <T extends Number> boolean isEven(T n) {
		return (n.longValue() & 1) == 0;
	}
}


public class Q6 implements Runnable {
	private final int N;
	
	public Q6(int n) {
		N = n;
	}
	
	@Override
	public void run() {
		System.out.println("Question 6: Detect even number");
		System.out.println(N + " is even: " + Math2.isEven(N));

	}

}
