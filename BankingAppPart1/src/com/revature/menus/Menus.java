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
}
