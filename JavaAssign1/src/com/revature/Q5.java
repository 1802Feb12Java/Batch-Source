package com.revature;

public class Q5 {

	public void createSubString(String s, int idx) { //create substring from 0 to index specified
		String subString = "";
		String[] stringArray = s.split("");
		
		for(int i = 0; i < idx; i++) {
			subString += stringArray[i];
		}
		
		System.out.println(subString);
	}
}
