package com.revature.work;

public class Q16 {
	String word;
	
	public Q16(String word) {
		this.word = word;
	}

	// checks number of character in given word passed as command line argument
	public void noc () {
		int num = word.length();
		System.out.println(word+" has "+num+" number of characters!");
		
	}
}
