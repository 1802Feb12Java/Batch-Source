package com.revature.model;

public class BankAccount {
	private int accountId;
	private boolean isActive;
	private double balance;
	private Customer owner;
	
	public BankAccount(int id, boolean isActive, double bal, Customer owner) {
		accountId = id;
		this.isActive = isActive;
		balance = bal;
		this.owner = owner;
	}
	
	public BankAccount() {
		this(0, false, 0.0, null);
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}
}
