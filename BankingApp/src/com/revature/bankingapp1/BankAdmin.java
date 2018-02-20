package com.revature.bankingapp1;

import java.util.ArrayList;

public class BankAdmin extends BankEmployee{
	
	private static final long serialVersionUID = 2194305704613266756L;
	private static final String accessType = "Admin";

	public BankAdmin(ArrayList<String> userLog, String username, String password, String firstName, String lastName) {
		super(userLog, username, password, firstName, lastName);

	}//end 4 parameter constructor
	
	public static void changeAccountStatus(ArrayList<Customer> customerLog, long accountNumber) {
		for(Customer c : customerLog) {
			for(Account a : c.accountList) {
				if(a.getAccountNumber() == accountNumber) {
					a.setAccountStatus("Closed");
				}
			}
		}
	}//end changeAccountStatus method
	
	public void withdraw(ArrayList<Customer> customerLog, long accountNumber, double amount) {
		boolean b = false;
		for(Customer c : customerLog) {
			for(Account a : c.accountList) {
				if(a.getAccountNumber() == accountNumber) {
					b = true;
					if(a.getBalance() >= amount) {
						double newBalance = a.getBalance() - amount;
						a.setBalance(newBalance);
						System.out.println("The amount of $" + amount + " has been removed from the account " + accountNumber + ".");
						System.out.println("Account " + accountNumber + " has a new balance of $" + a.getBalance() + ".");
						System.out.println();
					} else {
						System.out.println("Insufficient Funds. Transaction Cancelled.");
						System.out.println();
					}
				}
			}
		}
		if(!b) {
			System.out.println("Account Number " + accountNumber + " was not found. Transaction Canceled.");
			System.out.println();
		}
	}//end withdraw method
	
	public void deposit(ArrayList<Customer> customerLog, long accountNumber, double amount) {
		boolean b = false;
		for(Customer c : customerLog) {
			for(Account a : c.accountList) {
				if(a.getAccountNumber() == accountNumber) {
					b = true;
					double newBalance = a.getBalance() + amount;
					a.setBalance(newBalance);
					System.out.println("The amount of $" + amount + " has been added to the account " + accountNumber + ".");
					System.out.println("Account " + accountNumber + " has a new balance of $" + a.getBalance() + ".");
					System.out.println();
				}
			}
		}
		if(!b) {
			System.out.println("Account Number " + accountNumber + " was not found. Transaction Canceled.");
			System.out.println();
		}
	}//end deposit method
	
	public void transfer(ArrayList<Customer> customerLog, long fromAccountNumber, long toAccountNumber, double amount) {
		//first perform withdraw from the 'from' account
		//then perform deposit to the 'to' account
		withdraw(customerLog, fromAccountNumber, amount);
		deposit(customerLog, toAccountNumber, amount);
		
	}//end transfer method
	
		
	public static String getAccesstype() {
		return accessType;
	}
		
	
}//end class
