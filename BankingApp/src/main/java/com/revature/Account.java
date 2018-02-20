package com.revature;

import java.io.Serializable;
import javafx.util.Pair;

/*
 * Account Class - stores and modify account information
 * Final because we don't need to extend from this class
 * 	Members 
 * 		String Account Number
 * 		double Balance
 * 		Pair<String,String> Owner
 * 		boolean Joint
 * 		Pair<String,String> Joint owner
 * 	Methods:
 * 		boolean withdraw
 * 		boolean deposit
*/
final public class Account implements Serializable{

	private static final long serialVersionUID = 3531618419177529503L;
	String accountNumber;
	double balance;
	Pair<String, String> owner;
	boolean joint;
	Pair<String, String> jointOwner;
	
	
	//default Constructor
	public Account() {
		this.accountNumber = "";
		this.balance = 0.0;
		this.owner = new Pair<String, String>("","");
		this.joint = false;
		this.jointOwner = new Pair<String, String>("","");
	}
	
	//Constructor w/ all fields
	public Account(String accountNumber, double balance, Pair<String, String> owner, boolean joint, Pair<String, String> jointOwner) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.owner = owner;
		this.joint = joint;
		this.jointOwner = jointOwner;
	}
	
	//Constructor w/ zero balance to start & non-joint accounts
	public Account(String accountNumber, Pair<String, String> owner) {
		this.accountNumber = accountNumber;
		this.balance = 0.0;
		this.owner = owner;
		this.joint = false;
		this.jointOwner = new Pair<String, String>("","");
	}
	
	//Constructor w/ non-joint accounts
	public Account(String accountNumber, double balance, Pair<String, String> owner) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.owner = owner;
		this.joint = false;
		this.jointOwner = new Pair<String, String>("","");
	}
	
	//Getters & Setters
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
	public Pair<String, String> getOwner() {
		return owner;
	}
	public void setOwner(Pair<String, String> owner) {
		this.owner = owner;
	}
	public boolean isJoint() {
		return joint;
	}
	public void setJoint(boolean joint) {
		this.joint = joint;
	}
	public Pair<String, String> getJointOwner() {
		return jointOwner;
	}
	public void setJointOwner(Pair<String, String> jointOwner) {
		this.jointOwner = jointOwner;
	}
	
	//toString Override
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("Account Number: " + this.accountNumber + 
					"\nBalance: " + this.balance + 
					"\nOwner: " + this.owner.getValue() + " " + this.owner.getKey() +  
					"\nJoint: " + this.joint + "\n");
		if(this.joint)
			buff.append("Joint Owner: " + jointOwner.getValue() + " " + jointOwner.getKey() + "\n");
		return buff.toString();
	}
	
	/*
	 * Withdraw from current account
	 * return true when withdrawn successfully
	 * return false otherwise
	*/
	public boolean withdraw(double amount) {
		if(amount >= this.getBalance()) {
			return false;
		}
		this.setBalance(this.getBalance()-amount);
		return true;
	}
	
	/*
	 * Deposit to current account
	 * return true when deposit successfully
	 * return false otherwise
	*/
	public boolean deposit(double amount) {
		this.setBalance(this.getBalance()+amount);
		return true;
	}
	
}
