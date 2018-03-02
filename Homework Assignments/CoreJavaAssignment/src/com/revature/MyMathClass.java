package com.revature;

public class MyMathClass implements MathInterface{
	/*
	 * The purpose of this class is to demonstrate implementing 
	 * an interface.
	 */
	private double a;
	private double b;
	
	
	
	public MyMathClass(double a, double b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public double addition() {
		return this.a+this.b;
	}

	@Override
	public double subtraction() {
		return this.a-this.b;
	}

	@Override
	public double multiplication() {
		return this.a*this.b;
	}

	@Override
	public double division() {
		if(b != 0) {
			return this.a/this.b;
		} else {
			System.out.println("Invalid input, return value will be 0.");
			return 0;
		}
		
	}

	public double getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "MyMathClass [a=" + a + ", b=" + b + "]";
	}
	
}//end class
