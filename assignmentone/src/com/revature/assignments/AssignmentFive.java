package com.revature.assignments;

public class AssignmentFive {

	/**
	 * Write a substring method that accepts a string str and an integer idx and
	 * returns the substring contained between 0 and idx-1 inclusive. Do NOT use any
	 * of the existing substring methods in the String, StringBuilder, or
	 * StringBuffer APIs.
	 */
	public static String subString(String str, int idx) {
		String ret = "";
		for (int i = 0; i < idx; i++) {
			ret += str.charAt(i);
		}
		return ret;
	}

}
