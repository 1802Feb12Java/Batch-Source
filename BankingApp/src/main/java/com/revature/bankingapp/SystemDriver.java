package com.revature.bankingapp;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import com.revature.bankingapp.users.*;
//import com.revature.bankingapp.users.Customer;
//import com.revature.bankingapp.users.Employee;
//import com.revature.bankingapp.users.User;
//import com.revature.bankingapp.users.Users;

public class SystemDriver {

	private static boolean running;
	private static Scanner systemScanner;
	private static Users users;
	public static File initFile;
	
	public static void main(String[] args) {

		//initialize Scanner and User accounts
		systemScanner = new Scanner(System.in);
		//Users users = new Users(); //do this to initialize the arrays.
		//File initFile = new File("init.txt");

		users = Users.initializeAllUsers();	
		
		running = true;
		System.out.println("Welcome to the REVATURE Banking Application.");
		
		do {
		
			System.out.println("Login (Y)?\nRegister (R)\nExit Application(n)");
			String userInput = systemScanner.next();
			
			//User has input that they wish to log in.
			if(userInput.equalsIgnoreCase("y")) {
				logIntoUser();

			//User has input that they wish to exit the program.	
			} else if (userInput.equalsIgnoreCase("n")) {
				System.out.println("Exiting Application.");
				
				running = false;
						
			
			//User did not input a valid input. Asking to try again.
			} else if (userInput.equalsIgnoreCase("r")) {
				getTypeOfUser();
			} else {
				System.out.println("Please enter a valid option: Please Try Again");
			}

		}while (running);
			
			
	}

	public static boolean isRunning() {
		return running;
	}
	

	public static void getTypeOfUser() {
		
		String userType;
		
		do {
			System.out.println("Please choose an account type:\nNew Customer 'C'\nNew Employee 'E'\nNew Admin 'A'\nOr use 'N' to exit");
			userType = systemScanner.next();
			if (userType.equalsIgnoreCase("c")) {
				createNewUser(1);
				return;
			} else if (userType.equalsIgnoreCase("E")) {
				createNewUser(2);
				return;
			} else if (userType.equalsIgnoreCase("a")) {
				createNewUser(3);
				return;
			} else if(userType.equalsIgnoreCase("n")){
				return;
				
			} else {
				System.out.println("Please enter a valid option.");
			}
		} while (running);
		
	}
	
	
	//REGISTER A NEW ACCOUNT
	public static void createNewUser(int userType) {
		
		User newUser = null;
		
		String uName;
		String passWord;
		
		System.out.println("Please enter a username for your account.");
		uName = systemScanner.next();
		
		//check to see if the account name already exists
		for (User user : users.getUsers()) {
			if (uName.equals(user.getUserName())) {
				System.out.println("That username already is in use.  Please choose another");
				createNewUser(userType);
			}
		}
		
		passWord = validatePassword(uName);
		
		if (userType == 1) {
				newUser = new Customer(uName, passWord, users);
				
		} else if (userType == 2) {
				newUser = new Employee(uName, passWord, users);
				
		} else if (userType == 3) {
				newUser = new Admin(uName, passWord, users);
				
		}
		if (newUser.equals(null)) {
			return;
		} else {
			users.addUser(newUser);
			enterUser(newUser);
		}
		return;
	}
	
	public static void enterUser(User newUser) {
		newUser.userDriverStart(systemScanner);
	}
	
	public static String validatePassword(String uName) {
		
		
		String passWord; //good old none encrypted passwords
		String valPW;
		
		
		System.out.println(uName + " please enter a password.");
		passWord = systemScanner.next();
		System.out.println("Please re-enter you password.");
		valPW = systemScanner.next();
		
		if (passWord.equals(valPW)) {
			return passWord;
		} else {
			return validatePassword(uName);
		}
		
	}
	
	public static void logIntoUser() {
				
		System.out.println("Please enter you username.\nExit (N)");
		String username = systemScanner.next();
		System.out.println("Please enter your password.\nExit (N)");
		String password = systemScanner.next();

		if (username.equalsIgnoreCase("n") || password.equalsIgnoreCase("n")) {
			return;
		} else if (username.equalsIgnoreCase("r") || password.equalsIgnoreCase("r")) {
			getTypeOfUser();
			return;
		} else {
			//System.out.println(Users.getUsers().size());
			for(User user: users.getUsers()) {
				if(user.getUserName().equals(username)) {
					//System.out.println("Found user. Please enter your password.\nExit (N)"); BAD JUJU to tell user account exists
					if (user.getPassWord().equals(password)) {
						user.userDriverStart(systemScanner);
						return;
					} 
				}				
			}
			System.out.println("Username or Password are incorrect.\n");
			System.out.println("Register an account with us (R)");
			logIntoUser();
			
		}
		
	}

	public static File getInitFile() {
		return initFile;
	}
	
	
	/*public static boolean checkPasswords(User user, String password) {
		
		if(user.getPassWord().equals(password)) {
			return true;
		} else {
			System.out.println("Password or username incorrect");
		}
		
		
		return false;
		
	}*/
	
	
}
