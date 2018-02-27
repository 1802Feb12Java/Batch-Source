package com.revature.bankapp.interfaces;

public interface BankAccountInterface {
	
	/**
	 * allows a user to add money to an account
	 * @param amount
	 * @return amount
	 */
	public void deposit(double amount);
	
	/**
	 * allows a user to take money from an account
	 * @param amount
	 * @return
	 */
	public void withdraw(double amount);
	
}
