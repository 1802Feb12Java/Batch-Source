package com.revature;

public final class Menu {
	
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
	 * Print out the User's Main menu
	 * options:
	 * 		View Accounts with Balances
	 * 		Create an Account
	 * 		Delete an Account ONLY if its empty
	 * 		Withdraw from Account
	 * 		Deposit to Account
	 * 		Transfer from Account to Account
	*/
	public void printClient() {
		System.out.println("=========== Customer Menu ==========");
		System.out.println("|                                   |");
		System.out.println("|        1. View Accounts           |");
		System.out.println("|        2. Create Account          |");
		System.out.println("|        3. Delete Account          |");
		System.out.println("|        4. Withdraw                |");
		System.out.println("|        5. Deposit                 |");
		System.out.println("|        6. Transfer                |");
		System.out.println("|        7. Log Out                 |");
		System.out.println("|                                   |");
		System.out.println("=====================================");
	}
	
	/*
	 * Print out the Admin's Main menu
	 * options:
	 * 		1. View All Users
	 * 		2. Create User
	 * 		3. Modify User
	 * 		4. Delete User
	 * 		5. View All Accounts with Balances
	 * 		6. Create an Account
	 * 		7. Delete an Account
	 * 		8. Withdraw from Account
	 * 		9. Deposit to Account
	 * 		10. Transfer from Account to Account
	 * 		11. Logout
	*/
	public void printAdmin() {
		System.out.println("============= Admin Menu ============");
		System.out.println("|                                   |");
		System.out.println("|        1. View All Users          |");
		System.out.println("|        2. Create User             |");
		System.out.println("|        3. Modify User             |");
		System.out.println("|        4. Delete User             |");
		System.out.println("|        5. View All Accounts       |");
		System.out.println("|        6. Create an Account       |");
		System.out.println("|        7. Delete an Account       |");
		System.out.println("|        8. Withdraw from Account   |");
		System.out.println("|        9. Deposit to Account      |");
		System.out.println("|        10. Make a Transfer        |");
		System.out.println("|        11. Logout                 |");
		System.out.println("|                                   |");
		System.out.println("=====================================");
	}
	
}
