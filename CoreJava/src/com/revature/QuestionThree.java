package com.revature;

public class QuestionThree {
	public String reverseString(String reverseThis) {

		// add reversed string to end of parameter
		for (int i = reverseThis.length() - 1; i >= 0; i--) {
			reverseThis = reverseThis.concat(Character.toString(reverseThis.charAt(i)));
		}

		// take 2nd half of string
		reverseThis = reverseThis.substring(reverseThis.length() / 2);

		return reverseThis;
	}
}