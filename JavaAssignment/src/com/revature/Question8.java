/* This class file will store strings in 2 separate array lists
 * 
 * @author Dominic Nguyen
 */

package com.revature;

import java.util.ArrayList;

public class Question8 {

	// constructor
	public Question8() {
		
	}
	
	/* This method stores numbers in 2 separate ArrayLists
	 * @param str - array of strings
	 * @param strCount - number of string elements
	 */
	public void ArrayStorage(String[] str, int strCount) {
		int i = 0;
		int j = 0;
		String[] str2 = new String[strCount];
		ArrayList<String> array = new ArrayList<String>();
		ArrayList<String> palindrome = new ArrayList<String>();
		
		System.out.print("Q8: Palindromes: ");
		//System.out.println("string length: " + str[i].length());
		
		for(i = 0; i < strCount; i++) {
			str2[i] = "";
			
			// reverses string
			for(j = str[i].length() - 1; j >= 0; j--) {
				str2[i] = str2[i] + str[i].charAt(j); 
			}
			
			//System.out.println("str: " + str[i] + ", str2: " + str2[i]);
			
			// checks if string is equal to the reversed string
			if((str[i].toString()).equals(str2[i].toString())) {
				palindrome.add(str[i]);
				System.out.print(str[i] + " ");
			}
			else {
				array.add(str[i]);
			}
		}
		System.out.println();
	}
}
