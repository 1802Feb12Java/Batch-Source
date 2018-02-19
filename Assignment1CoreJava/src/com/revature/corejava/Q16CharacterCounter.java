package com.revature.corejava;

/*Question 16: Q16. Write a program to display the number of characters for a string input. 
The string should be entered as a command line argument using (String [ ] args).*/

public class Q16CharacterCounter {
	public static int countChars(String[] token) {
		int numOfCharacters = 0;

		//iterate through the args and add up the total length of each arg
		for (String current : token) {
			numOfCharacters += current.length();
		}
		return numOfCharacters;
	}
}
