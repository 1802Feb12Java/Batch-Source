package com.revature.exceptions;

public class NoSuchUserExistsException extends Exception {

	private static final long serialVersionUID = 4010529203776703125L;

	public NoSuchUserExistsException(String... vals) {
		super("Error, tried to find a user with values " + vals + "\n when none existed");

	}

}
