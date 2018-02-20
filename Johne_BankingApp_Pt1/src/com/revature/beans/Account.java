package com.revature.beans;

/**
 * 
 * @author johne
 *
 */
public class Account {
	private double balance; // total amount in customer's account
	private int accountID = 0; // accountID associated with customer, increments every time it is instantiated
	private static int count = 0;
	private String accountType;

	public Account() {
		this.balance = 0;
		this.accountType = "";
		this.accountID = count++;
	}

	/**
	 * 
	 * @param balance
	 * @param transactionID
	 */
	public Account(String accountType, double balance) {
		this.setAccountType(accountType);
		this.balance = balance;
		this.accountID = count++;
		/*
		 * might use this for later... try { this.balance = balance; } catch
		 * (NumberFormatException e) {
		 * System.out.println("A Negative amount is invalid. " +
		 * "Please enter a positive amount:"); }
		 */
	}

	/**
	 * when depositing, the new balance will be the current balance added to amount.
	 * Will try to associate a transaction id with each transaction.
	 * 
	 * @param amount
	 *            to deposit
	 */
	public void deposit(double amount) {
		this.balance += amount;
	}
	
	/**
	 * when transferring, the new balance for the account will be added to the
	 * amount. 
	 * 
	 * @param amount
	 *            to transfer
	 * @param account
	 *            targeted account to transfer to
	 */
	public void transfer(double amount, Account otherAccount) {
		this.balance = this.balance - amount;
		otherAccount.balance = otherAccount.balance + amount;
	}

	/**
	 * when withdrawing, the new balance will be current balance subtracted by the
	 * amount. Will try to associate a transaction id with each transaction.
	 * 
	 * @param amount
	 *            to withdraw
	 */
	public void withdraw(double amount) {
		this.balance -= amount;
	}

	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [AccountType = " + accountType + ", AccountID = " + accountID + ", Balance = " + balance +"]";
	}
}//end of class
