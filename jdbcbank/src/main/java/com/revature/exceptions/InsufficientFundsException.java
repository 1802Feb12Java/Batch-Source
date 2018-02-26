package com.revature.exceptions;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 4010529203776703125L;

	public InsufficientFundsException(String amount, String balance) {
		super("Error, tried to withdraw " + amount + ", when the account only has " + balance);
	}

}
