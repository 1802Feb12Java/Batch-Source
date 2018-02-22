package com.revature;

public class Q5 {
	
	public Q5(String str, int index) {
		
		// get the length of the string and store as an int variable
		int len = str.length();
		
		// split the string into an array with each value being one character in length
		String[] arr = str.split("");
		
		// set empty string variable to populate the substring
		String substr = "";
		
		// if the index is greater than the length of the string print out error statement
		if(index > len) {
			System.out.println("Q5: " + "Index must not exceed " + len + " for the given string.");
		} else {
			
			// create a for loop to populate the substring index amount of times
			for(int i=0; i<index; i++) {
				substr += arr[i];
			}
			
			// print out the substring
			System.out.println("Q5: " + substr);
		}
	}

}
