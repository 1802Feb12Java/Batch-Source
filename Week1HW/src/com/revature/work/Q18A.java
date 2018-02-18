package com.revature.work;
import java.lang.Character;

public class Q18A extends Q18B{
	
	@Override
	// checks for any Uppercase within the string. Returns true if there is
	boolean anyUp(String word) {
		char forUp = word.charAt(0);
		for (int x = 0; x < word.length(); x++) {
			forUp = word.charAt(x);
			if (Character.isUpperCase(forUp)) {
				return true;
			}
		}
		return false;
	}
		
	@Override
	// Changes any lower case strings to upper case
	String toUpper(String word) {
		return word.toUpperCase();
	}

	@Override
	// changes String to integer and adds 10 to it
	void toInt(String word) {
		int result = Integer.parseInt(word);
		System.out.println(word+" + 10 is "+(result+10));
	}

}
