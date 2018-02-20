package com.revature.bankapp;

import java.util.Scanner;

public class Customer {
	
	static int numCustomers =0;
	private int customerId;
	private String username;
	private String password;
	private int accountNumber;
	private boolean apply;
	private char accessLevel='c';
		
	
	
	public char getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(char accessLevel) {
		this.accessLevel = accessLevel;
	}
	public Customer(int customerId, String username, String password, int accountNumber, boolean apply) {
		super();
		numCustomers++;
		this.customerId = numCustomers;
		this.username = username;
		this.password = password;
		this.accountNumber = accountNumber;
		this.apply = apply;
	}
	public Customer(Scanner sc) {
		super();
		numCustomers++;
		customerId = numCustomers;
	}
	public Customer(Scanner sc, boolean joint) {
		super();
		numCustomers++;
		customerId = numCustomers;
	}
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
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void apply(Scanner sc) //returns true if joint and second user needs created. 
	{
		String temp;
		boolean joint = false;
		System.out.println("Please enter your username:");
		username = sc.nextLine();
		System.out.println("Please enter your password:");
		password = sc.nextLine();
		System.out.println("Is this a joint account? (y/n):");
		temp = sc.nextLine();
		if (temp == "y")
			joint = true;
		apply = true;
		accountNumber = customerId;		
		
	}
	public static int getNumCustomers() {
		return numCustomers;
	}
	public static void setNumCustomers(int numCustomers) {
		Customer.numCustomers = numCustomers;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public boolean isApply() {
		return apply;
	}
	public void setApply(boolean apply) {
		this.apply = apply;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", username=" + username + ", password=" + password
				+ ", accountNumber=" + accountNumber + ", apply=" + apply + "]";
	}

	
	
	
	
	
	
	
	
	
}
