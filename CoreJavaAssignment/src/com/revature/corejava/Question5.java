package com.revature.corejava;

public class Question5 {
	
	public static String getSubstring(String string, int index) {
		String result = "";
		if (index > string.length()) {
			return "Error! Index is greater than string length.";
		}
		for(int i = 0; i<index; i++) {
			result = result + string.charAt(i);
		}
		return result;
	}
}
