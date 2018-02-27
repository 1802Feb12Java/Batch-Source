package com.revature.bankapp.model;

public class BankAccount {
	private int accountID;
	private int customerID;
	private double balance;

	public BankAccount() {}
	
	/**
	 * @param accountID
	 * @param customerID
	 * @param balance
	 * @param notes
	 * @param dateEntered
	 */
	public BankAccount(int accountID, int customerID, double balance) {
		this.accountID = accountID;
		this.customerID = customerID;
		this.balance = balance;
	}

	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BankAccount [accountID=" + accountID + ", customerID=" + customerID + ", balance=" + balance + "]";
	}
	
}
