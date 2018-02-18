package com.revature.assignments;

import java.util.ArrayList;

public class AssignmentEight {

	// Write a program that stores the following strings in an ArrayList and saves
	// all the palindromes in another ArrayList.
	// “karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”, “refer”,
	// “billy”, “did”

	private ArrayList<String> palindromes;
	private ArrayList<String> nonPalindromes;

	public AssignmentEight() {
		palindromes = new ArrayList<String>();
		nonPalindromes = new ArrayList<String>();
	}

	public void storeWords(String... strings) {
		for (String x : strings) {
			// check if palindrome
			if (AssignmentThree.reverse(x).equals(x))
				palindromes.add(x);
			else // otherwise store string in non palindrome list
				nonPalindromes.add(x);
		}
	}

	public ArrayList<String> getPalindromes() {
		return palindromes;
	}

	public void setPalindromes(ArrayList<String> palindromes) {
		this.palindromes = palindromes;
	}

	public ArrayList<String> getNonPalindromes() {
		return nonPalindromes;
	}

	public void setNonPalindromes(ArrayList<String> nonPalindromes) {
		this.nonPalindromes = nonPalindromes;
	}

}
