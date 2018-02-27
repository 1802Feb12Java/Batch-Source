package com.revature.JDBCBank;

import java.util.ArrayList;

public class Account {	
	private static int numAccounts = 0;
	private int accountID;
	private ArrayList<User> users = new ArrayList<User>();	//will only be 1 value unless it's a joint account
	private double balance = 0;		//all accounts should start with $0 in them
	private boolean approved = false;
	private ArrayList<Integer> userIDs = new ArrayList<Integer>();
	
	public boolean transfer(Account receiver, double amount) {
		if(receiver == null || !receiver.isApproved()) {
			System.err.println("The receiver account either doesn't exist or is not approved yet. No money transferred.");
			return false;
		}
		if(!approved){
			System.err.println("The withdrawl account is not approved yet. No money transferred.");
			return false;
		}
		
		//don't need an else here because it'll only continue past those returns if none of those if statements were true
		boolean withdrawResult = withdraw(amount);	//call it on this class
		if(!withdrawResult) {
			System.err.println("Something went wrong withdrawing.");
			return false;
		}
		boolean depositResult = receiver.deposit(amount);	
		//if withdraw passes, deposit shouldn't fail because withdraw also has all of deposit's checks
		//but it doesn't hurt to be safe in case that changes in the future I guess
		if(!depositResult) {
			this.deposit(amount);	//put the money back that was withdrawn from this account 
			System.err.println("Something went wrong depositing.");
			return false;
		}

		//else if both deposit and withdraw went through nicely
		System.out.println("Transfer successful!");
//		System.out.println("Receiver's new balance: " + receiver.getBalance());
//		System.out.println("Your new balance: " + balance);
		return true;	//indicates transfer successful
	}
	
	public boolean deposit(double amount) {	//returns true if deposit successful
		if(amount < 0) {
			System.err.println("Attempted to deposit negative amount. No balance changes.");
			return false;
		}
		if((amount + balance) > Double.MAX_VALUE/2) {	//double goes insanely high anyways, let's just do max/2
			System.err.println("Attempted to deposit more money than the system can safely handle. No balance change.");
			return false;
		}
		balance += amount;
		System.out.println("Deposited $" + String.format("%.2f", amount) + "\tNew balance = $" + String.format("%.2f", balance));
		return true;
	}
	
	public boolean withdraw(double amount) {	//returns true if withdrawal successful
		if(amount < 0) {
			System.err.println("Attempted to withdraw negative amount. No balance changes.");
			return false;
		}
		if(amount > balance) {
			System.err.println("You don't have that much in your account. Current balance = $" + String.format("%.2f", balance) + ".");
			return false;
		}
		balance -= amount;
		System.out.println("Withdrew $" + String.format("%.2f", amount) + "\tNew balance = $" + String.format("%.2f", balance));
		return true;
	}
	
	//past this point is mostly Alt+Shift+S, minor modification in toString and constructors
	public int getAccountID() {
		return accountID;
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {	//might want to make it protected later so it'll only be accessible within subclasses
		//has to be public while tests being run because even subclassing won't make it work
		this.balance = balance;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public ArrayList<Integer> getUserIDs() {
		return userIDs;
	}
	public void addUserID(int userID) {
		userIDs.add(userID);
	}
	public Account(int accountID) {
		super();
		this.accountID = accountID;
		numAccounts++;
	}
	public Account(User... users) {
		super();
		numAccounts++;		//increase the counter for the number of accounts
		this.accountID = numAccounts;	//assign a unique ID number to the account, would start at 2 for some reason so did -1
		for(User c : users) {
			this.users.add(c);
		}
	}
	@Override
	public String toString() {
		String str = "Account ID = " + accountID + "\nBalance = $" + String.format("%.2f", balance) + "\nUsers = ";
		if(users!=null) {
		for(User c : users) {
			str += c.getUsername();
			if(c != users.get(users.size()-1)) {
				str += ", ";
			}
		}
		}
		str += "\nApproved? ";
		str += approved? "Yes\n":"No\n";
		return str;
	}
}
