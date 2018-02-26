package com.revature.model;

import java.util.Map;

public class Customer extends User {
	private Map<Integer, BankAccount> accounts;
	
	public Customer() {
		this(0, null, null, null);
	}

	public Customer(int userId, String userName, String fName, String lName, BankAccount... accts) {
		super(userId, userName, fName, lName);
		
		if(accts != null && accts.length > 0) {
			for(BankAccount acct : accts) {
				accounts.put(acct.getAccountId(), acct);
			}
		}
	}
	

	public Map<Integer, BankAccount> getAccounts() {
		return accounts;
	}
	
	
}
