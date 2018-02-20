package com.revature;

public class Menu {
	
	/*
	 * Print out the Default Main menu
	 * options to login as Different level of users and exit the application
	*/
	public void printMain() {
		System.out.println("================ Menu ===============");
		System.out.println("|                                   |");
		System.out.println("|        1. Login                   |");
		System.out.println("|        2. Register                |");
		System.out.println("|        3. Exit                    |");
		System.out.println("|                                   |");
		System.out.println("=====================================");
	}

	/*
	 * Print out the Customer's Main menu
	 * options:
	 * 		Withdraw
	 * 		Deposit
	 * 		Transfer
	 * 		Apply for Joint Account
	*/
	public void printClient() {
		System.out.println("=========== Customer Menu ==========");
		System.out.println("|                                   |");
		System.out.println("|        1. View Balance            |");
		System.out.println("|        2. Withdraw                |");
		System.out.println("|        3. Deposit                 |");
		System.out.println("|        4. Transfer                |");
		System.out.println("|        5. Apply for new Acc.      |");
		System.out.println("|        6. Apply for Joint Acc.    |");
		System.out.println("|        7. Exit                    |");
		System.out.println("|                                   |");
		System.out.println("=====================================");
	}
	
	/*
	 * Print out the Employee's Main menu
	 * options:
	 * 		View Client info
	 * 		Approve Account Creation
	 * 		Approve Join Account Application
	*/
	public void printEmployee() {
		System.out.println("=========== Employee  Menu ==========");
		System.out.println("|                                   |");
		System.out.println("|        1. View Customer Info      |");
		System.out.println("|        2. Acc. Creation App.      |");
		System.out.println("|        3. Joint Acc. App.         |");
		System.out.println("|        4. Withdraw                |");
		System.out.println("|        5. Deposit                 |");
		System.out.println("|        6. Transfer                |");
		System.out.println("|        7. Exit                    |");
		System.out.println("|                                   |");
		System.out.println("=====================================");
	}
	
	/*
	 * Print out the Admin's Main menu
	 * options:
	 * 		Approve/Deny Account Applications
	 * 		Approve/Deny Join Account Applications
	 * 		Withdraw
	 * 		Deposit
	 * 		Transfer
	 * 		Close Account
	*/
	public void printAdmin() {
		System.out.println("============= Admin Menu ============");
		System.out.println("|                                   |");
		System.out.println("|        1. View Customer Info      |");
		System.out.println("|        2. Acc. Creation App.      |");
		System.out.println("|        3. Joint Acc. App.         |");
		System.out.println("|        4. Withdraw                |");
		System.out.println("|        5. Deposit                 |");
		System.out.println("|        6. Transfer                |");
		System.out.println("|        7. Close Account           |");
		System.out.println("|        8. Exit                    |");
		System.out.println("|                                   |");
		System.out.println("=====================================");
	}
	
	
	
}
