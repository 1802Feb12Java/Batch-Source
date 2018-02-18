package com.revature;

public class Substring5 {

	public static String sub(String str, int idx) {
		
		if(idx > str.length() || idx < 2) {		// invalid user inputs
			
			return "Invalid input.";
		}
		
		char[] store = str.toCharArray();	// convert string to char in order to parse
		char[] cut = new char[idx];
		int k = 0;
		
		while(k < idx) {	// create a string by appending chars from str, till desired stop point
			
			cut[k] = store[k];
			k++;
		}
		
		String word = new String(cut);	// convert char array to string for output
		return word;
	}
}