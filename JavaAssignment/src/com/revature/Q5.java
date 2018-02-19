package com.revature;

public class Q5 {

	//5. Write a substring method that accepts a string str and an integer idx and returns the
	// substring contained between 0 and idx-1 inclusive. Do NOT use any of the existing
	// substring methods in the String, StringBuilder, or StringBuffer APIs.
	public static String subString(String str, int idx)
	{
		String stri = ""; //creates empty string
		char[] charString = str.toCharArray();//convert string into array of characters
		for (int i = 0; i < idx; i++)
		{
			stri += charString[i];//fill in string with the limit input
		}
		return stri;

	}
}
