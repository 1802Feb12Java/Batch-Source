package com.revature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PalindromeDetector {
	public static boolean isPalindrome(String str) {
		// Check the ends of the string with each other until a mismatch is found.
		for(int i = 0, end = str.length()-1; i < end; ++i, --end)
			if(str.charAt(i) != str.charAt(end)) 
				return false;
		
		return true;
	}
	
	public static List<String> getPalindromes(List<String> strList) {
		List<String> palindromeList = new ArrayList<>();
		
		strList.forEach((String s) -> {
			String sLower = s.toLowerCase();
			if(isPalindrome(sLower))
				palindromeList.add(s);
		});
		
		return palindromeList;
	}
}

public class Q8 implements Runnable {
	private final List<String> INPUT;
	
	public Q8(String... strList) {
		INPUT = new ArrayList<>();
		INPUT.addAll(Arrays.asList(strList));
	}
	
	@Override
	public void run() {
		System.out.println("Question 8: Palindrome Search");
		
		System.out.println("    Input: " + INPUT.toString());
		List<String> palindromeList = PalindromeDetector.getPalindromes(INPUT);
		
		System.out.println("    Output: " + palindromeList.toString());
		
	}

}
