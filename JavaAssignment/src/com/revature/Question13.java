/*  This is a class file for printing a triangle of 1's and 0's
 * 
 * @author Dominic Nguyen
 */

package com.revature;

public class Question13 {
	
	// constructor
	public Question13() {
		
	}
	
	/*  This method will print a triangle of 1's and 0's
	 * 
	 */
	public void Triangle() {
		int count = 1;
		int i = 1;
		
		System.out.print("Q13: ");
		
		// displays the triangle of 1's and 0's
		// the 0's are odd numbers and the 1's are even numbers
		while(i <= 10) {
			for(int j = 1; j <= 4; j++) {
				if(i > 1) {
					System.out.print("     ");
				}
				
				// prints the rows of 1's and 0's
				while(count <= j) {
					if(i % 2 == 0) {
						System.out.print(1 + " ");
					}
					else {
						System.out.print(0 + " ");
					}
					i++;
					count++;
				}
				System.out.println();
				count = 1;
			}
		}
	}
}
