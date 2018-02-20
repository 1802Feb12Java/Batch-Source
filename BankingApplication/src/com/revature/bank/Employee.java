package com.revature.bank;

import java.util.Scanner;

public class Employee extends User {

	public static Scanner sc = new Scanner(System.in);

	
	public Employee(String username, String password, String firstName, String lastName) {
		super(lastName, lastName, lastName, lastName);

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
