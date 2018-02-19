/*  This is a class file for performing multiplication that extends to the abstract class Math Operation
 * 
 *  @author Dominic Nguyen
 */

package com.revature;

public class Multiplication extends MathOperation{
	
	private String name;
	private String type;
	
	public Multiplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Multiplication(String name, String type) {
		super();
		
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void typeOfCalculation(int type) {
		// TODO Auto-generated method stub
		System.out.println("The type of calculation is multiplication");
	}

	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		System.out.println("Calculating multiplication");
		
		int a = 1024;
		int b = 1024;
		int result = a * b;
		
		System.out.println(a + " * " + b + " = " + result + "\n");
	}
	
	
}
