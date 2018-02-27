package com.revature.bankingappnew;

import java.sql.SQLException;


public class Account{
	
	private final int accountNumber;
	private Customer authorizedUser1;
	private Customer authorizedUser2;
	private String accountType;//checking, savings, joint checking, or joint savings
	private String accountStatus;//active, closed
	private double balance;
	
	public Account(Customer cust1, Customer cust2, String type) throws SQLException {
		this.authorizedUser1 = cust1;
		this.authorizedUser2 = cust2;
		this.accountType = "Joint " + type;
		this.accountStatus = "Active";
		com.revature.bankingappnew.daoimplementations.AccountLogImp.createAccountRecord("Active", type, 0.0, cust1.getUsername(), cust2.getUsername());
		
		this.accountNumber = com.revature.bankingappnew.daoimplementations.AccountLogImp.getMaxAccountID();
	}
	
	public Account(Customer cust1, String type) throws SQLException {
		this.authorizedUser1 = cust1;
		this.authorizedUser2 = null;
		this.accountType = type;
		this.accountStatus = "Active";
		
		com.revature.bankingappnew.daoimplementations.AccountLogImp.createAccountRecord("Active", type, 0.0, cust1.getUsername());
		this.accountNumber = com.revature.bankingappnew.daoimplementations.AccountLogImp.getMaxAccountID();
	}

	public Account(int accountID, Customer cust1, String accntStatus, String accntType, double balance) {
		this.authorizedUser1 = cust1;
		this.authorizedUser2 = null;
		this.accountType = accntType;
		this.accountStatus = accntStatus;
		this.balance = balance;
		this.accountNumber = accountID;
	}//end constructor. this constructor will be used to create Account Objects from DB records
	
	public Account(int accountID, Customer cust1, Customer cust2, String accntStatus, String accntType, double balance) {
		this.authorizedUser1 = cust1;
		this.authorizedUser2 = cust2;
		this.accountType = accntType;
		this.accountStatus = accntStatus;
		this.balance = balance;
		this.accountNumber = accountID;
	}//end constructor. this constructor will be used to create Account Objects from DB records
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) throws SQLException {
		this.accountType = accountType;
		if(authorizedUser2 != null) {
			com.revature.bankingappnew.daoimplementations.AccountLogImp.updateAccountInfo(this.accountNumber, this.accountStatus, accountType, this.authorizedUser2.getUsername());
		} else {
			com.revature.bankingappnew.daoimplementations.AccountLogImp.updateAccountInfo(this.accountNumber, this.accountStatus, accountType, "");
			
		}
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String status) throws SQLException {
		this.accountStatus = status;
		if(authorizedUser2 != null) {
			com.revature.bankingappnew.daoimplementations.AccountLogImp.updateAccountInfo(this.accountNumber, status, this.accountType, this.authorizedUser2.getUsername());
		} else {
			com.revature.bankingappnew.daoimplementations.AccountLogImp.updateAccountInfo(this.accountNumber, status, this.accountType, "");
			
		}
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) throws SQLException {
		this.balance = balance;
		com.revature.bankingappnew.daoimplementations.AccountLogImp.updateAccountBalance(this.accountNumber, balance);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public Customer getAuthorizedUser1() {
		return authorizedUser1;
	}

	public Customer getAuthorizedUser2() {
		return authorizedUser2;
	}

	public void setAuthorizedUser2(Customer authorizedUser2) throws SQLException {
		com.revature.bankingappnew.daoimplementations.AccountLogImp.updateAccountInfo(this.accountNumber, this.accountStatus, this.accountType, authorizedUser2.getUsername());
	}
	
	
}//end class
