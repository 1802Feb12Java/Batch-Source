package com.revature.corejava;

/* Question 3: Reverse a string without using a temporary variable.  
 * Do NOT use reverse() in the StringBuffer or the StringBuilder APIs.
 */

public class Q3ReverseString {
	public static String reverseString(String a) {
		/* Using the substring() method of the String object, reverse the string via
		 * concatenation, writing the portion of the string that has already been
		 * reversed followed by the last character in the string, followed by the 
		 * yet-to-be-reversed section
		 */
		for (int index = 0; index < a.length(); index++) {
				/*write the portion of the string that has already been reversed
				 *in substring, the ending index (substring(beginIndex, endIndex)
				 *is exclusive, so the substring of (0,index) is an empty string
				 */
				a = a.substring(0, index) 						//add the reversed portion of the string
						+ a.substring(a.length() - 1) 			//add the last character in the string
						+ a.substring(index, a.length() - 1);   //add the non-reversed portion of the string
		}

		//return the reversed string
		return a;
	}
}
