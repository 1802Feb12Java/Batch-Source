package com.revature.bankingappPT2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class SuperUserDriver {

	public SuperUserDriver() {
		super();
	}

//	private String username;
//	private String password;
//	private ArrayList<Account> listOfAccounts;
//	private Integer userAcess;
//	private Integer customerID;
	private static Scanner sysScanner;
	private static User admin;
	
	public static void mainMenu(User admin, Scanner sysScanner) throws SQLException {
		SuperUserDriver.sysScanner = sysScanner;
		SuperUserDriver.admin = admin;
		enterMainMenu();
		return;
	}

	private static void enterMainMenu() throws SQLException {
		System.out.println("Welcome Admin "+admin.getUsername()+"\n"
							+"What would you like to do?\n"
							+ "(1) View Account Information\n"
							+ "(2) Create an Account\n"
							+ "(3) Delete an Account\n"
							+ "(4) Update an Account\n"
							+ "(5) Logout");
		ArrayList<String[]> userInfo = UserDriver.getDao().getAllUserInformation();
		switch(UserDriver.getInput(sysScanner)) {
		
		case 1:
			viewUserDetails(userInfo);
			enterMainMenu();
			return;
		case 2:
			adminCreateAccount();
			enterMainMenu();
			return;
		case 3:
			
			deleteAccount(userInfo);
			enterMainMenu();
			return;
		case 4:
			updateUser(userInfo);
			enterMainMenu();
			return;
		case 5:	//Admin log out
			System.out.println("Log out Complete");
			return;		
		}
		Integer input = UserDriver.getInput(sysScanner);
	}

	private static void updateUser(ArrayList<String[]> userInfo) throws SQLException {
		System.out.println("Choose a user that you want to change.");
		Integer counter = displayAllUsers(userInfo);
		Integer input = UserDriver.getInput(sysScanner);
		if(input < 0 || input > counter) {
			System.out.println("\n\n\n\nInvalid Input.");
			updateUser(userInfo);
			return;
		} else if (input == counter) {
			return; 
		} else {
			updateUserP2(userInfo.get(input)[0], userInfo);
		}
		
	}

	private static void updateUserP2(String username, ArrayList<String[]> userInfo) throws SQLException {
		System.out.println("Choose an action to take on the user\n"
				+ "(1) Change Username\n"
				+ "(2) Change Password\n"
				+ "(3) Update User's bank account\n"
//				+ "(4) Withdraw from an account\n"
//				+ "(5) Transfer from an account\n"
				+ "(4) Exit");
		
		switch(UserDriver.getInput(sysScanner)) {
		
		case 1:
			System.out.println("What is the new username for this account?\n(N) Exit");
			String input = sysScanner.next();
			if (input.equalsIgnoreCase("n")) {
				return;
			}
			UserDriver.getDao().updateAccountUsernameAndPassword(username, input, "");
			
			return;
		case 2:
			System.out.println("What is the new password for this account?\n(N) Exit");
			input = sysScanner.next();
			if (input.equalsIgnoreCase("n")) {
				return;
			}
			UserDriver.getDao().updateAccountUsernameAndPassword(username, username, input);
			return;
		case 3:
			User user = UserDriver.getDao().rebuildUserAccount(username);
			//System.out.println("Which account would you like to make changes to?");

			//Account workingWith;
			int counter = 0;
			ArrayList<Account> accounts = user.getListOfAccounts();
			//print out all accounts the user has.
			if (accounts.size() < 1) {
				System.out.println("No active accounts.");
				return;
			} else {
				System.out.println("Which account would you like to make changes to?");
				for (Account acc : accounts) {
					System.out.println("(" + counter + ") "+acc.getAccountName()+" - Balance: $"+acc.getBalance());
					counter++;
				}
				System.out.println("\n(N) Exit");
			}
			
			//picking the account, need an int to pull from an index of account
			input = sysScanner.next();
			sysScanner.nextLine();
			
			if (input.equalsIgnoreCase("n")) {
				return;
			}
			try {
				int intput = Integer.parseInt(input);
				
				if (intput < 0 || intput >= counter) {
					System.out.println("Please enter a valid input");
					updateUserP2(username, userInfo);
				} else {
					//System.out.println("\ninsideAccount();");
					CustomerDriver.insideAccount(accounts.get(intput), sysScanner);
					updateUserP2(username, userInfo);
				}
			} catch (Exception e){
				System.out.println("Invalid input.");
				updateUserP2(username, userInfo);
			}
			return;
			
//			System.out.println(UserDriver.getDao().getAccountDetails(username));
//			System.out.println("Which account ID would you like to deposit into?\n (N) Exit");
//			input = sysScanner.next();
//			if(input.equalsIgnoreCase("n")) {
//				return;
//			}
//			try {
//				Integer accID = Integer.parseInt(input);
//			} catch (Exception e) {
//				System.out.println("\n\n\nInvalid Input.");
//				updateUserP2(username, userInfo);
//				return;
//			}
//			System.out.println("How much do you want to deposit?");
//			Double deposit = UserDriver.getDouble(sysScanner);
//			Double balance = Double.parseDouble(parseDAOString(username));
//			//System.out.println("DONT GO PAST THIS: "+(balance+deposit));
//			input = sysScanner.next();
//			UserDriver.getDao().updateAccountBalance(balance+deposit, 10);
			//break;
		case 4:
			//counter = displayAllUsers(userInfo);
			return;
		default:
			System.out.println("\n\nInvalid Input.");
			updateUserP2(username, userInfo);
		}
		
		
		
	}

	private static void deleteAccount(ArrayList<String[]> userInfo) throws SQLException {
		System.out.println("All Users");
		
		int counter = 0;
		for (String[] username: userInfo) {
			System.out.println("("+(counter++) + ") " +username[1]);
		}
		System.out.println("("+counter+") Exit\nChoose a User to view their account(s)");
		Integer input = UserDriver.getInput(sysScanner);
//			if (UserDriver.getBank().getUserAccounts().get(userInfo.get(input)[0]).getUserAccess()==1) {
//				System.out.println("You chose an admin account, please choose a non-admin account.");
		if(input < 0 || input > counter) {
			System.out.println("Invalid Input.");
			deleteAccount(userInfo);
			return;
		} else if (input == counter) {
			return;
		}
		if(userInfo.get(input)[0].equals(admin.getUsername())) {
			System.out.println("\n\n\n\nYou can't delete an account you are logged into.");
			deleteAccount(userInfo);
			return;
		}
		UserDriver.getDao().deleteUserAccount(userInfo.get(input)[0]);
		return;		
	}

	private static void adminCreateAccount() throws SQLException {
		System.out.println("\nWhat kind of account would you like to make?\n"
				+ "(1) Customer\n"
				+ "(2) Admin\n"
				+ "(3) Exit");
		switch(UserDriver.getInput(sysScanner)) {
		case 1:
			createNewUser(1);
			enterMainMenu();
			return;
		case 2:
			createNewUser(2);
			enterMainMenu();
			return;
		case 3:
			enterMainMenu();
			return;
		default:
			System.out.println("Invalid Input");
			adminCreateAccount();
			return;
		}
		
	}

	private static void createNewUser(int userType) throws SQLException {
		User newUser = null;
		
		String uName;
		String passWord;
		
		System.out.println("Please enter a username for the account.");
		uName = sysScanner.next();
		
		if(UserDriver.doesUsernameExist(uName)) {
			System.out.println("That username is already in use.  Please choose another");
			createNewUser(userType);
			return;
		}
		
		
		passWord = UserDriver.validatePassword(uName);
		if (passWord.equalsIgnoreCase("")) {
			createNewUser(userType);
		}
		if (userType == 1) {
				newUser = new Customer(uName, passWord);
				UserDriver.getDao().addCustomerToTable(newUser);
				UserDriver.getBank().addUser(newUser);
				System.out.println("Account creation complete");

		} else if (userType == 2) {
				newUser = new Customer(uName, passWord, 2);
				UserDriver.getDao().addCustomerToTable(newUser);
				UserDriver.getBank().addUser(newUser);
				System.out.println("Account creation complete");

		}
		if (newUser.equals(null)) {
			return;
		} else {
			UserDriver.getBank().addUser(newUser);
			return;
		}
		
	}

	private static void viewUserDetails(ArrayList<String[]> userInfo) throws SQLException {
		System.out.println("All Users");
		int counter = 0;
		for (String[] username: userInfo) {
			System.out.println("("+(counter++) + ") " +username[1]);
		}
		System.out.println("Choose a User to view their account(s)\n"
				+ "("+counter+") Exit");
		Integer input = UserDriver.getInput(sysScanner);
//			if (UserDriver.getBank().getUserAccounts().get(userInfo.get(input)[0]).getUserAccess()==1) {
//				System.out.println("You chose an admin account, please choose a non-admin account.");
		if(input < 0 || input > counter) {
			System.out.println("Invalid Input.");
			viewUserDetails(userInfo);
			return;
		} else if (input == counter) {
			return;
		}
		System.out.println(UserDriver.getDao().getAccountDetails(userInfo.get(input)[0]));
		System.out.println("Press any key to contine");
		String strput = sysScanner.next();
		strput = sysScanner.nextLine();
		return;
	}
	
	private static Integer displayAllUsers(ArrayList<String[]> userInfo) {
		System.out.println("All Users");
		
		int counter = 0;
		for (String[] username: userInfo) {
			System.out.println("("+(counter++) + ") " +username[1]);
		}
		System.out.println("("+counter+") Exit\nChoose a User to view their account(s)");
		return counter;
	}
	
	private static String parseDAOString(String username) throws SQLException {
		String output = UserDriver.getDao().getAccountDetails(username);
		String[] outputs = output.split("::");
		for (String str: outputs) {
			//System.out.println(str);
			if(str.contains("Balance")) {
				outputs = str.split(":");
				//System.out.println(outputs[1].trim().replace("$", ""));
				output = outputs[1].trim().replace("$", "");
				//System.out.println(outputs[1].trim());
			}
		}
		return output;
		
	}
}
