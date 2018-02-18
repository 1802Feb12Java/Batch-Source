package com.revature;

//Q3. Reverse a string without using a temporary variable.  
//    Do NOT use reverse() in the StringBuffer or the StringBuilder APIs.

public class StringReverse {
	
	//reverse a string w/o using reverse()
	public static void rev(String strToRev) {
		int strLen = strToRev.length() - 1;
		StringBuilder revString = new StringBuilder("");
		
		System.out.println("BEFORE: " + strToRev.toString());
		//read the sting backwards and using a string builder to build a new reversed string
		for(int i = strLen; i >= 0; i--) {
			revString.append(strToRev.charAt(i));
		}
		System.out.println("AFTER:  " + revString.toString());
	}
	
}
