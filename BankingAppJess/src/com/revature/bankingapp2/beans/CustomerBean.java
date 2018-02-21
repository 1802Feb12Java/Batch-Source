package com.revature.bankingapp2.beans;

import java.util.ArrayList;

import com.revature.bankingapp1.Account;

public class CustomerBean extends UserBean{

	private static final long serialVersionUID = -4313368101099816873L;
	private static final String accountType = "Customer";
	private ArrayList<Account> accountList;
	private ArrayList<Application> applicationList;
	
	public CustomerBean(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		this.accountList = new ArrayList<Account>();
		this.applicationList = new ArrayList<Application>();
	}
	
	public void deposit(Account a, double amount) {
		double newBalance = a.getBalance() + amount;
		a.setBalance(newBalance);
		System.out.println("The amount of $" + amount + " has been added to the account " + a.getAccountNumber() + ".");
		System.out.println("Account " + a.getAccountNumber() + " has a new balance of $" + a.getBalance() + ".");
		System.out.println();
	}//end deposit method

	public void withdraw(long accountNumber, double amount) {
		boolean b = false;
		for(Account a: accountList) {
			if(a.getAccountNumber() == accountNumber) {
				b = true;
				if(a.getBalance() >= amount) {
					double newBalance = a.getBalance() - amount;
					a.setBalance(newBalance);
					System.out.println("The amount of $" + amount + " has been renoved from the account " + accountNumber + ".");
					System.out.println("Account " + accountNumber + " has a new balance of $" + a.getBalance() + ".");
					System.out.println();
				} else {
					System.out.println("Insufficient Funds. Transaction Cancelled.");
					System.out.println();
				}
			}
		}
		//if the first if conditional never evaluates to true, then the account number
		//does not match any in this customer's account. No changes will be made and the
		//boolean variable b will stay false. In this case, the user should be made aware
		//that no changes have been made.
		if(!b) {
			System.out.println("Account Number " + accountNumber + " was not found in your account list." 
					+ " Transaction Canceled.");
			System.out.println();
		}
	}//end withdraw method

	public static String getAccounttype() {

		return accountType;
	}

	public ArrayList<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}

	public ArrayList<Application> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(ArrayList<Application> applicationList) {
		this.applicationList = applicationList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}//end class
