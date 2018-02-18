package com.revature.corejava;

public class Question3 {
	public static String reverseString(String string) {
	    int length = string.length(); //save original length of string
	    for (int i = (length - 1); i >= 0; --i) { //loop through string starting at back
	    	string += string.charAt(i); //append each char to the back of string
	    }
	    //original string is now: [originalString] + [reversedString]
	    string = string.substring(length); //strip off original string only leaving reversed version

		return string;
	}
}
