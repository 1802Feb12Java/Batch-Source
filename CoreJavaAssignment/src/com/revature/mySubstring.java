package com.revature;

public class mySubstring {

	public static void main(String[] args) {
		
		String myString = "Hello World, Goodbye Earth";
		System.out.println(mySubstringMethod(myString, 11));
		

	}
	
	public static String mySubstringMethod(String str, int idx) {
		String subString = "";
		for(int i = 0; i < idx; i++) {
			subString = subString.concat(String.valueOf(str.charAt(i)));
		}
		return subString;
	}

}
