package com.revature;

import java.util.ArrayList;
import java.util.Arrays;

public class Palindrome {
	
	//example of checkPalindrom String
	public static void example() {
		
		//list of strings
		ArrayList<String> allString = new ArrayList<String>(Arrays.asList("karan", "madam", "tom", "civic", 
				"radar", "jimmy", "kayak", "john",  "refer", "billy", "did"));
		ArrayList<String> palindrome = new ArrayList<String>();
		
		System.out.println("All words in list given: ");
		
		for(String i: allString) {
			System.out.println(i);

		}
		
		System.out.println("\nThe palindromes in the list: ");
		
		//loop through all strings, add palindromes in allString to palindrome
		for(String i: allString) {
			if(checkPalindrome(i)) {
				palindrome.add(i);
				System.out.println(i);
			}
		}
		
		}
	
	//function to determine whether a string is a palindrome or not
	static boolean checkPalindrome(String inputString) {
		
		//it looks at each side of the string until hitting the middle
		//if each side is the same, it will exit the for loop and return true
		for(int i = 0; i < (inputString.length() / 2); i++){
	        if(inputString.charAt(i) != inputString.charAt((inputString.length() - 1) - i)){
	             return false;
	        }
	    }
	    
		return true;
	}
}

