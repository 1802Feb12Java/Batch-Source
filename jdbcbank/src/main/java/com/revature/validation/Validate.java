package com.revature.validation;

import com.revature.exceptions.InvalidMenuOptionException;

public class Validate {

	public static int safeParse(String input) throws InvalidMenuOptionException {
		try {
			return Integer.parseInt(input);

		} catch (NumberFormatException e) {
			throw new InvalidMenuOptionException(input);
		}
	}

	public static boolean validUsername(String username) {
		// validate length
		if (username.length() < 6)
			return false;
		// validate unique
		// if (DataStore.readUsersFromFile() == null)
		// return true;
		// for (User u : DataStore.readUsersFromFile()) {
		// if (u.getUsername().equals(username))
		// return false;
		// }
		return true;
	}

	public static boolean validPassword(String password) {
		// validate length
		if (password.length() < 6)
			return false;
		// validate contains number
		if (!containsNumber(password))
			return false;
		return true;
	}

	public static boolean validSuperCode(String input) {
		if (input != null && input.toLowerCase().replaceAll("\\s", "").equals("iamsuper"))
			return true;
		return false;
	}

	public static boolean validAmount(String amount) {
		String pattern = "^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$";
		return amount.matches(pattern);
	}

	private static boolean containsNumber(String s) {
		for (int i = 0, len = s.length(); i < len; i++) {
			if (Character.isDigit(s.charAt(i))) {
				return true;
			}
		}
		return false;
	}

}
