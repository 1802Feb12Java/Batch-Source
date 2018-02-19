package com.revature.corejavaassignment;
import com.revature.corejavaassignmentq11.FloatVars;;
public class Q11 {
/*
 * Q11. Write a program that would access two float-variables from a class that exists 
 * in another package. Note, you will need to create two packages to demonstrate the solution.
 */ 
	public static void answer() {
		System.out.println("Q11.\tWrite a program that would access two float-variables from a class that exists \r\n" + 
				"\tin another package. Note, you will need to create two packages to demonstrate the solution.\n");
		getFloats();
	}
	/*
	 * This method prints 2 float values from another package
	 * the float values are static 
	 */
	public static void getFloats() {
		System.out.println("The floats in package 'com.revature.corejavaassignmentq11' are: ");
		System.out.println(FloatVars.getFloat1());
		System.out.println(FloatVars.getFloat2());
		
	}
	
}
