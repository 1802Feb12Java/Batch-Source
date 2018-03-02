package com.revature;

import java.util.ArrayList;

public class QuestionEight {

	//adds specified words to array list
	public static ArrayList<String> populateArrayList() {
		ArrayList<String> original = new ArrayList<String>();
		original.add("karan");
		original.add("madam");
		original.add("tom");
		original.add("civic");
		original.add("radar");
		original.add("jimmy");
		original.add("kayak");
		original.add("john");
		original.add("refer");
		original.add("billy");
		original.add("did");

		return original;
	}

	//checks to see if word is palindrome
	public ArrayList<String> palindrome() {
		ArrayList<String> og = populateArrayList();
		ArrayList<String> addToThis = new ArrayList<String>();

		for (String s : og) {

			int iterations = 0;

			//iterates through half of string
			for (int i = 0; i < s.length() / 2; i++) {
				//compares first and last letters and moves inwards; breaks out if not same
				if (s.charAt(i) != s.charAt(s.length() - (i + 1))) {
					break;
				} else {
					iterations++;
				}
			}

			//if loop made it to the middle of string, is a palindrome
			if (iterations == s.length() / 2) {
				addToThis.add(s);
			}
		}

		System.out.println(addToThis);

		return addToThis;
	}
}