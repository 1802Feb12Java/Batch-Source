package com.revature.answers;

public class Question15 implements Question15Interface {

	public void run() {
		mathFun(50, 10);
	}
	
	public void mathFun(int x, int y) {
		System.out.println("Using numbers " + x + " and " + y);
		System.out.println(addition(x,y));
		System.out.println(subtraction(x,y));
		System.out.println(multiplication(x,y));
		System.out.println(division(x,y));
	}

	@Override
	public int addition(int x, int y) {
		return x+y;
	}

	@Override
	public int subtraction(int x, int y) {
		return x-y;
	}

	@Override
	public int multiplication(int x, int y) {
		return x*y;
	}

	@Override
	public int division(int x, int y) {
		return x/y;
	}

	

}
