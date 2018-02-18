package com.revature.work;

public class Q3 {
	
	public void reverse(String woop) {
		// split words into String array with individual character
		String[] word = woop.split("");
		// created n to count down from last index
		int n = word.length - 1 ;
		for (int x = 0; x < word.length ; x++) {
			System.out.print(word[n]);
			n--;
		}	
	}
	
}
