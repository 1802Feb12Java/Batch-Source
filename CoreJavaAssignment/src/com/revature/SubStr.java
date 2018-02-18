package com.revature;

//Q5. Write a substring method that accepts a string str and
//	  an integer idx and returns the substring contained 
//	  between 0 and idx-1 inclusive.  Do NOT use any of 
//	  the existing substring methods in the String, 
//	  StringBuilder, or StringBuffer APIs.

public class SubStr {
	
	
	public static void subStr(String str,int subI) {
		int strLen = str.length();
		//if the given index is larger than the len of the string, aborts
		if(subI >= strLen) {
			System.out.println("Index given is larger than length of String");
			return;
		}
		
		//print out each character up to the given index
		for(int i = 0; i <= subI; i++) {
			System.out.print(str.charAt(i));
		}
		System.out.println("");
	}
}
