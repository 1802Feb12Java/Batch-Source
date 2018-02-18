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
