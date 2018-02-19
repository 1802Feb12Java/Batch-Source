/* This is a class file that determines whether or not a number is a prime number
 * 
 * @author Dominic Nguyen
 */

package com.revature;

public class Question9 {

	// constructor
	public Question9() {
		
	}
	
	/*  This method will determine whether or not a number is a prime number
	 *  @param maxNum - the max number for finding the prime numbers
	 */
	public void PrimeNumber(int maxNum) {
		int i = 0;
		int j = 0;
		int count = 0;
		
		System.out.print("Q9: Prime numbers: ");
		
		// Determines whether a number is a prime number
		for(i = 1; i <= maxNum; i++) {
			count = 0;
			for(j = 1; j < maxNum; j++) {
				if(i % j == 0) {
					count++;
				}
			}
			
			if(count == 2 && i < maxNum) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}
}
