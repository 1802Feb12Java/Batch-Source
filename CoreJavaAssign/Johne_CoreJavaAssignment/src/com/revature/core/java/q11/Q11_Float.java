package com.revature.core.java.q11;

/**
 * 
 * @author johne
 * Q11. Write a program that would access two float-variables 
 * from a class that exists in another package. Note, you will 
 * need to create two packages to demonstrate the solution.
 * 
 * use public access modifier so that it is available to all packages
 */
public class Q11_Float {
	
	public float num1;
	public float num2;
	
	public Q11_Float() {
		this.num1 = 32.123f;
		this.num2 = 123.4321f;
	}

	/**
	 * @return the num1
	 */
	public float getNum1() {
		return num1;
	}

	/**
	 * @param num1 the num1 to set
	 */
	public void setNum1(float num1) {
		this.num1 = num1;
	}

	/**
	 * @return the num2
	 */
	public float getNum2() {
		return num2;
	}

	/**
	 * @param num2 the num2 to set
	 */
	public void setNum2(float num2) {
		this.num2 = num2;
	}
	
}
