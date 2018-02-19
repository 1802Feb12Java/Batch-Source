package com.revature;

public class Reverse {
	
	public static void example() {
		System.out.println("Original String: abcdefg");
		System.out.println("New String: " + reverseString("abcdefg"));
	}
	
	public static String reverseString(String string) {
		
		//get original length of string
		int originalLength= string.length();
		
		//concat string backwards onto original string, backwards
		for(int i = string.length() - 1; i >= 0; i--) {
			string = string.concat(String.valueOf(string.charAt(i)));
		}
		
		//returns string from original string.length() , which should just be the reversed tring
		return (String) string.subSequence(originalLength, string.length());
		
	}

}
