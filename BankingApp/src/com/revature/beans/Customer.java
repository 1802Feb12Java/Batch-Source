package com.revature.beans;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean approved = false;
	private double accBalance;
	
	public Customer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passoword) {
		this.password = passoword;
	}

	public double getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(double accBalance) {
		this.accBalance = accBalance;
	}
	

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	
	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", accBalance=" + accBalance + ", apprroved=" + approved + "]";
	}
	
	public void withdrawMoney(double d) {
		accBalance -= d;
		System.out.println("The new total in your account is : $" + (accBalance));
	}
	
	public void depositMoney(double d) {
		accBalance += d;
		System.out.println("The new total in your account is : $" + (accBalance));
	}
	
	public void transferMoney(Customer c, double d) {
		accBalance -= d;
		c.setAccBalance(c.getAccBalance()+d);
		System.out.println("Transfer completed!");
		
	}
	

	
	
	

}
