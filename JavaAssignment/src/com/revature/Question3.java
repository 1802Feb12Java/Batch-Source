/* This is a class file for reversing a string
 * 
 * @author Dominic Nguyen
 */

package com.revature;

public class Question3 {

	// constructor
	public Question3() {
		
	}
	
	/*  This method reverses the string
	 *  @param str - string that will be reversed
	 */
	public void ReverseString(String str) {
		int i = 0;
		char[] charArray = str.toCharArray();
		
		// display string in reversed order
		System.out.print("Q3: Reversed string: ");
		for(i = charArray.length - 1; i >= 0; i--) {
			System.out.print(charArray[i]);
		}
		System.out.println();
	}
}
