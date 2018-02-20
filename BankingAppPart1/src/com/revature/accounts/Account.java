package com.revature.accounts;

public class Account {
	private double balance;
	private boolean active;
	
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
}
