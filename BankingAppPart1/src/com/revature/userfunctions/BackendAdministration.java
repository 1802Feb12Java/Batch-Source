package com.revature.userfunctions;

import java.util.Scanner;

import com.revature.users.*;

public class BackendAdministration {
	public static Admin createAdmin(Scanner getInput) {
		System.out.print("User name: ");
		String uname = getInput.next();
		System.out.print("Password: ");
		String pwd = getInput.next();
		System.out.print("First name: ");
		String fname = getInput.next();
		System.out.print("Last name: ");
		String lname = getInput.next();
		
		Admin admin = new Admin(uname, pwd, fname, lname);
		
		return admin;
	}
	
	public static Customer createCustomer(Scanner getInput) {
		Customer cust = new Customer();
		
		System.out.print("User name: ");
		cust.setUserName(getInput.next());
		System.out.print("Password: ");
		cust.setPassword(getInput.next());
		System.out.print("First name:");
		cust.setFirstName(getInput.next());
		System.out.print("Last name: ");
		cust.setLastName(getInput.next());
		System.out.print("Street address: ");
		cust.setStreetAddress(getInput.next());
		System.out.print("City: ");
		cust.setCity(getInput.next());
		System.out.print("State: ");
		cust.setState(getInput.next());
		System.out.print("Phone: ");
		cust.setPhoneNumber(getInput.next());
		
		return cust;
	}
}
