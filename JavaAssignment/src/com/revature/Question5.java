/* This is a class file for finding the substring of a given string
 * 
 * @author Dominic Nguyen
 */

package com.revature;

public class Question5 {
	
	// constructor
	public Question5() {
		
	}
	
	/* This method finds the substring of a given string
	 * @param str - string
	 * @param idx - index used for creating a substring
	 */
	public void SubString(String str, int idx) {
		char character;
		int i;
		
		// displays the substring
		System.out.print("Q5: Substring: ");
		for(i = 0; i < idx; i++) {
			 character = str.charAt(i);
			 System.out.print(character);
		}
		System.out.println();
	}
}
