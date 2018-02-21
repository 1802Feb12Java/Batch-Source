package com.revature.bankingapp1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BankEmployee implements Serializable{
	
	private static final long serialVersionUID = 782690677086110916L;
	private String username;
	private String password;
	private static final String accessType = "Employee";
	//private String accessType;
	private String firstName;
	private String lastName;
	
	public BankEmployee(String username, String password, String firstName, String lastName) {
		//need validation to make sure user name is unique
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		//this.accessType = "Employee";
	}//end  initial constructor
	
	public BankEmployee(ArrayList<String> userLog, String username, String password, String firstName, String lastName) {
		//need validation to make sure user name is unique
		this.username = validateUsername(userLog, username);
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		//this.accessType = "Employee";
		FileKeeping.addToUsernamePasswordLog(userLog, this);
	}//end  constructor
	
	public BankEmployee(ArrayList<String> userLog, ArrayList<BankEmployee> employeeLog, String username, String password, String firstName, String lastName) {
		this.username = validateUsername(userLog, username);
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		//this.accessType = "Employee";
		FileKeeping.addToUsernamePasswordLog(userLog, this);
		FileKeeping.addToEmployeeLog(employeeLog, this);
	}//end constructor

	public void viewCustomerDetails(ArrayList<Customer> customerLog, String username) {
		for(Customer c : customerLog) {
			if(c.getUsername().equals(username)) {
				c.viewCustomerDetails();
			}
		}
		
	}//end viewCustomerDetails method
	
	public void viewAccountDetails(ArrayList<Customer> customerLog, int accountNumber) {
		//the following for loops are to find the correct account by going through the 
		//customer log and accessing their account list
		for(Customer c : customerLog) {
			for(Account a : c.accountList) {
				if(a.getAccountNumber() == accountNumber) {
					System.out.println(a.toString());
					return;
				}
			}
		}		
	}//end viewAccountDetails method

	public void viewPendingApplications(ArrayList<Customer> customerLog, String customerUsername) {
		boolean b = false;
		boolean b2 = false;
		for(Customer c : customerLog) {
			if(c.getUsername().equals(customerUsername)) {
				b = true;
				for(Application a : c.applicationList) {
					if(a.getApplicationStatus().equals("Pending")) {
						b2 = true;
						System.out.println(a.toString());
						System.out.println();
					}//will only show pending application details
				}
				return;
			}
		}
		if(!b) {
			System.out.println("Customer with username " + customerUsername + " was not found.");
		}
		if(!b2) {
			System.out.println("This customer has no pending applications.");
		}
	}//end viewPendingApplications method
	
	
	public void changeApplicationStatus(ArrayList<Customer> customerLog, String customerUsername, String applicationType, String newStatus) {
		
		for(Customer c : customerLog) {
			if(c.getUsername().equals(customerUsername)) {
				for(Application a : c.applicationList) {
					if(a.getApplicationStatus().equals("Pending") && a.getApplicationType().equals(applicationType)) {
						a.setApplicationStatus(newStatus);
						System.out.println(c.getFirstName() + " " + c.getLastName() + "'s application for a " 
								+ applicationType + " account has been " + newStatus + ".");
						if(newStatus.equalsIgnoreCase("Approved")) {
							createNewAccount(c, applicationType);
							//c.applicationList.remove(a);
							
						}
						return;
					}
				}
			}
		}
		
	}//end changeApplicationStatus method
	
	public void createNewAccount(Customer customer, String applicationType) {
		
		for(Application a : customer.applicationList) {
			if(a.getApplicationStatus().equalsIgnoreCase("Approved") && a.getApplicationType().equals(applicationType)) {
				customer.accountList.add(new Account(a.getCustomerList(), applicationType));
				System.out.println("A new " + applicationType + " account has been created for " + customer.getFirstName() 
				+ " " + customer.getLastName() + ".");
				break;
			}
		}
		
	}//end createNewAccount method
	
	public String getUsername() {
		return username;
	}


	public String validateUsername(ArrayList<String> userLog, String username) {
		String username2 = "";
		boolean b = FileKeeping.checkForUsername(userLog, username);
		if(!b) { 
			return username;
		} else {
			while(b) {
				System.out.println("This username is already taken.");
				Scanner read = new Scanner(System.in);
				System.out.print("Enter another username you wish to use: ");
				username2 = read.nextLine();
				b = FileKeeping.checkForUsername(userLog, username2);
			}
			return username2;
		}
		
		
	}//end validateUsername


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAccessType() {
		return accessType;
	}


	
}//end class
