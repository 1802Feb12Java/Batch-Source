package com.revature.accounts;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.log4j.Logger;

import com.revature.users.Customer;

public class Account {
	private double balance = 25.00;
	private boolean active = true;
	private boolean jointAccount = false;
	private boolean goodStanding = true;
	private int accountNumber = new Random().nextInt(1000) + 10000;
	private String accountType;
	private String primaryAccountHolder = null;
	private Logger log = Logger.getRootLogger();
	
	public Account() {
		super();
	}
	
	public Account(String primaryAccountHolder, String accountType) {
		super();
		this.primaryAccountHolder = primaryAccountHolder;
		this.accountType = accountType;

	}

	public static void listAccounts(ArrayList<Account> accountList, String userName) {
		for(Account current : accountList) {
			int index = 1;
			System.out.println(index + ".  Account: " + current.getAccountNumber() + "  " + current.getAccountType() +
					"  " + "Balance: " + current.getBalance());
			index++;
		}
	}
	

	public static HashMap<String, ArrayList<Account>> createAccount(Customer customer, 
			HashMap<String, ArrayList<Account>> accountsMap) {
		
		Account newAccount = null;
		
		ArrayList<Account> accountsList = null;
		
		//get the customer's account list
		if(accountsMap.containsKey(customer.getUserName())) {
			 accountsList = accountsMap.get(customer.getUserName());
		}
		
		else {
			accountsList = new ArrayList<>();
		}
		
		//CHECKING
		//Regular checking
		if(!customer.isApplyingForSavings()) {
			newAccount = new Account(customer.getUserName(), "Checking");
			
			//add the new account to the accounts list
			accountsList.add(newAccount);
			accountsMap.put(customer.getUserName(), accountsList);
			
		}
		
		//SAVINGS
		//Regular savings
		else {
			newAccount = new Account(customer.getUserName(), "Savings");
			
			//add the new account to the accounts list
			accountsList.add(newAccount);
			
			//add the list to the account map
			accountsMap.put(customer.getUserName(), accountsList);
		}

		customer.setAccountHolder(true);
		return accountsMap;
	}
	
	public void withdraw(double amount) {
		balance -= amount;
		log.info("customer withdrew some stuff");
	}
	
	public void deposit(double amount) {
		balance += amount;
	}
	
	public void transfer(double amount, Account destination) {
		balance -= amount;
		destination.setBalance(destination.getBalance() + amount);		
	}

	public void listAccounts(ArrayList<Account> accounts) {
		for (Account current : accounts) {
			current.toString();
		}
	}
		
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isJointAccount() {
		return jointAccount;
	}

	public void setJointAccount(boolean jointAccount) {
		this.jointAccount = jointAccount;
	}

	public boolean isGoodStanding() {
		return goodStanding;
	}

	public void setGoodStanding(boolean goodStanding) {
		this.goodStanding = goodStanding;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPrimaryAccountHolder() {
		return primaryAccountHolder;
	}

	public void setPrimaryAccountHolder(String primaryAccountHolder) {
		this.primaryAccountHolder = primaryAccountHolder;
	}

	@Override
	public String toString() {
		return "Account balance: " + balance + "\nAccount active: " + active + "\nJoint account: " + jointAccount
				+ "\nAccount in good standing: " + goodStanding + "\nAccount type: " + accountType + 
				"Primary account holder: " + primaryAccountHolder;
	}	
}
