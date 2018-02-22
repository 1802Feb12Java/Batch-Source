package com.revature;

public class Q3 {
	
	public Q3(String str) {
		
		// get the length of the string and store as an int variable
		int len = str.length();
		
		// create a for loop to loop through the length of the string
		for(int i=0; i<len; i++) {
			
			// get a substring of the characters after the first letter
			// but leave out characters that have already been shifted to the end of the string
			// by subtracting by i
		    str = str.substring(1, len - i)
		    		
		    		// add the first letter to the end of the substring above 
		        + str.substring(0, 1)
		        
		        // add the missing characters to the end of the substring by
		        // starting index at the length of the strin minus i to the end of the string
		        + str.substring(len - i, len);
		}
		
		// Print out the string
		System.out.println("Q3: " + str);
	}

}
