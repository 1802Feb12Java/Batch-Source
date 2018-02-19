/*  This is a class file for finding the minimum number of 2 numbers
 * 
 * @author Dominic Nguyen
 */

package com.revature;

public class Question10 {

	// constructor
	public Question10() {
		
	}
	
	/* This is a method for finding the minimum number of 2 numbers
	 * @param number1 - the first number
	 * @param number2 - the second number
	 */
	public void MinimumNumber(int number1, int number2) {
		int min = number2;
		
		min = number1 < number2 ? number1 : number2;
		
		System.out.println("Q10: The minimum number is " + min);
	}
}