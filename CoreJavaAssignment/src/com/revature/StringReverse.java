package com.revature;

public class StringReverse {

	public static void main(String[] args) {
		String myString = "hello world";
		System.out.println(reverseString(myString));

	}
	
	public static String reverseString(String s) {
		String newString = "";
		for(int i = (s.length()-1); i >= 0; i--) {
			newString = newString.concat(String.valueOf(s.charAt(i)));
		}
		return newString;
	}

}
