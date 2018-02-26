package com.revature.exceptions;

public class InvalidMenuOptionException extends Exception {

	private static final long serialVersionUID = 8580334860991364393L;

	public InvalidMenuOptionException(String input) {
		super(input + " is an invalid menu option, please retry");
	}

}
