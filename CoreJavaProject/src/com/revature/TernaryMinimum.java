package com.revature;

public class TernaryMinimum {
	
	//example of my getMinimum function
	public static void example() {
		
		System.out.println("The minimum of 3 and 5 is: ");
		System.out.println(getMinimum(3, 5));
		
	}
	
	public static int getMinimum(int num1, int num2) {
		//(argument) ? true: false;
		return (num1 < num2) ? num1: num2;
	}	
}
