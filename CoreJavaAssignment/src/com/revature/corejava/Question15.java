package com.revature.corejava;

public class Question15 implements ICalculation {
	Question15() {
		super();
	}
	
	@Override
	public int Add(int x, int y) {
		return x + y;
	}

	@Override
	public int Subtract(int x, int y) {
		return x - y;
	}

	@Override
	public int Multiply(int x, int y) {
		return x * y;
	}

	@Override
	public int Divide(int x, int y) {
		return x/y;
	}

}
