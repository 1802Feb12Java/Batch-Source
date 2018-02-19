package com.revature.corejavaassignment;

public class Q5 {
/*
 * Q5. Write a substring method that accepts a string str and an integer idx and 
 * returns the substring contained between 0 and idx-1 inclusive.  
 * Do NOT use any of the existing substring methods in the String, StringBuilder, 
 * or StringBuffer APIs.
 */
	public static void answer() {
		System.out.println("Q5.\tWrite a substring method that accepts a string str and an integer idx and \r\n" + 
				"\treturns the substring contained between 0 and idx-1 inclusive.  \r\n" + 
				"\tDo NOT use any of the existing substring methods in the String, StringBuilder, \r\n" + 
				"\tor StringBuffer APIs.\n");
		stringSub("Roll Tide!:)", 6);
	}
	/*
	 * This method takes a string and an index and returns the substring with index denoting
	 * the end of the substring
	 */
	public static void stringSub(String string, int index) {
		if (index>string.length() || index <0) {
			System.out.println("index must be less than string length and must be greater than 0");
			return;
		}
		char[] chars= new char[index];
		string.getChars(0,  index, chars, 0);
		System.out.println(chars);
		System.out.println("\n");
	}
}
