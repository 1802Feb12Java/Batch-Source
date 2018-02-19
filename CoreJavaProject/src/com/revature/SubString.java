package com.revature;

public class SubString {
	
	//example of the getSubString method
	public static void example() {
		System.out.println("Original String: abcdefghijklmnop");
		System.out.println("Input: getSubString(abcdefghijklmnop, 5)");
		System.out.println("Output: " + getSubString("abcdefghijklmnop", 5));
	}
	
	public static String getSubString(String string, int index) {
		//instantiate empty StringBuilder object
		StringBuilder newString = new StringBuilder("");
		
		//loop through specified index
		for(int i = 0; i < index; i++) {
			//append specified portion of the string onto newString
			newString.append(string.charAt(i));
		}
		
		return newString.toString();
	}
}
