package com.revature.bankingappPT2;

import java.util.Hashtable;

public class Bank {

	private Hashtable<String, Account> accounts;
	private Hashtable<String, User> userAccounts;
	
	public Bank() {
		super();
		accounts = new Hashtable<String, Account>();
		userAccounts = new Hashtable<String, User>();
	}
	public Bank(Hashtable<String, Account> accounts, Hashtable<String, User> userAccounts) {
		super();
		this.accounts = accounts;
		this.userAccounts = userAccounts;
	}
	public Hashtable<String, Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Hashtable<String, Account> accounts) {
		this.accounts = accounts;
	}
	public Hashtable<String, User> getUserAccounts() {
		return userAccounts;
	}
	public void setUserAccounts(Hashtable<String, User> userAccounts) {
		this.userAccounts = userAccounts;
	}
	public void addUser(User newUser) {
		userAccounts.put(newUser.getUsername(), newUser);
		
	}
	public void addAccount(Account newAccount) {
		accounts.put(newAccount.getAccountUser(), newAccount);
		
	}
	
	
	
}
