package com.revature.homeworkfeb19;

//Q3. Reverse a string without using a temporary variable.  
//Do NOT use reverse() in the StringBuffer or the StringBuilder APIs.

public class Question03 {
	private String str = "string";
	{
		for (int i=0; i<str.length(); i++)
		{
			str = str.substring(1, str.length()-i) +str.substring(0, 1)+str.substring(str.length()-i, str.length());
		}
	}
	{
		System.out.println(str);
	}

}
