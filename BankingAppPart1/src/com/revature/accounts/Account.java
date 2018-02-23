package com.revature.accounts;

import java.util.ArrayList;

public class Account {
	private double balance = 25.00;
	private boolean active = true;
	private boolean jointAccount = false;
	private boolean goodStanding = true;
	private static int accountNumber = 10000;
	private String accountType;
	private String primaryAccountHolder = null;
	private String secondaryAccountHolder = null;
	
	public Account() {
		super();
	}
	
	public Account(String primaryAccountHolder, String accountType) {
		super();
		this.primaryAccountHolder = primaryAccountHolder;
		this.accountType = accountType;

	}
	
	public Account(String primaryAccountHolder, String accountType, String secondaryAccountHolder) {
		this(primaryAccountHolder, accountType);
		this.secondaryAccountHolder = secondaryAccountHolder;
		this.jointAccount = true;
	}
	
	public void withdraw(double amount) {
		balance -= amount;
	}
	
	public void deposit(double amount) {
		balance += amount;
	}
	
	public void transfer(double amount, Account destination) {
		balance -= amount;
		destination.setBalance(destination.getBalance() + amount);		
	}

	public void listAccounts(ArrayList<Account> accounts) {
		for (Account current : accounts) {
			current.toString();
		}
	}
		
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isJointAccount() {
		return jointAccount;
	}

	public void setJointAccount(boolean jointAccount) {
		this.jointAccount = jointAccount;
	}

	public boolean isGoodStanding() {
		return goodStanding;
	}

	public void setGoodStanding(boolean goodStanding) {
		this.goodStanding = goodStanding;
	}

	public static int getAccountNumber() {
		return accountNumber;
	}

	public static void setAccountNumber(int accountNumber) {
		Account.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPrimaryAccountHolder() {
		return primaryAccountHolder;
	}

	public void setPrimaryAccountHolder(String primaryAccountHolder) {
		this.primaryAccountHolder = primaryAccountHolder;
	}

	public String getSecondaryAccountHolder() {
		return secondaryAccountHolder;
	}

	public void setSecondaryAccountHolder(String secondaryAccountHolder) {
		this.secondaryAccountHolder = secondaryAccountHolder;
	}

	@Override
	public String toString() {
		return "Account balance: " + balance + "\nAccount active: " + active + "\nJoint account: " + jointAccount
				+ "\nAccount in good standing: " + goodStanding + "\nAccount type: " + accountType + 
				"Primary account holder: " + primaryAccountHolder + "\nSecondary Account Holder: " 
				+ secondaryAccountHolder;
	}

	
}
