package com.revature;

public class QuestionEighteen extends Q18Abstract {

	@Override
	public boolean hasUppercaseLetters(String s) {
		char[] charArray = s.toCharArray();
		boolean isCapital = false;

		// checks each character to see if uppercase
		for (char c : charArray) {
			if (Character.isUpperCase(c)) {
				isCapital = true;
			}
		}

		return isCapital;
	}

	// converts given string to all upppercase letters
	@Override
	public String toUpperCase(String s) {
		return s.toUpperCase();
	}

	@Override
	public int add10(String s) {
		// adds 10 to integer value of given string
		return Integer.valueOf(s) + 10;
	}

}