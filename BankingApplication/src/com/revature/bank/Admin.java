package com.revature.bank;

import java.util.UUID;

public class Admin extends Employee{

	
	
	public Admin(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
	//
	public static String approveAccount() {
		String accountNumber = UUID.randomUUID().toString();
		return accountNumber;

	}
	
	public void denyAccount() {
		
	}
	
}
