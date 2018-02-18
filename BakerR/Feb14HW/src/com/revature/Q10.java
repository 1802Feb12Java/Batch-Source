package com.revature;

public class Q10<T extends Number & Comparable<T>> implements Runnable {
	
	private final T a, b;
	
	public Q10(T a, T b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public void run() {
		System.out.println("Question 10: Find minimum of two numbers");
		System.out.print("    min(" + a.toString() + ", " + b.toString() + "): ");
		
		System.out.println(a.compareTo(b) < 0 ? a : b);
	}

}
