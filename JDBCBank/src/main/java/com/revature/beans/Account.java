package com.revature.beans;

/*
 * Accounts with the bank
 * String Account
 * double Balance
 * User Owner
 * boolean Joint
 * User jointOwner
 * 
 * withdraw
 * deposit
*/
public class Account{
	
	int accountID;
	String accountNumber;
	double balance;
	User owner;
	boolean joint;
	User jointOwner;
	
	//Default Constructor
	public Account() {
		this.accountID = 0;
		this.accountNumber = "";
		this.balance = 0.0;
		this.owner = null;
		this.joint = false;
		this.jointOwner = null;
	}
	
	//Constructors
	public Account(int accountID, String accountNumber, double balance, User owner, boolean joint, User jointOwner) {
		this.accountID = accountID;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.owner = owner;
		this.joint = joint;
		this.jointOwner = jointOwner;
	}
	
	//Getters & Setters
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public boolean isJoint() {
		return joint;
	}
	public void setJoint(boolean joint) {
		this.joint = joint;
	}
	public User getJointOwner() {
		return jointOwner;
	}
	public void setJointOwner(User jointOwner) {
		this.jointOwner = jointOwner;
	}

	//ToString Override 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(
				"Account Number: " + accountNumber + 
				"\nBalance: " + balance + 
				"\nOwner: " + owner.getName().getKey() +  " " + owner.getName().getValue());
		return sb.toString();
	}
	
	/*
	 * Withdraw from this account 
	 * return true when withdrawn successfully: have enough balance
	 * return false otherwise
	*/
	public boolean withdraw(double amount) {
		if(amount >= 0 && amount <= this.getBalance()) {
			this.setBalance(this.getBalance()-amount);
			return true;
		}
		return false;
	}
	
	/*
	 * Deposit to Client's account
	 * return true when deposit successful: amount is positive
	 * return false otherwise
	*/
	public boolean deposit(double amount) {
		if(amount >= 0) {
			this.setBalance(this.getBalance() + amount);
			return true;
		}
		return false;
	}
}
