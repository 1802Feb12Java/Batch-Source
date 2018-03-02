package com.revature;

public class QuestionFive {

	public String substringWithoutSubstring(String subThis, int start, int end) {
		// checks to see if parameters are in correct order
		if (end > start) {

		} else {
			System.err.println("End must be greater than start.");
			System.exit(-1);
		}

		char[] arrayOfLetters = subThis.toCharArray();
		String returnThis = "";

		try {
			// loops from specified start point to end point
			for (int i = start; i < end; i++) {
				returnThis = returnThis.concat(Character.toString(arrayOfLetters[i]));
			}
		} catch (Exception e) {
			System.err.println("Incorrect indices.");
			System.exit(-1);
		}

		return returnThis;
	}

}