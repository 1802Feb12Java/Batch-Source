package com.revature.validation;

import com.revature.banking.people.User;
import com.revature.database.DataStore;

public class Validate {

	public static boolean validUsername(String username) {
		// validate length
		if (username.length() < 6)
			return false;
		// validate unique
		for (User u : DataStore.readUsersFromFile()) {
			if (u.getUsername().equals(username))
				return false;
		}
		return true;
	}

	public static boolean validPassword(String password) {
		// validate length
		if (password.length() < 6)
			return false;
		// validate contains number
		if (!containsNumber(password))
			return false;
		// validate unique
		for (User u : DataStore.readUsersFromFile()) {
			if (u.getUsername().equals(password))
				return false;
		}
		return true;
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
