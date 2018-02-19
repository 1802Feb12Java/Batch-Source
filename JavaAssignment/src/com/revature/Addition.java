/*  This is a class file for performing addition that extends to the abstract class Math Operation
 * 
 *  @author Dominic Nguyen
 */

package com.revature;

public class Addition extends MathOperation{

	private String name;
	private String type;
	
	public Addition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Addition(String name, String type) {
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
		System.out.println("The type of calculation is addition");
	}

	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		System.out.println("Calculating addition");
		
		int a = 128;
		int b = 256;
		int result = 0;
		
		result = a + b;
		
		System.out.println(a + " + " + b + " = " + result + "\n");
	}
}
