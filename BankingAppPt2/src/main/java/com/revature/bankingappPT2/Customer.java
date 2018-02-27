package com.revature.bankingappPT2;

import java.util.ArrayList;

public class Customer implements User {

	private String username;
	private String password;
	private ArrayList<Account> listOfAccounts;
	private Integer userAcess;
	private Integer customerID;
	
	public Customer() {
		super();
		//Shouldn't be able to make a null account
	}
	
	public Customer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.userAcess = 1;
		this.listOfAccounts = new ArrayList<Account>();
	}

	public Customer(String username, String password, Integer userAcess) {
		super();
		this.username = username;
		this.password = password;
		this.userAcess = userAcess;
		this.listOfAccounts = new ArrayList<Account>();
	}
	
	public Customer(String username, String password, Integer userAccess, Integer customerID) {
		this.username = username;
		this.password = password;
		this.userAcess = userAccess;
		this.customerID = customerID;
		this.listOfAccounts = new ArrayList<Account>();
	}
	
	/*
	 * Getters and Setters for all instance variables
	 */
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Account> getListOfAccounts() {
		return listOfAccounts;
	}

	public void setListOfAccounts(ArrayList<Account> listOfAccounts) {
		this.listOfAccounts = listOfAccounts;
	}

	public Integer getUserAccess() {
		// TODO Auto-generated method stub
		return this.userAcess;
	}

	public void addAccount(Account newAccount) {
		listOfAccounts.add(newAccount);
		
	}

	public Integer getUserAcess() {
		return userAcess;
	}

	public void setUserAcess(Integer userAcess) {
		this.userAcess = userAcess;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	
	
	
}
