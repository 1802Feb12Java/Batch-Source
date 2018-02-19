package com.revature;

public class Q3 {

	//3. Reverse a string without using a temporary variable. Do NOT use reverse() in the
	//StringBuffer or the StringBuilder APIs.
	
	public static String stringReverse(String str)
	{
		int strLength = str.length();//get string's length
		for (int i = 0; i < strLength- 1; i++)
		{ 
			//adding character to the string in reverse
			str += str.substring(strLength - 1 - i, strLength - i);
		}
		str += str.substring(0,1);//creates string with original + reversed string
		str = str.substring(strLength);// cut the original string to get reverse string
		return str;
	}
}
