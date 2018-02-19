package com.revature;


public class Q16 {
	public void countLetters(String s) {
		int count = 0; //counter variable for letters
		String[] strArray = s.split("");
		for(String c : strArray) {
			count += 1;
		}
		System.out.println("There are " + count + " characters.");
	}
}
