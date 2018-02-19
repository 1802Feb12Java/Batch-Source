/* This is a class file for determining if a number is even
 * 
 * @author Dominic Nguyen
 */

package com.revature;

public class Question6 {

	// constructor
	public Question6() {
		
	}
	
	/* This method will determine if the number is even or odd
	 * @param number - number
	 */
	public void IsEven (int number) {
		float num = number;
		
		num = num / 2;  // divide num by 2
		
		// displays the number is even or odd
		if(num - (int)num == 0) {
			System.out.println("Q6: The number " + number + " is even");
		}
		else {
			System.out.println("Q6: The number " + number+ " is odd");
		}
	}
}
