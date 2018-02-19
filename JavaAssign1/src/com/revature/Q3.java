package com.revature;

public class Q3 {  //reverses a string w/o using reverse method or temp var
	public void reverseStr(String s) { //String is used in Driver class

		System.out.println("Original word: "+ s);
		
		String rev_word="";
											//create empty string and input characters backwards
		int length = s.length();
		for(int i = length-1; i>=0; i--) {
			rev_word = rev_word + s.charAt(i);
		}
		System.out.println("Reversed word: "+ rev_word);
	}
}
