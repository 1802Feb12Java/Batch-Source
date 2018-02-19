/* This is a class file for finding the Fibonacci numbers
 * 
 * @author Dominic Nguyen
 */

package com.revature;

import java.util.Arrays;

public class Question2 {
	
	// constructor
	public Question2() {
		
	}
	
	/* This method finds the fibonacci numbers
	 * @param array - stores fibonacci numbers
	 */
	public void Fibonacci(int[] array) {
		int i = 0; // array index
		
		// get the fibonacci numbers
		for(i = 0; i < array.length; i++) {
			if(i == 0) {
				array[i] = 0;  // first index set to 0
			}
			else if(i == 1) {
				array[i] = 1;  // second index set to 1
			}
			else {
				array[i] = array[i - 1] + array[i - 2];  // add the 2 previous array values and store in current index of array
			}	
		}
		
		// display answer to question 2
		System.out.println("Q2: Fibonacci numbers: " + Arrays.toString(array));
	}
	
}
