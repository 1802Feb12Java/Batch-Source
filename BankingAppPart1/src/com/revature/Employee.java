package com.revature;

import java.util.ArrayList;
import java.util.Scanner;

public class Employee {
	private ArrayList<Account> allAccounts;		//each employee will have a reference to all available accounts and customers
	private ArrayList<Customer> allCustomers;
	private String employeePassword = "let the employee in";
	
	public String readAccount(int accountID) {
		boolean foundAccount = false;
		int index = 0;
		for(int i=0; i<allAccounts.size(); i++) {
			if(allAccounts.get(i).getAccountID() == accountID) {
				foundAccount = true;
				index = i;
			}
		}
		if(!foundAccount) {
			return "Account ID doesn't exist currently.";
		} 
		Account a = allAccounts.get(index);
		return a.toString();
	}
	public String readCustomer(int customerID) {
		Customer c = null;		
		boolean foundCustomer = false;
		for(int i=0; i<allCustomers.size(); i++) {
			if(allCustomers.get(i).getCustomerID() == customerID) {
				c = allCustomers.get(i);
				foundCustomer = true;
			}
		}
		if(!foundCustomer) {
			return "ID doesn't exist currently.";
		} 
		return c.toString();
	}
	public void reviewAccountApplications() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);	//have to leave it open here because it'll mess up the driver if I close it here
		String choice = "";
		for(Account a : allAccounts) {
			if(!a.isApproved()) {
				System.out.print(a.toString() + "\nWould you like to approve this account? Please type Y or N: ");
				choice = s.nextLine();
				while(!(choice.equals("y") || choice.equals("n") || choice.equals("Y") || choice.equals("N"))) {	
					//while they didn't put in c, e, or a (or their capital versions)
					System.out.println("Invalid input. Please put either Y or N.");
					choice = s.nextLine();
				}
				if(choice.equals("y") || choice.equals("Y")) {
					a.setApproved(true);
					System.out.println("Account " + a.getAccountID() + " approved!");
				}
			}
		}
		System.out.println("\nNo more unapproved accounts.");
	}

	//past this point is mostly Alt+Shift+S, minor modification in toString and constructors
	public ArrayList<Account> getAllAccounts() {
		return allAccounts;
	}
	public ArrayList<Customer> getAllCustomers() {
		return allCustomers;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public Employee() {
		super();
	}
	public Employee(ArrayList<Account> allAccounts, ArrayList<Customer> allCustomers) {
		super();
		this.allAccounts = allAccounts;
		this.allCustomers = allCustomers;
	}
}
