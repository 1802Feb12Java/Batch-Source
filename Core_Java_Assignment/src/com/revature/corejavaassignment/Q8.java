package com.revature.corejavaassignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q8 {
/*
 * Q8. Write a program that stores the following strings in an ArrayList and saves all the 
 * palindromes in another ArrayList. “karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, 
 * “john”,  “refer”, “billy”, “did”
 */
	public static void answer() {
		System.out.println("Q8.\tWrite a program that stores the following strings in an ArrayList and saves all the \r\n" + 
				"\tpalindromes in another ArrayList. “karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, \r\n" + 
				"\t“john”,  “refer”, “billy”, “did”\n");
		List<String> strings = new ArrayList<String>(), palindromes =null ;
		String[] stringArr = {"karan", "madam", "tom", "civic", "radar", "jimmy", "kayak","john",  "refer", "billy", "did"};
		strings.addAll(Arrays.asList(stringArr));
		System.out.println(strings.toString() + "\n");
		System.out.println("Checking for palindromes");
		palindromes = checkPalindromes(strings);
		System.out.println(palindromes.toString() + "\n");
	}
	/*
	 * this method takes a list of strings and checks if any of the strings within it
	 * are palindroms, it then returns a List of the palindromes found
	 */
	public static List<String> checkPalindromes(List<String> strings) {
		List<String> palindromes = new ArrayList<String>();
		for (String string: strings) {
			Boolean found = true;
			for (int start =0, end=string.length()-1; start<end; start++, end--) {
				if (string.charAt(start) != string.charAt(end)) {
					found=false;
					break;
				}
			}
			if (found) {	palindromes.add(string);	}
		}
		return palindromes;
	}
	
}
