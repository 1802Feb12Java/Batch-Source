/* This is a class for calculating the factorial of a given number
 * 
 * @author Dominic Nguyen
 */

package com.revature;

public class Question4 {

	// constructor
	public Question4() {
		
	}
	
	/*  This method calculates the factorial of a given number
	 *  @param number - number used for calculating the factorial
	 */
	public void Factorial(int number) {
		int i = 0;  // index number
		int maxNum = number;  // max number for factorial
		int factorialNum = number;
		
		// calculate the N factorial by decrementing the index and multiplying with the 
		for(i = maxNum; i > 0; i--) {
			if(i > 1) {
				factorialNum = factorialNum * (i - 1);
			}
		}
		
		System.out.println("Q4: Factorial of " + number + " is " + factorialNum);
	}
}
