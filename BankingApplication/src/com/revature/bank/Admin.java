package com.revature.bank;

public class Admin extends Employee{

	
	
	private static final long serialVersionUID = 1L;

	public Admin(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
	//
	public static void approveAccount(Customer c) {
		Account a = new Account();
		c.setAccountNumber(a.getAccountNumber());
		c.getAcct();

	}
	
}
