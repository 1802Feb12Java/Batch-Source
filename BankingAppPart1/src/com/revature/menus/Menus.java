package com.revature.menus;

public class Menus {
	public static void displayStartMenu() {
		System.out.println("1. Initiate banking operation");
		System.out.println("2. Backend administration");
		System.out.print("Enter selection: ");
	}
	
	public static void displayBackendAccountCreationMenu() {
		System.out.println("Which type of account would you like to create:");
		System.out.println("  1. Administrative");
		System.out.println("  2. Employee");
		System.out.println("  3. Customer");
		System.out.println("  4. Exit backend administration");
	}
	
	public static void displayLogInMenu() {
		System.out.println("Welcome to Bank of the Citadel");
		System.out.println("  1.  Login existing user");
		System.out.println("  2.  Register new user");
		System.out.println("  3.  Exit");
		System.out.print("  Please select an option: ");	
	}
	
	public static void displayCustomerMenu(String fname, String lname) {
		System.out.println("Welcome, " + fname + " " + lname);
		System.out.println("  1.  Apply for an account");
		System.out.println("  2.  List accounts");
		System.out.println("  3.  View an account");
		System.out.println("  4.  Withdraw from an account");
		System.out.println("  5.  Deposit to an account");
		System.out.println("  6.  Transfer funds");
		System.out.println("  7.  Exit the system");
	}
	
	public static void displayEmployeeMenu(String fname, String lname) {
		System.out.println("Welcome, " + fname + " " + lname);
		System.out.println("  1.  View customer information");
		System.out.println("  2.  View customer account");
		System.out.println("  3.  View pending account applications");
		System.out.println("  4.  Exit the system");
	}
	
	public static void displayAdminMenu(String fname, String lname) {
		System.out.println("Welcome, " + fname + " " + lname);	
		System.out.println("  1.  View customer information");
		System.out.println("  2.  View customer account");
		System.out.println("  3.  View pending account applications");
		System.out.println("  4.  Modify accounts");
		System.out.println("  5.  Exit the system");
		
	}
}
