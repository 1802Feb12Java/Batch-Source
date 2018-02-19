package com.revature.bankingapp;

import java.util.Scanner;

public class MenuSystem {
	private static Scanner scanner = new Scanner(System.in);
	public static void runMenu() {
		scanner = new Scanner(System.in);
		System.out.println("Welcome to Janky Bank");
		System.out.println("Enter a numerical option to run function");
		System.out.println("1. Customer Options");
		System.out.println("2. Employee Options");
		System.out.println("3. Administrator Options");
		System.out.println("4. Exit Program");
		int userOption = scanner.nextInt();
		scanner.nextLine();		
		switch (userOption) {
		case 1:
			displayCustomerOptions();
			break;
		case 2:
			displayEmployeeOptions();
			break;
		case 3:
			displayAdminOptions();
			break;
		case 4:
			System.exit(0);
		default:
			System.out.println("Something went terribly wrong!");
			runMenu();
			break;
		}
	}
	
	private static void displayAdminOptions() {
		System.out.println("Admin Menu");
		System.out.println("What would you like to do? Please enter a numerical value.");
		System.out.println("1. Register a new employee");
		System.out.println("2. View employee information");
		System.out.println("3. View, edit, or cancel customer account");
		int userOption = scanner.nextInt();
		scanner.nextLine();
		AdminAccount adminAccount = new AdminAccount();
		
		switch (userOption) {
		case 1:
			adminAccount.registerEmployee();
			break;
		case 2:
			adminAccount.viewEmployeeInformation();
			break;
		case 3:
			displayEditsFromAdminAccount();
			break;
		default:
			break;
		}
	}
	private static void displayEditsFromAdminAccount() {
		System.out.println("Enter a number to perform an action on a customer account");
		System.out.println("1. Adjust balance");
		System.out.println("2. Force Transfer");
		System.out.println("3. Delete Customer Account");
		System.out.println("4. Approve Customer Account");
		int userOption = scanner.nextInt();
		scanner.nextLine();
		AdminAccount adminAccount = new AdminAccount();
		
		switch (userOption) {
		case 1:
			adminAccount.adjustBalance();
			break;
		case 2:
			adminAccount.forceTransfer();
			break;
		case 3:
			adminAccount.deleteAccount();
			break;
		case 4:
			adminAccount.approveAccounts();
			break;
		default:
			break;
		}
		
	}

	private static void displayEmployeeOptions() {
		System.out.println("Employee Menu");
		System.out.println("What would you like to do? Please enter a numerical value.");
		System.out.println("1. View customer information");
		System.out.println("2. Approve customer accounts");
		System.out.println("3. Go back to main menu");
		int userOption = scanner.nextInt();
		scanner.nextLine();
		
		EmployeeAccount employeeAccount = new EmployeeAccount();
		
		switch (userOption) {
		case 1:
			employeeAccount.viewCustomerInfo();
			break;
		case  2:
			employeeAccount.approveCustomerAccount();
			break;
		case 3:
			MenuSystem.runMenu();
			break;
		default:
			System.out.println("Something went horribly wrong!");
			MenuSystem.runMenu();
			break;
		}
	}

	private static void displayCustomerOptions() {
		System.out.println("Customer Menu");
		System.out.println("What would you like to do? Please enter a numerical value.");
		System.out.println("1. Register");
		System.out.println("2. View your information");
		System.out.println("3. View your accounts");
		System.out.println("4. Open new account");
		System.out.println("5. Make a withdrawl");
		System.out.println("6. Make a deposit");
		System.out.println("7. Make a transfer");
		System.out.println("8. Go back to main menu");
		int userOption = scanner.nextInt();
		scanner.nextLine();
		
		CustomerInformation customerInformation = new CustomerInformation();
		CustomerAccount customerAccount = new CustomerAccount();
		switch (userOption) {
		case 1:
			customerInformation.enterCustomerInformation();
			break;
		case 2:
			customerInformation.viewCustomerInformation();
			break;
		case 3:
			customerAccount.viewCustomerAccounts();
			break;
		case 4:
			customerAccount.openCustomerAccount();
			break;
		case 5:
			customerAccount.withdrawMoney();
			break;
		case 6:
			customerAccount.depositMoney();
			break;
		case 7:
			customerAccount.transferMoney();
			break;
		case 8:
			runMenu();
			break;

		default:
			break;
		}
	}
}
