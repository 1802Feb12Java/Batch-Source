package com.revature.beans;

public class Account {
	
	private int id;
	private int is_approved;
	private double balance;
	
	public Account(int id, int is_approved, double balance) {
		this.id=id;
		this.is_approved=is_approved;
		this.balance=balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIs_approved() {
		return is_approved;
	}

	public void setIs_approved(int is_approved) {
		this.is_approved = is_approved;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
