package com.revature.work;

public class Q5 {

	public String subWord (String word, Integer num) {
		// split word passed into individual Strings in array
		String[] newWord = word.split("");
		String ans = null;
		// use num passed to set limit of how many characters to display starting at 0
		for (int x = 0; x < num - 1; x++) {
			if(x==0) {
				ans = newWord[x];
			} else {
			ans += newWord[x];
			}
		}
		return ans;
	}
}
