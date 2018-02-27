package com.revature.bankingappPT2;

public class Account {

	private String accountUser;  	//user account name attached to account
	private String accountName;		//name of the account
	private Integer accountID;		//unique id number for this account
	private Double balance;			//current balance of the account
	private boolean approved;		//whether the account is approved or not
	private Integer customerID;
	
	//no arg default constructor
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	//create an empty inactive account.
	public Account(String accountUser, String accountName, Integer accountID, Integer customerID) {
		this(accountUser, accountName, accountID, 0.00D, false, customerID);
	}
	//full constructor
	public Account(String accountUser, String accountName, Integer accountID, Double balance, boolean approved, Integer customerID) {
		super();
		this.accountUser = accountUser;
		this.accountName = accountName;
		this.accountID = accountID;
		this.balance = balance;
		this.approved = approved;
		this.customerID = customerID;
	}
	public String getAccountUser() {
		return accountUser;
	}
	public void setAccountUser(String accountUser) {
		this.accountUser = accountUser;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getAccountID() {
		return accountID;
	}
	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public int getCustomerID() {
		// TODO Auto-generated method stub
		return customerID;
	}
	
	
	
	
}
