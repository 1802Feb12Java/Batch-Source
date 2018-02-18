package com.revature.corejava;

public class Question3 {
	public static String reverseString(String string) {
		//loop will swap letters until entire string is reversed
		//credit to: stivlo @ 
		//https://stackoverflow.com/questions/7612396/reverse-string-in-java-without-using-any-temporary-string-char-or-string-builder
		for (int i = 0; i < string.length(); i++) {
			string = string.substring(1, string.length() - i)
		        + string.substring(0, 1)
		        + string.substring(string.length() - i, string.length());
		 }
		return string;
	}
}
