package com.revature.beans;

public class User {

	private int userID;
	private int accountID;
	private String username;
	private String password;
	private double accountBal;
	
	public User(int userID, int accountID, String username, String password, double accountBal) {
		super();
		this.userID = userID;
		this.accountID = accountID;
		this.username = username;
		this.password = password;
		this.accountBal = accountBal;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public double getAccountBal() {
		return accountBal;
	}

	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}

	
	
	@Override
	public String toString() {
		return "User [userID= " + userID + ", accountID= " + accountID + ", username= " + username + 
				", password= " + password + ", accountBal= " + accountBal + "]";
	}
}
