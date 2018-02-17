package com.revature.homeworkone;


public class Question5 {
	
	public static String subStringQ(String str, int index) {
		
		char[] arrStr = new char[index] ;
				
		str.getChars(0, index, arrStr, 0);
		str = "";
		for(char s: arrStr) {
			str += s;
		}
		
		return str;
	}
	
	
}
