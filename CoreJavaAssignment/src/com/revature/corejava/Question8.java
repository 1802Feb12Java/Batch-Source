package com.revature.corejava;

import java.util.ArrayList;
import java.util.Arrays;

public class Question8 {
	public static void storePalindromes() {
		System.out.println("Creating initial ArrayList");
		ArrayList<String> initialArray = new ArrayList<String>(
				Arrays.asList("karan", "madam", "tom", "civic", "radar", "jimmy", "kayak", "john", "refer", "billy", "did"));
		System.out.println("Initial Array List is:\n" + Arrays.toString(initialArray.toArray()));
		ArrayList<String> palindromes = new ArrayList<>();
		for (String word : initialArray) {
			if (isPalindrome(word)) {
				palindromes.add(word);
			}
		}
		
		System.out.println("The Palindromes are: " + Arrays.toString(palindromes.toArray()));
	}

	private static boolean isPalindrome(String word) {
		int lastIndex = word.length()-1, firstIndex = 0;
		while (lastIndex > firstIndex) {
			if (word.charAt(firstIndex) != word.charAt(lastIndex)) {
				return false;
			}
			lastIndex--;
			firstIndex++;
		}
		return true;
	}
}
