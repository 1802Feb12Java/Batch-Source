package com.revature.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.services.UserServices;

public class Core {
	
	// Instantiate objects and set variables
	Scanner sc = new Scanner(System.in);
	UserServices us = new UserServices();
	int choice = 0;
	String username = "";
	String password = "";
	
	public void adminLogin() throws FileNotFoundException, IOException {
		
		// Get super user login info from database.properties
		Properties prop = new Properties();
		prop.load(new FileReader("src/main/resources/database.properties"));
		String s_username = prop.getProperty("s_usr");
		String s_password = prop.getProperty("s_psw");
		
		// Get username input
		System.out.println();
		System.out.println("Enter your username");
		String usernameInput = sc.next();
		
		// Get password input
		System.out.println();
		System.out.println("Enter your password");
		String passwordInput = sc.next();
		
		// Verification
		if(usernameInput.equals(s_username) && passwordInput.equals(s_password)) {
			System.out.println();
			System.out.println("Welcome admin!");
		}
	}
	
	public void register() {
		System.out.println("Enter your first name:");
		String firstName = sc.next();
		
		System.out.println("Enter your last name:");
		String lastName = sc.next();
		
		System.out.println("Enter your email:");
		String email = sc.next();
		
		System.out.println("Enter your phone number:");
		String phone = sc.next();
		
		System.out.println("Enter your desired username:");
		String username = sc.next();
		
		System.out.println("Enter your password:");
		String password = sc.next();
		
		boolean passwordOk = false;
		while(!passwordOk) {
			if (password.length() < 6) {
				System.out.println("Error! Please choose a password that has at least six characters.");
				password = sc.next();
			} else {
				passwordOk = true;
			}
		}
		
		this.us.insertNewUser(3, firstName, lastName, email, phone, username, password);
	}
	
	public void userAccountFlow() {
		
		User user = null;
		
		boolean validLogin = false;
		while(!validLogin) {
			
			System.out.println("Please enter your username:");
			username = sc.next();
			
			System.out.println("Please enter your password:");
			password = sc.next();
			
			user = us.getUserByUsername(username);
			if(user == null) {
				System.out.println();
				System.out.println("******************************************************************");
				System.out.println("Error! The username or password is incorrect. Please try again.");
				System.out.println("******************************************************************");
			} else {
				if(user.getPassword().equals(password)) {
					validLogin = true;
				} else {
					System.out.println();
					System.out.println("******************************************************************");
					System.out.println("Error! The username or password is incorrect. Please try again.");
					System.out.println("******************************************************************");
				}
			}
		}
		
		if(this.userHasAccount(user.getId())) {
			
		}
		
		
		
		
		boolean loggedIn = true;
		while(loggedIn) {
			
			System.out.println();
			System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
			System.out.println("What would you like to do?");
			System.out.println("1. Make A Deposit");
			System.out.println("2. Make A Deposit");
			
			
			
			
		}
		
		
		
		
	}
	
	public boolean userHasAccount(int id) {
		
		
		
		
		
		return false;
	}
	
	
	
	
	
	
	
	
}
