package com.revature.main;

import java.util.Scanner;

import com.revature.beans.User;

public class Menu {
	
	User user = null;
	Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		System.out.println();
		System.out.println("*******************************************************");
		System.out.println("************* WELCOME TO BANK OF BOUDREAU *************");
		System.out.println("*******************************************************");
		System.out.println("Please choose an action below:");
		System.out.println("1. Login as Admin");
		System.out.println("2. Login as Customer");
		System.out.println("3. Create an Account");
		System.out.println("4. Exit");
	}
	
	public void customerMainMenu() {
		
		
		System.out.println();
		System.out.println("Please choose an action");
		System.out.println("1. Apply for an account");
		System.out.println("2. Logout");
		
		
	}
	
	public void customerPersonalInfo() {
		System.out.println();
		boolean mainMenu = false;
		int choice = 0;
		while(!mainMenu) {
			System.out.println("******************************************************************");
			System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
			System.out.println("Email: " + user.getEmail());
			System.out.println("Username: " + user.getUsername());
			System.out.println("Password: " + user.getPassword());
			System.out.println("******************************************************************");
			
			System.out.println("Actions:");
			System.out.println("1. Main Menu");
			System.out.println("******************************************************************");
			choice = sc.nextInt();
			
			if(choice == 1) {
				mainMenu = true;
			} else {
				System.out.println();
				System.out.println("******************************************************************");
				System.out.println("Error! Invalid option.");
			}
		}
	}
	
	

}
