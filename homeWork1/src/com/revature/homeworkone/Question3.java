package com.revature.homeworkone;

//Got tired of fancy names for classes. Just going to name class the question now.

public class Question3 {

	
	public static String getReverse(String str) {
		
		for (int i = 0; i < str.length() - i; i++) {
			
			str += str.substring(str.length() - 1 - 2*i ,str.length() - 2*i);
			
		}
		
		str = str.substring(str.length() / 2 );
		
		return str;
		
	}
	
}
