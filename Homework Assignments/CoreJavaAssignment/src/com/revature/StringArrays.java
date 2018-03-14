package com.revature;

import java.util.ArrayList;

public class StringArrays {
	/*
	 * The purpose of this class is to demonstrate using ArrayLists
	 * and comparing characters within strings to identify palindromes.
	 */
	public static void main(String[] args) {
		
		runStringArrays();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/

	}//end main
	
	public static void runStringArrays() {
		ArrayList<String> plainList = new ArrayList<String>();
		plainList.add("karan");
		plainList.add("madam");
		plainList.add("tom");
		plainList.add("civic");
		plainList.add("radar");
		plainList.add("jimmy");
		plainList.add("kayak");
		plainList.add("john");
		plainList.add("refer");
		plainList.add("billy");
		plainList.add("did");
		
		ArrayList<String> palindromeList = new ArrayList<String>();
		for(String i : plainList) {
			if(isPalindrome(i)) {
				palindromeList.add(i);
			}
		}
		
		System.out.println("The Full List: " + plainList);
		System.out.println("The Palindrome List: " + palindromeList);
	}//end runStringArrays method

	public static Boolean isPalindrome(String s) {
		
		if(s.length() == 1) {
			return true;
		} else {
			for(int i = 0; i < s.length()/2 ; i++) {
				if(s.charAt(i) != s.charAt(s.length()-i-1)) {
					return false;
				}//end if
					
			}//end for
		}
		return true;
		
		
	}//end isPalindrome method
	
	
	
}//end class
