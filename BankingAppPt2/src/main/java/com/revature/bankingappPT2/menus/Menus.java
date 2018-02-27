package com.revature.bankingappPT2.menus;

public class Menus {

	public static void mainMenu() {
		
		System.out.println(
				  "Welcome to the Bank of Revature\n"
				+ "\n"
				+ "Please choose an option\n"
				+ "(1) Login\n"
				+ "(2) Register an Account with us\n"
				+ "(3) Exit Application");
		
	}
	
	public static void ip() {
		
		System.out.println("\n\n\n\nInvalid Input. Try Again.\n");
	}
	
	public static void getTypeOfUsersMenu() {
		System.out.println(
				"Please choose an account type:"
			+ 	"\n(1) New Customer"
			+ 	"\n(2) New Admin"
			+ 	"\n(3) Exit");
	}
	
}
