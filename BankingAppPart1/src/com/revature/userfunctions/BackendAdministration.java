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
	
	public static Employee createEmployee(Scanner getInput) {
		System.out.print("User name: ");
		String uname = getInput.next();
		System.out.print("Password: ");
		String pwd = getInput.next();
		System.out.print("First name: ");
		String fname = getInput.next();
		System.out.print("Last name: ");
		String lname = getInput.next();
		
		Employee emp = new Employee(uname, pwd, fname, lname);
		
		return emp;
	}
	
	public static Customer createCustomer(Scanner getInput) {
		Customer cust = new Customer();
		
		System.out.print("User name: ");
		cust.setUserName(getInput.next());
		System.out.println("Password: ");
		cust.setPassword(getInput.next());
		System.out.println("First name:");
		cust.setFirstName(getInput.next());
		System.out.println("Street address: ");
		cust.setStreetAddress(getInput.next());
		System.out.println("City: ");
		cust.setCity(getInput.next());
		System.out.println("State: ");
		cust.setState(getInput.next());
		System.out.println("Phone: ");
		cust.setPhoneNumber(getInput.next());
		
		return cust;
	}
}
