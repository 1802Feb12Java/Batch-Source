package com.revature.accounts;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminAccount extends EmployeeAccount{
	
	//CustomerAccounts identifier, and PrivilegeLevel
	private static final byte PRIVILEGE_LEVEL = 2;
	
	private static final long serialVersionUID = 2L;
	
	private int adminID;

	
	public AdminAccount() {
		
	}
	
	public AdminAccount(String username, String password, String firstName, String lastName, int employeeID) {
		super.setUsername(username);
		super.setPassword(password);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setEmployeeID(employeeID);
		adminID = 1000;
	}
	
	public static byte getPrivilegeLevel() {
		return PRIVILEGE_LEVEL;
	}

	@Override
	public byte getPriorityLevel() {
		// TODO Auto-generated method stub
		return PRIVILEGE_LEVEL;
	}
	
	public void withdrawFromCustomerChecking(ArrayList<CustomerAccount> customers) {
		String input;
		double doubleInput = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("Please type in the username of the account you would like to withdraw from: ");
		System.out.println();
		input = sc.next();
		sc.nextLine();
		
		for(CustomerAccount i: customers) {
			//only print out accounts that have not been approved
			if(i.getUsername().equals(input)) {
				System.out.print("How much would you like to withdraw from checking?: ");
				
				try {
					doubleInput = sc.nextDouble();
					sc.nextLine();					
					i.withdrawChecking(doubleInput);
				}
				catch(InputMismatchException e){
					System.out.println("INVALID INPUT");
				}
				
				System.out.println();
				
			}
		}
	}
	
	public void withdrawFromCustomerSavings(ArrayList<CustomerAccount> customers) {
		String input;
		double doubleInput = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("Please type in the username of the account you would like to withdraw from: ");
		System.out.println();
		input = sc.next();
		sc.nextLine();
		
		for(CustomerAccount i: customers) {
			//only print out accounts that have not been approved
			if(i.getUsername().equals(input)) {
				System.out.print("How much would you like to withdraw from savings?: ");
				
				try {
					doubleInput = sc.nextDouble();
					sc.nextLine();					
					i.withdrawSavings(doubleInput);
				}
				catch(InputMismatchException e){
					System.out.println("INVALID INPUT");
				}
				
				System.out.println();
				
			}
		}
	}
	
	public void depositToCustomerChecking(ArrayList<CustomerAccount> customers) {
		String input;
		double doubleInput = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("Please type in the username of the account you would like to deposit to: ");
		System.out.println();
		input = sc.next();
		sc.nextLine();
		
		for(CustomerAccount i: customers) {
			//only print out accounts that have not been approved
			if(i.getUsername().equals(input)) {
				System.out.print("How much would you like to deposit to checking?: ");
				
				try {
					doubleInput = sc.nextDouble();
					sc.nextLine();					
					i.depositChecking(doubleInput);
				}
				catch(InputMismatchException e){
					System.out.println("INVALID INPUT");
				}
				
				System.out.println();
				
			}
		}
	}
	
	public void depositToCustomerSaving(ArrayList<CustomerAccount> customers) {
		String input;
		double doubleInput = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("Please type in the username of the account you would like to deposit to: ");
		System.out.println();
		input = sc.next();
		sc.nextLine();
		
		for(CustomerAccount i: customers) {
			//only print out accounts that have not been approved
			if(i.getUsername().equals(input)) {
				System.out.print("How much would you like to deposit to savings?: ");
				
				try {
					doubleInput = sc.nextDouble();
					sc.nextLine();					
					i.depositSavings(doubleInput);
				}
				catch(InputMismatchException e){
					System.out.println("INVALID INPUT");
				}
				
				System.out.println();
				
			}
		}
	}
	
	public void disableAccount(ArrayList<CustomerAccount> customers) {
		String input;
		double doubleInput = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("Please type in the username of the account you would like to disable: ");
		input = sc.next();
		sc.nextLine();
		System.out.println();
		
		for(CustomerAccount i: customers) {
			//only print out accounts that have not been approved
			if(i.getUsername().equals(input)) {
				i.setActive(false);
			}
		
		}
	
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
}
