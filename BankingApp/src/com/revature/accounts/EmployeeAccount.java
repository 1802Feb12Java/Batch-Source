package com.revature.accounts;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeAccount extends Account{
	
	//read employee ID from txt file
	private int employeeID;
	
	public EmployeeAccount() {
	}
	
	public EmployeeAccount(String username, String password, String firstName, String lastName, int employeeID) {
		
		super.setUsername(username);
		super.setPassword(password);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		this.setEmployeeID(employeeID);
		
	}
	
	//EmployeeAccounts identifier, and PrivilegeLevel
	private static final byte PRIVILEGE_LEVEL = 1;
	
	private static final long serialVersionUID = 3L;

	@Override
	public byte getPriorityLevel() {
		// TODO Auto-generated method stub
		return PRIVILEGE_LEVEL;
	}
	
	//view all accounts
	public void viewAllCustomerInformation(ArrayList<CustomerAccount> customers) {
		
		for(CustomerAccount i: customers) {
			System.out.println(i.toString());
		}
		
	}
	
	//view all inactive accounts (accounts that have not been approved yet)
	public void viewAllInactiveAccounts(ArrayList<CustomerAccount> customers) {
		
		for(CustomerAccount i: customers) {
			//only print out accounts that have not been approved
			if(!i.isActive()) {
				System.out.print(i.toString());
			}
		}
		
	}
	
	public void approveAccounts(ArrayList<CustomerAccount> customers) {
	
		String input;
		Scanner sc = new Scanner(System.in);
		viewAllInactiveAccounts(customers);
		
		System.out.println();
		System.out.print("Please type in the username of the account you would like to approve: ");
		input = sc.next();
		sc.nextLine();
		
		for(CustomerAccount i: customers) {
			//only print out accounts that have not been approved
			if(!i.isActive() && i.getUsername().equals(input)) {
				i.setActive(true);
			}
		}
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
}
