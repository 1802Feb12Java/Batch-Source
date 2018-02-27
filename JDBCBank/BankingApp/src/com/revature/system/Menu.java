package com.revature.system;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.accounts.AdminAccount;
import com.revature.accounts.CustomerAccount;
import com.revature.accounts.EmployeeAccount;
import com.revature.accounts.JointAccount;

public class Menu {
	
	private static Logger log = Logger.getLogger(AdminAccount.class.getName());


	
	public static void mainMenu() {
		System.out.println("--------------------------------------------------------");
		System.out.println("Please choose an option: ");
		System.out.println("1. Log In");
		System.out.println("2. Create an account");
		System.out.println("3. Create a joint account");
		System.out.println("4. Exit Program");
	}	
	
	private static void loginSuccessCustomer() {
		System.out.println("--------------------------------------------------------");
		System.out.println("Please choose an option");
		System.out.println("1. Withdraw from checking");
		System.out.println("2. Withdraw from savings");
		System.out.println("3. Deposit into checking");
		System.out.println("4. Deposit into savings");
		System.out.println("5. Transfer from checking");
		System.out.println("6. Transfer from savings");
		System.out.println("7. View your account details");
		System.out.println("8. Logout");	
		
	}	
	
	public static void loginSuccessCustomerFunctionality(CustomerAccount customer, ArrayList<CustomerAccount> customers) {

		String option = "";
		String username = "";
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		boolean actionComplete;
		
		while(!exit) {
			//display main menu
			double amount = 0.0d;
			loginSuccessCustomer();
			
			option = sc.next();
			sc.nextLine();
			
			switch(option) {
			case "1":
				
				System.out.print("Enter the amount you would like to withdraw from your checking: ");
				
				try {
					amount = sc.nextDouble();
					sc.nextLine();
					
					actionComplete = customer.withdrawChecking(amount);
					
					if(!actionComplete) {
						System.out.println("You do not have enough in your checking to withdraw that much");
					}
					else {
						System.out.println("Successfully withdrew $" + amount + " from checking");
					}
					
				}catch(InputMismatchException e){
					System.out.println("INVALID INPUT");
				}
				break;
			case "2":
				
				System.out.print("Enter the amount you would like to withdraw from your savings: ");	
				
				try {
					amount = sc.nextDouble();
					sc.nextLine();	
					
					actionComplete = customer.withdrawSavings(amount);
					
					if(!actionComplete) {
						System.out.println("You do not have enough in your savings to withdraw that much");
					}
					else {
						System.out.println("Successfully withdrew $" + amount + " from savings");
					}
					
				}catch(InputMismatchException e){
					System.out.println("INVALID INPUT");
				}
				break;
			case "3":				
				
				System.out.print("Enter the amount you would like to put into your checking: ");
					
				try {
					amount = sc.nextDouble();
					sc.nextLine();	
					
					customer.depositChecking(amount);
					
					System.out.println("Successfully deposited $" + amount + " into checking");
					
				}catch(InputMismatchException e){
					System.out.println("INVALID INPUT");
				}
				break;
			case "4":
				System.out.print("Enter the amount you would like to put into your savings: ");
				
				try {
					amount = sc.nextDouble();
					sc.nextLine();	
					
					customer.depositSavings(amount);
					
				}catch(InputMismatchException e){
					System.out.println("INVALID INPUT");
				}				
				break;
			case "5":
				System.out.print("Please enter the username of the account you would like to transfer to: ");
				
				try {
					username = sc.next();
					sc.nextLine();
					
					System.out.print("Please enter the amount: ");
					
					amount = sc.nextDouble();
					sc.nextLine();
					
					customer.transferFromSavings(customer, username, customers, amount);
					
				}catch(InputMismatchException e){
					System.out.println("INVALID INPUT");
				}
				break;	
			case "6":
				System.out.print("Please enter the username of the account you would like to transfer to: ");
				
				
					username = sc.next();
					sc.nextLine();	
					
					System.out.print("Please enter the amount: ");
				try {	
					amount = sc.nextDouble();
					sc.nextLine();
					
					customer.transferFromChecking(customer, username, customers, amount);
					
				}catch(InputMismatchException e){
					System.out.println("INVALID INPUT");
				}
				break;
			case "7":
				customer.toString();
			case "8":
				log.debug(customer.getUsername() + " logged out.");
				exit = true;
				break;
			default:
				System.out.println("INVALID OPTION");
				loginSuccessCustomer();
				break;
			}
			
		}
	}	
	
