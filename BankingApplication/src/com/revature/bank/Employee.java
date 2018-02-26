package com.revature.bank;

import java.util.Scanner;

public class Employee extends User {

	
	private static final long serialVersionUID = 1L;
	public static Scanner sc = new Scanner(System.in);

	
	public Employee(String username, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public double getBalance(Account c) {
		return c.getAccountBal();
	}
//	
//	public String getCustomerName(Customer c) {
//		if(c.firstName2 == "") {
//			return c.firstName + c.lastName;
//		} else {
//			return c.firstName + " " + c.lastName + " and " + c.firstName2 + " " + c.lastName2;
//		}
//	}
	
	//public 
	
}
