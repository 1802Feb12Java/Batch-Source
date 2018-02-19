/*  This is a class file that uses an enhanced for loop for finding and printing the even numbers
 * 
 *  @author Dominic Nguyen
 */

package com.revature;

public class Question12 {

	// constructor
	public Question12() {
		
	}
	
	/* This method uses an enhanced for loop to find and print the even numbers
	 * 
	 */
	public void EnhancedForLoop() {
		int i = 0;
		int array[] = new int[100];
		
		System.out.print("Q12: Even numbers: ");
		
		// Stores 100 numbers in an array
		for(i = 0; i < 100; i++) {
			array[i] = i + 1;
		}
		
		// Determines if a number is even
		for(int j : array) {
			if(j % 2 == 0) {
				System.out.print(j + " ");
			}
		}
		System.out.println();
	}
}