	private static void loginSuccessEmployee() {
		System.out.println("--------------------------------------------------------");
		System.out.println("Please choose an option");
		System.out.println("1. View all accounts");
		System.out.println("2. View unapproved applications");
		System.out.println("3. Approve applications");
		System.out.println("4. Logout");	
		
	}	
	
	public static void loginEmployeeFunctionality(EmployeeAccount employee, ArrayList<CustomerAccount> customers) {
		
		String option = "";
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while(!exit) {
			//display employee login
			loginSuccessEmployee();
			option = sc.next();
			sc.nextLine();
			
			switch(option) {
			case "1":
				employee.viewAllCustomerInformation(customers);
				break;
			case "2":
				employee.viewAllInactiveAccounts(customers);
				break;
			case "3":
				employee.approveAccounts(customers);
				break;
			case "4":
				log.debug(employee.getUsername() + " logged out.");
				exit = true;
				break;
			default:
				System.out.println("INVALID OPTION");
				loginSuccessEmployee();
				break;
			}
			
		}
	}	
	
	private static void loginSuccessAdmin() {
		System.out.println("1. View all accounts");
		System.out.println("2. View unapproved applications");
		System.out.println("3. Approve applications");
		System.out.println("4. Deposit To Customer Checking");
		System.out.println("5. Deposit To Customer Savings");
		System.out.println("6. Withdraw From Customer Checking");
		System.out.println("7. Withdraw From Customer Savings");
		System.out.println("8. Disable Account");
		System.out.println("9. Delete Account");
		System.out.println("10. Logout");
	}
	
public static void loginAdminFunctionality(AdminAccount admin, ArrayList<CustomerAccount> customers) {
		
		String option = "";
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while(!exit) {
			//display employee login
			loginSuccessAdmin();
			option = sc.next();
			sc.nextLine();
			
			switch(option) {
			case "1":
				admin.viewAllCustomerInformation(customers);
				break;
			case "2":
				admin.viewAllInactiveAccounts(customers);
				break;
			case "3":
				admin.approveAccounts(customers);
				break;
			case "4":
				admin.depositToCustomerChecking(customers);
				break;
			case "5":
				admin.depositToCustomerSaving(customers);
				break;
			case "6":
				admin.withdrawFromCustomerChecking(customers);
				break;
			case "7":
				admin.withdrawFromCustomerSavings(customers);
				break;
			case "8":
				admin.disableAccount(customers);
				break;
			case "9":
				admin.deleteAccount(customers);
				break;
			case "10":
				log.debug(admin.getUsername() + " logged out.");
				exit = true;
				break;
			default:
				System.out.println("INVALID OPTION");
				loginSuccessAdmin();
				break;
			}
			
		}
	}

public static void loginJointFunctionality(JointAccount customer) {	

	String option = "";
	boolean exit = false;
	Scanner sc = new Scanner(System.in);
	
		while(!exit) {
			//display menu
			loginSuccessCustomer();
				
			option = sc.next();
			sc.nextLine();
			
			switch(option) {
			case "1":
				customer.withdrawChecking();
				break;
			case "2":
				customer.withdrawSavings();
				break;
			case "3":		
				customer.depositChecking();
				break;
			case "4":
				customer.depositSavings();
				break;
			case "5":
				System.out.println(customer.toString());
				break;
			case "6":
				exit = true;
				break;
			default:
				log.debug(customer.getUsername() + " logged out.");
				System.out.println("INVALID OPTION");
				loginSuccessCustomer();
				break;
			}
			
		}
	}
}
	

