package com.revature.system;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.accounts.Account;
import com.revature.accounts.AdminAccount;
import com.revature.accounts.CustomerAccount;
import com.revature.accounts.EmployeeAccount;
import com.revature.accounts.JointAccount;
import com.revature.dao.JointAccountDAOImp;

public class Core {
	
	private static Logger log = Logger.getLogger(AdminAccount.class.getName());
	
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
		
		//default these, the database will take care of these
		customer.setAccountNumber(1);
		customer.setCustomerID(1000);
		
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
				customer.setCustomerID(i.getCustomerID());
				customer.setJointID(1000);
			}
		}
		
		if(customer.getJointID() != 1000) {
			return null;
		}
		
		JointAccountDAOImp jointDOA = new JointAccountDAOImp();
		jointDOA.addCustomer(customer);
		return customer;
	}
	
	public static void authenticateLogin(String username, String password, ArrayList<Account> accounts, ArrayList<CustomerAccount> customers) {
		
		//go through accounts, and match username and password
		for(Account i: accounts) {
			if(username.equals(i.getUsername())) {
				
				//had to make a quick fix
				if(password.equals(i.getPassword())) {
					boolean active = false;
					//if customer account
					if(i.getPriorityLevel() == 0) {
						
						for(CustomerAccount d: customers) {
							if(d.getUsername().equals(username)) {
								active = d.isActive();
							}
						}
						
						if(active) {
							log.debug(i.getUsername() + " has logged in");
							Menu.loginSuccessCustomerFunctionality((CustomerAccount)i, customers);
							break;
						}
					}
					//employee account
					else if(i.getPriorityLevel() == 1) {
						log.debug(i.getUsername() + " has logged in");
						Menu.loginEmployeeFunctionality((EmployeeAccount)i, customers);
						break;
					}
					//admin account
					else if(i.getPriorityLevel() == 2) {
						log.debug(i.getUsername() + " has logged in");
						Menu.loginAdminFunctionality((AdminAccount)i, customers);
						break;
					}
					//joint account
					else if(i.getPriorityLevel() == -1) {
						JointAccount customerJ = (JointAccount) i;
						
						if(customerJ.isActive()) {
							log.debug(i.getUsername() + " has logged in");
							Menu.loginJointFunctionality(customerJ);
							break;
						}
					}
				}
			}
			
		}
	}
		
	
	
	
	
}
