package com.revature.bankingappnew;

import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Admin extends Employee{
	
	private static Logger log = Logger.getLogger(Admin.class.getName());
	
	public Admin(String username, String password, String firstName, String lastName) throws SQLException {
		super(username, password, firstName, lastName, "Admin");
	}
	
	public Admin(int userID, String username, String password, String firstName, String lastName) throws SQLException {
		super(userID, username, password, firstName, lastName, "Admin");
	}//end constructor. this constructor will be used for creating Customer objects from DB records
	
	public void changeAccountStatus(Account a, String newStatus) throws SQLException {
		a.setAccountStatus(newStatus);
	}//end changeAccountStatus method
	
	public void deposit(Account a, double amount) throws SQLException {
		if(amount < 0) {
			System.out.println("You cannot deposit a negative amount. Transaction Cancelled");
			log.debug("Debug Message: User tried depositing a negative amount.");
		}
		//Account a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accountNum);
		double newBalance = a.getBalance() + amount;
		a.setBalance(newBalance);
		
		com.revature.bankingappnew.daoimplementations.TransactionLogImp.createTransactionRecord(a.getAccountNumber(), "Deposit", amount, this.getUserID());
	}//end deposit method
	
	public void withdraw(Account a, double amount) throws SQLException {
		//Account a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accountNum);
		if(a.getBalance() < amount) {
			System.out.println("Insufficient Funds. Transaction Cancelled.");
			log.info("Transaction Cancelled: Insufficient Funds.");
		} else {
			double newBalance = a.getBalance() - amount;
			a.setBalance(newBalance);
			
			com.revature.bankingappnew.daoimplementations.TransactionLogImp.createTransactionRecord(a.getAccountNumber(), "Withdrawal", amount, this.getUserID());
		}
	}//end withdraw method

	public void transfer(Account a, Account b, double amount) throws SQLException {
		//first perform withdraw from a
		//then perform deposit to b
		withdraw(a, amount);
		//Account acnt = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(a);
		if(a.getBalance() < amount) {
			return; //because withdraw would not have gone through.
		}
		deposit(b, amount);
		
	}//end transfer method
		
	public void viewUserTransactionHistory(String username) throws SQLException {
		com.revature.bankingappnew.daoimplementations.TransactionLogImp.viewUserTransactions(username);
	}//end viewMyTransactionHistory method
	
	public void viewAccountTransactionHistory(int accountNum) throws SQLException{
		com.revature.bankingappnew.daoimplementations.TransactionLogImp.viewAccountTransactions(accountNum);
	}//end viewAccountTransactionHistory method
	
}//end class
