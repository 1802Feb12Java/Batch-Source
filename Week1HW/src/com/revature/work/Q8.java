package com.revature.work;

import java.util.ArrayList;

public class Q8 {
	// checks for palindrome
	public boolean checkPalindrome(String in) {
	    char[] chars = in.toCharArray();
	    // first and last value
	    int i1=0;
	    int i2=chars.length - 1;
	    while (i2 > i1) {
	    	// checking if i1 and i2 values are the same
	        if ((in.charAt(i1)) != (in.charAt(i2))) {
	            return false;
	        }
	        // increasing first index and decreasing second index for the next check until they are the switched
	        ++i1;
	        --i2;
	    }
	    return true;
	}
	
	public void placeInArray(String[] req) { 

		ArrayList<String> Pain = new ArrayList<String>(); // Arraylist with palindrome
		ArrayList<String> NoPain = new ArrayList<String>(); // ArrayList without palindrome

		System.out.print("Array of all Strings: ");
		for (int x = 0; x < req.length; x++) {
			// prints all the Strings
			System.out.print(req[x]+" ");
			// adding Strings to the right arraylist
			if(checkPalindrome(req[x])== true) {
				Pain.add(req[x]);
			} else
				NoPain.add(req[x]);
		}
		System.out.println("\nArray of Strings with Palindrome: "+Pain);
		System.out.println("Array of Strings without Palindrome: "+NoPain);
	}
}
