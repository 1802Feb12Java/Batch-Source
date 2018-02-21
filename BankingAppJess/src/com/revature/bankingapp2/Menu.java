package com.revature.bankingapp2;

public class Menu {
	public static void mainMenu() {
		System.out.println();
		System.out.println("        Welcome to myFirstBank        ");
		System.out.println();
		System.out.println("    1. Log in.");
		System.out.println("    2. New Customer.");
		System.out.println("    3. Exit.");
		System.out.println();
		System.out.print("Your selection: ");
	}
	
	public static void logInScreen1() {
		System.out.println();
		System.out.println("    Log In");
		System.out.println();
		System.out.print("Username: ");
	}
	
	public static void logInScreen2() {
		System.out.print("Password: ");
	}
	
	public static void newUserScreen1() {
		System.out.println();
		System.out.println("    New User Log In");
		System.out.println();
		System.out.print("Desired Username: ");
	}
	
	public static void newUserScreen2() {
		System.out.print("Password: ");
	}
	
}//end class
