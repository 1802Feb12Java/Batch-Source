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

}
