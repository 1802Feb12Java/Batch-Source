package com.revature.system;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.accounts.Account;
import com.revature.accounts.AdminAccount;
import com.revature.accounts.CustomerAccount;
import com.revature.accounts.EmployeeAccount;
import com.revature.accounts.JointAccount;

public class Core {
	
	
	public static CustomerAccount createCustomerAccount() {
		String input = "";
		int inputInt = 0;
		Scanner sc = new Scanner(System.in);
		CustomerAccount customer = new CustomerAccount();
		boolean validity;
		
		//this section is for username
		do {
			System.out.println("Please enter a username of 32 characters or less (no spaces).");
			System.out.print("Username: ");
			input = sc.next();
			
			//From what I understand is that this will go to the next line, rather than the 
			//next token. Without this, the next token (if there is one) would be used instead
			//of the one typed in. Therefore this is in a way "clearing" the scanner.
			sc.nextLine();	
			
			//setCustomer returns true when given valid input and false otherwise
			validity = customer.setUsername(input);
		
			//loop if the username is invalid
		}while(!validity);
		
		do {
			System.out.print("Password: ");
			input = sc.next();		
			sc.nextLine();
			
			validity = customer.setPassword(input);
			
			//loop if the password is invalid
		}while(!validity);
		
		System.out.print("Enter your age: ");

		//catch possible exceptions from invalid input
		try {
			inputInt = sc.nextInt();
			sc.nextLine();
			
			//if the customer is not 18, we do not want them to make an account
			if(!customer.setAge(inputInt)) {
				return null;
			}
		}
		catch(InputMismatchException e){
			System.out.println("INVALID INPUT");
			return null;
		}
		
		System.out.print("First Name: ");
		input = sc.next();		
		sc.nextLine();
		customer.setFirstName(input);
		
		System.out.print("Last Name: ");
		input = sc.next();		
		sc.nextLine();
		customer.setLastName(input);
		
		return customer;		
	}
	
	public static JointAccount createJointAccount(ArrayList<CustomerAccount> customers) {
		String input = "";
		int inputInt = 0;
		Scanner sc = new Scanner(System.in);
		JointAccount customer = new JointAccount();
		boolean validity;
		
		//this section is for username
		do {
			System.out.println("Please enter a username of 32 characters or less (no spaces).");
			System.out.print("Username: ");
			input = sc.next();
			
			//From what I understand is that this will go to the next line, rather than the 
			//next token. Without this, the next token (if there is one) would be used instead
			//of the one typed in. Therefore this is in a way "clearing" the scanner.
			sc.nextLine();	
			
			//setCustomer returns true when given valid input and false otherwise
			validity = customer.setUsername(input);
		
			//loop if the username is invalid
		}while(!validity);
		
		do {
			System.out.print("Password: ");
			input = sc.next();		
			sc.nextLine();
			
			validity = customer.setPassword(input);
			
			//loop if the password is invalid
		}while(!validity);
		
		System.out.print("Enter your age: ");

		//catch possible exceptions from invalid input
		try {
			inputInt = sc.nextInt();
			sc.nextLine();
			
			//if the customer is not 18, we do not want them to make an account
			if(!customer.setAge(inputInt)) {
				return null;
			}
		}
		catch(InputMismatchException e){
			System.out.println("INVALID INPUT");
			return null;
		}
		
		System.out.print("First Name: ");
		input = sc.next();		
		sc.nextLine();
		customer.setFirstName(input);
		
		System.out.print("Last Name: ");
		input = sc.next();		
		sc.nextLine();
		customer.setLastName(input);
		
		System.out.print("Enter account to join with: ");
		input = sc.next();		
		sc.nextLine();
		
		for(CustomerAccount i: customers) {
			if(i.getUsername().equals(input)) {
				customer.setCustomer(i);
				return customer;
			}
		}
		
		return null;
	}
	
	public static void authenticateLogin(String username, String password, ArrayList<Account> accounts, ArrayList<CustomerAccount> customers) {
		
		//go through accounts, and match username and password
		for(Account i: accounts) {
			if(username.equals(i.getUsername())) {
				if(password.equals(i.getPassword())) {
					
					//if customer account
					if(i.getPriorityLevel() == 0) {
						CustomerAccount customer = (CustomerAccount) i;
						if(customer.isActive()) {
							Menu.loginSuccessCustomerFunctionality((CustomerAccount)i);
							break;
						}
					}
					//employee account
					else if(i.getPriorityLevel() == 1) {
						Menu.loginEmployeeFunctionality((EmployeeAccount)i, customers);
						break;
					}
					//admin account
					else if(i.getPriorityLevel() == 2) {
						Menu.loginAdminFunctionality((AdminAccount)i, customers);
						break;
					}
					//joint account
					else if(i.getPriorityLevel() == -1) {
						JointAccount customerJ = (JointAccount) i;
						
						if(customerJ.isActive()) {
							Menu.loginJointFunctionality(customerJ);
							break;
						}
					}
				}
			}
			
		}
	}
		
	
	
	
	
}
