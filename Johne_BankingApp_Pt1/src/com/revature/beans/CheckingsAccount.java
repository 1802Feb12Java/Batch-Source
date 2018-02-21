package com.revature.beans;

/**
 * @author johne
 * CheckingsAccount is a child class of Account
 */
public class CheckingsAccount extends BankAccount{
	
	public CheckingsAccount() {
		super("Checkings", 0);
	}
	
	public CheckingsAccount(double balance) {
		super("Checkings", balance);
	}
}	//end of class
