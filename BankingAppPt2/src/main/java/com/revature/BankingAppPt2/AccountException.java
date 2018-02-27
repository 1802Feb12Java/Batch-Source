package com.revature.BankingAppPt2;

public class AccountException extends RuntimeException {
	public AccountException() {
		super("Illegal Account Operation.");
	}
}
