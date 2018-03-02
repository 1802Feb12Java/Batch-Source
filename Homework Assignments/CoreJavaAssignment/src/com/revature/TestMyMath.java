package com.revature;

import java.util.Scanner;

public class TestMyMath {
	/*
	 * The purpose of this class is to test the MyMath class.
	 */
	public static void main(String[] args) {
		
		runTestMyMathClass();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
	}//end main

	public static void runTestMyMathClass() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Choose two numbers.");
		System.out.println("Number 1: ");
		double a = userInput.nextDouble();
		System.out.println("Number 2: ");
		double b = userInput.nextDouble();
		MyMathClass userObject = new MyMathClass(a,b);
		double sum = userObject.addition();
		double difference = userObject.subtraction();
		double product = userObject.multiplication();
		double quotient = userObject.division();
		System.out.println("The sum of your two numbers is: " + sum);
		System.out.println("The difference between your two numbers is: " + difference);
		System.out.println("The product of your two numbers is: " + product);
		System.out.println("Your first number divided by your second number is: " + quotient);
				
	}//end testingMyMathClass method
	
}//end class
