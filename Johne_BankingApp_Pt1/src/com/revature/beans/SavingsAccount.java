package com.revature.beans;

/**
 * 
 * @author johne
 * SavingsAccount is the child class of Account
 */
public class SavingsAccount extends BankAccount{
	
	public SavingsAccount() {
		super("Savings", 0);
	}

	public SavingsAccount(double balance) {
		super("Savings", balance);
	}
}	//end of class
