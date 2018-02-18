package com.revature;

public class Q5 {
	public void run() {
		System.out.println("substring(\"supercalifragilisticexpialidocious\", 1): "+substring("supercalifragilisticexpialidocious", 1));
		System.out.println("substring(\"supercalifragilisticexpialidocious\", 5): "+substring("supercalifragilisticexpialidocious", 5));
		System.out.println("substring(\"supercalifragilisticexpialidocious\", 8): "+substring("supercalifragilisticexpialidocious", 8));
		System.out.println("substring(\"supercalifragilisticexpialidocious\", 12): "+substring("supercalifragilisticexpialidocious", 12));
			//print samples
	}
	
	public String substring(String str, int idx) {
		String subString = "";	//to be returned, start off as nothing
		for(int i=0; i<idx; i++) {	//start at 0, go up to 1 before the index (< instead of <=)
			subString += str.charAt(i);	//get the character at each slot, add to the end of the string to return
		}
		return subString;
	}
}
