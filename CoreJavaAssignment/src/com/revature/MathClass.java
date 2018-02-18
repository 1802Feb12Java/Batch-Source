package com.revature;

//Math class implementing the custom interface
public class MathClass implements CustomMath{
	
	//defining the addition method
	public double addition(double a, double b) {
		return a + b;
	}
	
	//defining the subtraction method
	public double subtraction(double a, double b) {
		return a - b;
	}
	
	//defining the multiplication method
	public double multiplication(double a, double b) {
		return a * b;
	}
	
	//defining the division method
	public double division(double a, double b) {
		return a / b;
	}
}
