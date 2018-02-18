package com.revature;

import java.util.ArrayList;
import java.util.Collections;

public class Q8 {
	public void run() {
		ArrayList<String> stringList = new ArrayList<String>();
		Collections.addAll(stringList, "karan", "madam", "tom", "civic", "radar", "jimmy", "kayak", "john",  "refer", "billy", "did");
		//storing them all in the the arraylist
		System.out.println("All strings in arraylist: " + stringList.toString());
		

		ArrayList<String> palindromeList = new ArrayList<String>();
		//will store all the palindromes at the end
		boolean palindrome;	//will be assumed true when starting a word, turned false if not
		for(String s: stringList) {	//go through each string in the list
			palindrome = true;	//reset the palindrome each time in the loop
			for(int i=0; i<s.length()/2; i++) {	//for each string, check half the characters
				if(s.charAt(i) != s.charAt(s.length()-i-1)) {	//see if the opposite characters match
					palindrome = false;	//if not, turn the boolean false for later
				}
			}
			if(palindrome) {	//if it got through the whole word without an issue matching characters
				palindromeList.add(s);	//add that word to the palindrome list
			}
		}
		
		System.out.println("Only palindromes in arraylist: " + palindromeList.toString());
	}
}
