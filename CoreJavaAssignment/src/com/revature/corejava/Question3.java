package com.revature.corejava;

public class Question3 {
	public static String reverseString(String string) {
		char[] characterString = string.toCharArray();
		String result = "";
		int index = characterString.length-1;
		while (index > -1) {
			result = result + characterString[index];
			index--;
		}
		return result;
	}
}
