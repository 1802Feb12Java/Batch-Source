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
		// TODO Auto-generated method stub
		
	}

	private static void displayEmployeeOptions() {
		// TODO Auto-generated method stub
		
	}

	private static void displayCustomerOptions() {
		System.out.println("What would you like to do? Please enter a numerical value.");
		System.out.println("1. Register a new customer");
		System.out.println("2. View customer information");
		System.out.println("3. Open new account");
		System.out.println("4. Make a withdrawl");
		System.out.println("5. Make a deposit");
		System.out.println("6. Make a transfer");
		System.out.println("7. Go back to main menu");
		int userOption = scanner.nextInt();
		scanner.nextLine();
		
		CustomerAccount customerAccount = new CustomerAccount();
		switch (userOption) {
		case 1:
			customerAccount.enterCustomerInformation();
			break;
		case 2:
			customerAccount.viewCustomerInformation();
			break;
		case 3:
			customerAccount.openCustomerAccount();
			break;
		case 4:
			customerAccount.withdrawMoney();
			break;
		case 5:
			customerAccount.depositMoney();
			break;
		case 6:
			customerAccount.transferMoney();
			break;
		case 7:
			runMenu();
			break;

		default:
			break;
		}
	}
}
