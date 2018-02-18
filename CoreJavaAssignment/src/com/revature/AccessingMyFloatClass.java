package com.revature;

public class AccessingMyFloatClass {
/*
 * The purpose of this class is to access two float variables that are
 * in a class in another package.
 */
	public static void main(String[] args) {
		
		runAccessingMyFloatClass(); 
		/* 
		 * this method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
	}//end main

	public static void runAccessingMyFloatClass() {
		float tau = com.revature2.MyFloatClass.getTau();
		float pi = com.revature2.MyFloatClass.getPi();
		//Because the packages reside in the same project, I only need
		//to specify the path within the context of the project.
		
		System.out.println("Some people favor tau (" + tau + "), some people favor pi (" + pi + ").");
		System.out.println("I don't have a preference, but both of these values were accessed from a class in a different project.");
		
	}//end runAccessingMyFloatClass method
}//end class
