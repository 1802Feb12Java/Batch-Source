package com.revature.validation;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.bl.Users;
import com.revature.exceptions.InvalidMenuOptionException;

public class Validate {
	private static final Logger logger = LogManager.getLogger(Validate.class);

	public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public static int safeParse(String input) {
		try {
			return Integer.parseInt(input.trim());

		} catch (NumberFormatException e) {

			logger.info(new InvalidMenuOptionException(input).getMessage());
		} catch (Exception e) {

		}
		return -1;
	}

	public static boolean validUsername(String username) {
		// validate length
		if (username.length() < 6)
			return false;
		// validate unique
		if (Users.getUser(username) == null)
			return true;
		return false;
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

	public static Date validDate(String input) {
		if (input == null)
			return null;
		try {
			java.util.Date date = formatter.parse(input.trim());
			return new Date(date.getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public static boolean validBoolean(String input) {
		if (input.toLowerCase().trim().equals("true"))
			return true;
		return false;
	}

	public static double validAmount(String amount) {
		try {
			return Double.parseDouble(amount);
			// String pattern = "^[+-]?[0-9]{1,3}(?:,?[0-9]{3})*(?:\\.[0-9]{2})?$";
			// amount.matches(pattern);
		} catch (Exception e) {
		}
		return -1;
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
