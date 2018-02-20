package com.revature.bankingapp.users;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userAccountName;
	private String accountName;
	private Double	Balance;
	
	public Account(String userAccountName, String accountName, Double balance) {
		super();
		this.userAccountName = userAccountName;
		this.accountName = accountName;
		Balance = balance;
	}

	public Account(String userAccountName, String accountName) {
		this(userAccountName, accountName, 0.00D);
	}
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserAccountName() {
		return userAccountName;
	}

	public void setUserAccountName(String userAccountName) {
		this.userAccountName = userAccountName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Double getBalance() {
		return Balance;
	}

	public void setBalance(Double balance) {
		Balance = balance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
	
	
	
	
	
}
