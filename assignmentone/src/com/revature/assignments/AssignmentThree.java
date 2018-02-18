package com.revature.assignments;

public class AssignmentThree {

	// Reverse a string without using a temporary variable. Do NOT use reverse() in
	// the StringBuffer or the StringBuilder APIs.
	public static String reverse(String s) {
		String rev = "";
		for (int i = s.length() - 1; i >= 0; i--)
			rev += s.charAt(i);
		return rev;
	}

}
