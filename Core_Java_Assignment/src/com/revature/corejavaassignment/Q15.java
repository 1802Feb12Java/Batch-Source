package com.revature.corejavaassignment;

public class Q15 {
/*
 * Q15. Write a program that defines an interface having the following methods: addition, 
 * subtraction, multiplication, and division.  Create a class that implements this 
 * interface and provides appropriate functionality to carry out the required operations. 
 * Hard code two operands in a test class having a main method that calls the implementing class.
 */
	public static void answer() {
		System.out.println("Q15. Write a program that defines an interface having the following methods: addition, \r\n" + 
				" * subtraction, multiplication, and division.  Create a class that implements this \r\n" + 
				" * interface and provides appropriate functionality to carry out the required operations. \r\n" + 
				" * Hard code two operands in a test class having a main method that calls the implementing class.");
		testInterface();
	}
	/*
	 * This method creates a MathLove class object then invokes one of its four 
	 * arithmetic methods
	 * MathLove has addition, multiplication, subtraction, and division methods
	 * defined in the interface MathLike
	 */
	public static void testInterface() {
		mathLove calc = new mathLove();
		double a = 10, b=5;
		System.out.println("The two operands for calculations are " + a + " and " + b);
		System.out.println("Addition " + calc.addition(a,b));
		System.out.println("Subtraction " + calc.subtraction(a,b));
		System.out.println("Mutliplication " + calc.multiplication(a, b));
		System.out.println("Division " + calc.division(a, b));
	}
}
interface mathLike{
	public double addition(double a, double b);
	public double subtraction(double a, double b);
	public double multiplication(double a, double b);
	public double division(double a, double b);
}
class mathLove implements mathLike{

	@Override
	public double addition(double a, double b) {
		return a+b;
	}
	@Override
	public double subtraction(double a, double b) {
		return a-b;
	}
	@Override
	public double multiplication(double a, double b) {
		return a*b;
	}
	@Override
	public double division(double a, double b) {
		if(b==0) {	return 0;	}
		else {	return a/b;	}
	}
	
}