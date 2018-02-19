package com.revature;

import java.util.ArrayList;
public class Q8 {

	public void getPalindromes(ArrayList<String> a) {

		ArrayList<String> palList = new ArrayList<String>();
		for (String s : a) {
			
			String rev_word="";
			
			int length = s.length();
			for(int i = length-1; i>=0; i--) {
				rev_word = rev_word + s.charAt(i); //determines the reverse of OG word
			}
			
			if (s.equals(rev_word)) {
				palList.add(s); //palindrome determined when reverse word == OG word
			}
			
		}
		System.out.println("Palindrome ArrayList: \n" + palList.toString());
	}
}
