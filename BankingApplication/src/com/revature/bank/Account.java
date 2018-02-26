package com.revature.bank;

public class Account{

	public double accountBal = 0;
	public String accountNumber = "";
	public String customerID = "";

	
	public String getCustomerID() {
		return customerID;
	}



	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}



	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}



	public Account() {
		
	}
	


	public double getAccountBal() {
		return accountBal;
	}

	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	//Method only used for transferring so there are no extra print statements.
	public void t2(double x) {
		accountBal += x;
	}
	
	//withdrawing money from the current account.
	public void withdraw(double w) {
		if(accountBal < w) {
			System.out.println("You do not have the appropriate funds to withdraw that much.");
		}else if(w < 0) {
			System.out.println("You cannot withdraw a negative amount.");
		}else {
			accountBal = accountBal - w;
			System.out.println("You have withdrawn $" + w);
			System.out.println("Your new balance is $" + accountBal);
		}
	}
	
	//depositing funds into the account you are in.
	public void deposit(double d) {
		if(d < 0) {
			System.out.println("You cannot deposit a negative amount.");
		} else {
			accountBal = accountBal + d;
			System.out.println("You deposited $" + d);
			System.out.println("Your new balance is $" + accountBal);
		}
	}
	
	//transfers funds between current and specified account.
	public void transferFunds(double x, Account c) {
		if(accountBal < x) {
			System.out.println("You do not have the appropriate funds to transfer that much.");
		}else if(x < 0) {
			System.out.println("You cannot transfer a negative amount.");
		}else {
			accountBal = accountBal - x;
			c.t2(x);
		}
	}
	
	//closes the specified account.
	public void closeAccount(Account c) {
		c.withdraw(c.accountBal);
		c = null;
		System.gc();
	}
	
	
}
