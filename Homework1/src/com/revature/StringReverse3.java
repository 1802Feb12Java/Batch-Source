package com.revature;

public class StringReverse3 {
	
	public static String reverse(String workWithThis) {

		 if ((workWithThis == null) || (workWithThis.length() <= 1)) {	// invalid or 1 letter strings return itself
			 
	            return workWithThis;
		 }
		 
	        return reverse(workWithThis.substring(1)) + workWithThis.charAt(0);	// recursively send in word without the last char
	        																	// appending it to the end
	}
}