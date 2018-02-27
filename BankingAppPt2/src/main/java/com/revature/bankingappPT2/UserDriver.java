package com.revature.bankingappPT2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.bankingappPT2.menus.Menus;

public class UserDriver {

	private static Bank bank;
	private static UserDAObject dao;
	private static boolean running;
	private static Scanner sysScanner;
	
	public static void main(String[] args) throws SQLException {
		
		dao = new UserDAObject();
		//temporary instantiate bank
		bank = new Bank();
		try {
			bank.getUserAccounts().get("Admin");
			
		} catch (Exception e) {
			//useless old code, afraid to remove it though
			Customer admin = new Customer("Admin", "b");
			bank.addUser(admin);
		}
		running = true;
		sysScanner = new Scanner(System.in);
		
		do {
			Menus.mainMenu();
			
			switch(getInput(sysScanner)) {
			case 1:
				logIntoUser();
				break;
			case 2:
				//Register
				getTypeOfUser();
				break;
			case 3:
				running = false;
				System.out.println("\n\nThank you for using Bank of Revature!");
				break;
			default:
				System.out.println("Invalid Input. Try Again.\n\n\n");
				break;
			}
			
		} while (running);
	}
	//Used to validate an integer was input and deals with exceptions thrown
	public static Integer getInput(Scanner sysScanner) {
		try {
			if(sysScanner.hasNextInt()) {
				Integer input = sysScanner.nextInt();
				//System.out.println("Input: "+input);
				return input;
			} else {
				sysScanner.next();
			}
		} catch (Exception e) {
			
			System.out.println("INPUT Invalid option. Try Again.\n\n\n");
			//sysScanner.nextLine();
			return getInput(sysScanner);
			//e.printStackTrace();
		}
		return -1;
	}
	//Login transition
	public static void logIntoUser() throws SQLException {
		
		System.out.println("Please enter you username.\n(N) Exit");
		String username = sysScanner.next();
		//Exit early
		if (username.equalsIgnoreCase("n")) {
			return;
		} else if (username.equalsIgnoreCase("r")) {
			getTypeOfUser();
			return;
		}
		//Get password, validate, then proceed to enter.
		System.out.println("Please enter your password.\n(N) Exit");
		String password = sysScanner.next();

		if (password.equalsIgnoreCase("n")) {
			return;
		} else if (password.equalsIgnoreCase("r")) {
			getTypeOfUser();
			return;
		} else {
			//check to see if user exists
			if (doesUsernameExist(username)) {
				//System.out.println("Username Found-UserDriver");
				if(dao.validatePassword(username, password)) {
					//System.out.println("Password Correct- Userdriver");
					User user = isUserInstantiated(username);
					if(user == null){
						//System.out.println("Object was null");
						user = dao.rebuildUserAccount(username);
						
					} 
					//System.out.println("Should enter user");
					enterUser(user);
					return;
				}
			}
			
			System.out.println("Username or Password are incorrect.\n");
			System.out.println("Register an account with us (R)");
			return;
		}
	}
	//Get the type of User for registration purposes
	public static void getTypeOfUser() throws SQLException {
		Integer userType;
		
		Menus.getTypeOfUsersMenu();
		userType = getInput(sysScanner);
		if (userType > 0 && userType < 3) {
			createNewUser(userType);
		} else if(userType == 3) {
			return;
		} else {
			Menus.ip();
			getTypeOfUser();
			return;
		}
	}
	public static void createNewUser(int userType) throws SQLException {
		User newUser = null;
		
		String uName;
		String passWord;
		
		System.out.println("Please enter a username for your account.");
		uName = sysScanner.next();
		
		if(doesUsernameExist(uName)) {
			System.out.println("That username is already in use.  Please choose another");
			createNewUser(userType);
			return;
		}
		
		passWord = validatePassword(uName);
		if (passWord.equalsIgnoreCase("")) {
			createNewUser(userType);
		}
		if (userType == 1) {
				newUser = new Customer(uName, passWord);
				dao.addCustomerToTable(newUser);
				//Remove bank later
				bank.addUser(newUser);
				enterUser(newUser);
				//System.out.println("Steppingthrough");
		} else if (userType == 2) {
				newUser = new Customer(uName, passWord, 2);
				dao.addCustomerToTable(newUser);
				bank.addUser(newUser);
				enterUser(newUser);
				
		} else if (userType == 3) {
				//newUser = new Admin(uName, passWord, users);
				
		}
		if (newUser.equals(null)) {
			return;
		} else {
			bank.addUser(newUser);
			return;
		}
	}
	public static String validatePassword(String uName) {
		
		String passWord; //good old none encrypted passwords
		String valPW;
		
		System.out.println("\n"+ uName + " please enter a password.\n(N) Exit");
		passWord = sysScanner.next();
		if (passWord.equalsIgnoreCase("n")) {
			return "";
		}
		System.out.println("\nPlease re-enter the password.");
		valPW = sysScanner.next();
		
		if (passWord.equals(valPW)) {
			return passWord;
		} else {
			return validatePassword(uName);
		}
		
	}
	public static void enterUser(User user) throws SQLException {
		switch(user.getUserAccess()) {
		case 1:
			//System.out.println("Should Enter Customer");
			CustomerDriver.mainMenu(user, sysScanner);
			break;
		case 2:
			SuperUserDriver.mainMenu(user, sysScanner);
			break;
		default:
			break;
		}
		return;
	}
	public static Bank getBank() {
		return bank;
	}
	public static Double getDouble(Scanner sysScanner) {
		try {
			if(sysScanner.hasNextDouble()) {
				Double input = sysScanner.nextDouble();
				//System.out.println("Input: "+input);
				return input;
			} else {
				sysScanner.next();
			}
		} catch (Exception e) {
			//System.out.println("INPUT Invalid option. Try Again.\n\n\n");
			return -1.0D;
			//e.printStackTrace();
		}
		return 0.00D;
	}
	public static Boolean withdraw(Account workingWith, Double withdraw) {
		//removes withdraw amount from current balance
		Double newBal = workingWith.getBalance() - withdraw;
		if(newBal < 0.00) {
			System.out.println("Not enough funds to enact withrdraw.");
			return false;
		} else {
			workingWith.setBalance(newBal);
			return true;
		}
		
		
	}
	public static void deposit(Account workingWith, Double depo) {
		//adds to account
		workingWith.setBalance(workingWith.getBalance() + depo);
		
	}
	public static Boolean transfer(Account acc1, User user2, Account acc2, Double amount) {
		/*
		 * Transfer FROM acc1
		 * TO acc2
		 * 
		 * transfers from active account to new account
		 * this.acc1 - amount -> user2.acc2 + amount
		 * 
		 */
		
		if(withdraw(acc1, amount)) {
			deposit(acc2, amount);
			System.out.println("Transfer complete: $"+amount+" moved from "+acc1.getAccountName()+" to "+user2.getUsername()+"'s account "+acc2.getAccountName());
			return true;	
		} else return false;
		
	}
	public static Boolean doesUsernameExist(String username) throws SQLException {
		
		if(bank.getUserAccounts().containsKey(username)) {
			return true;
		} else if (dao.userAccountExists(username)) {
			return true;
		}
		return false;
	}
	private static User isUserInstantiated(String username) {
		if (bank.getUserAccounts().containsKey(username)) {
			return bank.getUserAccounts().get(username);
		} else {
			return null;
		}
	}
	public static UserDAObject getDao() {
		return dao;
	}
}
