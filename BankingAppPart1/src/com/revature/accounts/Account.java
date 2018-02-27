package com.revature.accounts;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.revature.users.User;

public class Account {
	private double balance = 25.00;
	private int accountNumber;
	private String status = "Pending";
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
	

	public static void createAccount(User user) {
		

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
