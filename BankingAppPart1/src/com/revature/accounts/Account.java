package com.revature.accounts;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.revature.users.User;

public class Account {
	private double balance = 25.00d;
	private int accountNumber;
	private String status = "Pending";
	private String accountType;
	private String primaryAccountHolder = null;
	protected static Logger log = Logger.getRootLogger();
	
	public Account() {
		super();
	}
	
	public Account(String primaryAccountHolder, String accountType) {
		super();
		this.primaryAccountHolder = primaryAccountHolder;
		this.accountType = accountType;

	}

	public static void listAccounts(ArrayList<Account> accountList) {
		int index = 1;
		
		for(Account current : accountList) {
			System.out.println(index + ".  Account: " + current.getAccountNumber() + "  " + current.getAccountType() +
					"  " + "Balance: " + current.getBalance());
			index++;
		}
	}
	

	public static Account createAccount(User user, String accountType) {
		Account newAccount = new Account();
		
		//set the account to checking or savings
		newAccount.setAccountType(accountType);
		
		//assign the account the the customer
		newAccount.setPrimaryAccountHolder(user.getUserName());
		log.info(user.getFirstName() + " " + user.getLastName() + "created new account.");
		
		return newAccount;
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
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "Account balance: " + balance + "\nAccount status: " + status + "\nAccount type: " + accountType + 
				"Primary account holder: " + primaryAccountHolder;
	}	
}
