package com.revature.bankingappnew;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Customer extends User {
	
	private static Logger log = Logger.getLogger(Customer.class.getName());
	
	public Customer(String username, String password, String firstName, String lastName) throws SQLException {
		super(username, password, firstName, lastName, "Customer");
	}
	
	public Customer(int userID, String username, String password, String firstName, String lastName) throws SQLException {
		super(userID, username, password, firstName, lastName, "Customer");
	}//end constructor. this constructor will be used for creating Customer objects from DB records
	
	public void deposit(Account a, double amount) throws SQLException {
		if(amount < 0) {
			System.out.println("You cannot deposit a negative amount. Transaction Cancelled");
			log.debug("Debug Message: User tried depositing a negative amount.");
			return;
		}
		//Account a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accountNum);
		if(a.getAccountStatus().equalsIgnoreCase("Closed")) {
			System.out.println("This account is closed. Transaction Cancelled.");
			log.debug("Debug Message: User tried depositing to a CLOSED account.");
			return;
		}
		double newBalance = a.getBalance() + amount;
		a.setBalance(newBalance);
		//updating data base:
		com.revature.bankingappnew.daoimplementations.TransactionLogImp.createTransactionRecord(a.getAccountNumber(), "Deposit", amount, this.getUserID());
	}//end deposit method
	
	public void withdraw(Account a, double amount) throws SQLException {
		//Account a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accountNum);
		if(a.getAccountStatus().equalsIgnoreCase("Closed")) {
			System.out.println("This account is closed. Transaction Cancelled.");
			log.info("Transaction Cancelled: User tried withdrawing from a CLOSED account.");
			return;
		}
		if(a.getAuthorizedUser2() != null) {
			if(a.getAuthorizedUser1().getUserID() != this.getUserID() && a.getAuthorizedUser2().getUserID() != this.getUserID()) {
				System.out.println("*****Error: You are not authorized to withdraw from this account.*****");
				log.info("Transaction Cancelled: User tried withdrawing from an account that they are not authorized on.");
				return;
			}
		} else {
			if(a.getAuthorizedUser1().getUserID() != this.getUserID()) {
				System.out.println("*****Error: You are not authorized to withdraw from this account.*****");
				log.info("Transaction Cancelled: User tried withdrawing from an account that they are not authorized on.");
				return;
			}
		}
		
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
		//Account a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accountNum1);
		if(a.getAuthorizedUser2() != null) {
			if(a.getAuthorizedUser1().getUserID() != this.getUserID() && a.getAuthorizedUser2().getUserID() != this.getUserID()) {
				return;
			}
		} else {
			if(a.getAuthorizedUser1().getUserID() != this.getUserID()) {
				return;
			}
		}
		deposit(b, amount);
		
	}//end transfer method
		
	public void viewMyInfo() throws SQLException {
		com.revature.bankingappnew.daoimplementations.UserLogImp.viewUserRecord(this.getUsername());
	}//end viewMyDetails method
	
	public void viewAccountDetails() throws SQLException {
		com.revature.bankingappnew.daoimplementations.AccountLogImp.viewUserAccounts(this.getUsername());
	}//end viewAccountDetails method

	public void createJointApplication(String applicationType, String otherUsername) throws SQLException {
		Customer otherUser = com.revature.bankingappnew.daoimplementations.UserLogImp.getCustomerObject(otherUsername);
		new Application(this, otherUser, applicationType);
	}//end createJointApplication method
	
	public void createApplication(String applicationType) throws SQLException {
		new Application(this, applicationType);
	}//end createJointApplication method

	public void viewMyTransactionHistory() throws SQLException {
		com.revature.bankingappnew.daoimplementations.TransactionLogImp.viewUserTransactions(getUsername());
	}//end viewMyTransactionHistory method
	
	public void viewAccountTransactionHistory(Account a) throws SQLException{
		//Account a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accountNum);
		if(a.getAuthorizedUser2() == null) {
			if(a.getAuthorizedUser1().getUserID() != this.getUserID()) {
				System.out.println("*****Error: You are not authorized to view transaction history for this account.*****");
				log.info("Transaction Cancelled: User tried viewing the transaction history of an account that they are not authorized on.");
				return;
			}
		} else {
			if(a.getAuthorizedUser1().getUserID() != this.getUserID() && a.getAuthorizedUser2().getUserID() != this.getUserID()) {
				System.out.println("*****Error: You are not authorized to view transaction history for this account.*****");
				log.info("Transaction Cancelled: User tried viewing the transaction history of an account that they are not authorized on.");
				return;
			}
		}
		
		com.revature.bankingappnew.daoimplementations.TransactionLogImp.viewAccountTransactions(a.getAccountNumber());
	}//end viewAccountTransactionHistory method

}//end class
