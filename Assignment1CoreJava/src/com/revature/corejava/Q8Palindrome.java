package com.revature.corejava;

import java.util.ArrayList;

/*Write a program that stores the following strings in an ArrayList and saves all the palindromes in another ArrayList.
“karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”,  “refer”, “billy”, “did”
*/

public class Q8Palindrome {
	public static ArrayList<String> creaePalindromeArray(ArrayList<String> normal){
		ArrayList<String> palindromes = new ArrayList<String>();
		boolean isPalindrome = false;
		
		for (String current : normal) {
			for(int index = 0; index < current.length(); index++) {
				if (current.charAt(index) == current.charAt(current.length() - index - 1)) {
					isPalindrome = true;
				}
				
				else {
					isPalindrome = false;
					break;
				}
			}
			if(isPalindrome) {
				palindromes.add(current);
				isPalindrome = false;
			}
		}
		
		return palindromes;
	}	
}
