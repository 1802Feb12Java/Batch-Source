package com.revature;

import java.util.ArrayList;

public class StringArrays {

	public static void main(String[] args) {
		
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
		
		//System.out.println(isPalindrome("aabba"));
		//System.out.println(isPalindrome("aabaa"));
		//String s = "aabba";
		//System.out.println(s.length()/2);

	}//end main

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
