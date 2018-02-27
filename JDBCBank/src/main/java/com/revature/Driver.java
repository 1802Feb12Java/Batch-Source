package com.revature;

import com.revature.beans.*;
import com.revature.util.LogicDriver;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Driver {
	
	//static scanner for the whole driver class
	private static Scanner sc = new Scanner(System.in);
	
	//static menu object to print menu 
	private static Menu menu = new Menu();
	
	//static logicDriver
	private static LogicDriver logicDriver = new LogicDriver();
	
	//Log4J object in Driver
	private final static Logger logger = Logger.getLogger(Driver.class);
	
	
	//Prompts the user for username and password to login
	private static User login(User u) {
		System.out.println("Enter Username: ");
		String usr = sc.nextLine();
		System.out.println("Enter PassWord: ");
		String pw = sc.nextLine();
		logger.info("Logging in user " + usr);
		
		//make SQL call to find user/pw match
		logger.info("Validating...");
		u = logicDriver.validate(usr, pw, u);
		return u;
	}
	
	/*
	 * Prompts the user for all information needed to register
	 * will re-prompt for invalid username.
	 * return true when success added to DB
	 * return false when SQLDriver.register have a problem
	*/
	private static boolean register() {
		//set up for username and password loop
		String usr = "", pw = "";
		do {
			System.out.println("Please enter a new username: ");
			usr = sc.nextLine();
			System.out.println("Please enter a new password: ");
			pw = sc.nextLine();
		}while(logicDriver.checkUser(usr));
		
		//get the rest of the info from stdin
		//fist and last name
		System.out.println("First Name: ");
		String fname = sc.nextLine();
		System.out.println("Last Name: ");
		String lname = sc.nextLine();
		//address, city, state, zip, country
		System.out.println("Street Address: ");
		String addr = sc.nextLine();
		System.out.println("City: ");
		String city = sc.nextLine();
		System.out.println("State: ");
		String state = sc.nextLine();
		System.out.println("Zip Code: ");
		String zip = sc.nextLine();
		System.out.println("Country: ");
		String country = sc.nextLine();
		//phone
		System.out.println("Phone Number: ");
		String phone = sc.nextLine();
		
		//address, city, state, zip, country
		StringBuilder fullAddr = 
				new StringBuilder(addr + ", " + city + ", " + state + ", " + zip + ", " + country);
		User u = new User(0, 0, fname, lname, fullAddr.toString(), phone);
		return logicDriver.register( u, usr, pw);
	}
	
	/*
	 * Prompts the normal user for options
	 * calls respected LogicDriver methods
	 * options:
	 * 		1. View Accounts with Balances
	 * 		2. Create an Account
	 * 		3. Delete an Account ONLY if its empty
	 * 		4. Withdraw from Account
	 * 		5. Deposit to Account
	 * 		6. Transfer from Account to Account
	 * 		7. Log out
	*/
	private static void runClient(User u) {
		boolean running = true;
		while(running) {
			menu.printClient();
			String option = sc.nextLine();
			if(option.equals("1")) {
				logicDriver.viewAccounts(u);
			}
			else if(option.equals("2")) {//create account
				//account number
				System.out.println("Enter Account Number: ");
				String accNum = sc.nextLine();
				Account a = new Account();
				a.setOwner(u);
				a.setJointOwner(u);
				a.setAccountNumber(accNum);
				logicDriver.createAccount(a);
			}
			else if(option.equals("3")) {//delete
				System.out.println("Which account would you like to delete?");
				String acc = sc.nextLine();
				logicDriver.deleteEmpty(acc);
			}
			else if(option.equals("4")) {//withdraw
				System.out.println("Which account would you like to withdraw from? ");
				String fromAcc = sc.nextLine();
				System.out.println("How much would you like to withdraw? ");
				double amount = sc.nextDouble();
				sc.nextLine();
				logicDriver.withdraw(fromAcc,amount);
				
			}
			else if(option.equals("5")) {//deposit
				System.out.println("Which account would you like to deposit to? ");
				String toAcc = sc.nextLine();
				System.out.println("How much would you like to deposit? ");
				double amount = sc.nextDouble();
				sc.nextLine();
				logicDriver.deposit(toAcc,amount);
			}
			else if(option.equals("6")) {//transfer
				System.out.println("Which account would you like to withdraw from? ");
				String fromAcc = sc.nextLine();
				System.out.println("Which account would you like to deposit to? ");
				String toAcc = sc.nextLine();
				System.out.println("How much would you like to transfer? ");
				double amount = sc.nextDouble();
				sc.nextLine();
				
				logicDriver.transfer(fromAcc, toAcc, amount);
			}
			else if(option.equals("7")) {
				running = false;
			}
			else {
				System.out.println("Invalid option, please try again!");
			}
		}
		System.out.println("Logging out of " + u.getName().getKey() + "'s account");
	}
	
	/*
	 * Prompts the normal user for options
	 * calls respected LogicDriver methods
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
	 * 
	*/
	private static void runAdmin(User u) {
		boolean running = true;
		while(running) {
			menu.printAdmin();
			String option = sc.nextLine();
			if(option.equals("1")) {//view all users
				logicDriver.ViewAllUser();
			}
			else if(option.equals("2")) {//Create user
				if(!register()) {
					logger.info("Something went wrong! Please try again.");
				}
				else {
					logger.info("Registration successful!");
				}
			}
			else if(option.equals("3")) {//Modify user
				System.out.println("Enter User ID to be modify: ");
//				int usrID = sc.nextInt();
//				sc.nextLine();
				//TODO
				
			}
			else if(option.equals("4")) {//delete user 
				System.out.println("Enter User id to be delete: ");
				int usrID = sc.nextInt();
				sc.nextLine();
				logicDriver.deleteUser(usrID);
				
			}
			else if(option.equals("5")) {//view all accounts
				logicDriver.viewAllAcc();
			}
			else if(option.equals("6")) {//create account
				//account number
				System.out.println("Enter User ID to create the account for: ");
				int usrID = sc.nextInt();
				sc.nextLine();
				User uC = logicDriver.userLookup(usrID);
				
				System.out.println("Enter Account Number: ");
				String accNum = sc.nextLine();
				Account a = new Account();
				a.setOwner(uC);
				a.setJointOwner(uC);
				a.setAccountNumber(accNum);
				logicDriver.createAccount(a);
			}
			else if(option.equals("7")) {//delete
				System.out.println("Which account would you like to delete?");
				String acc = sc.nextLine();
				logicDriver.deleteAcc(acc);
				
			}
			else if(option.equals("8")) {//withdraw
				System.out.println("Which account would you like to withdraw from? ");
				String fromAcc = sc.nextLine();
				System.out.println("How much would you like to withdraw? ");
				double amount = sc.nextDouble();
				sc.nextLine();
				logicDriver.withdraw(fromAcc,amount);
				
			}
			else if(option.equals("9")) {//deposit
				System.out.println("Which account would you like to deposit to? ");
				String toAcc = sc.nextLine();
				System.out.println("How much would you like to deposit? ");
				double amount = sc.nextDouble();
				sc.nextLine();
				logicDriver.deposit(toAcc,amount);
				
			}
			else if(option.equals("10")) {//transfer
				System.out.println("Which account would you like to withdraw from? ");
				String fromAcc = sc.nextLine();
				System.out.println("Which account would you like to deposit to? ");
				String toAcc = sc.nextLine();
				System.out.println("How much would you like to transfer? ");
				double amount = sc.nextDouble();
				sc.nextLine();
				
				logicDriver.transfer(fromAcc, toAcc, amount);
			}
			else if(option.equals("11")) {//logout
				running = false;
			}
			else {
				System.out.println("Invalid option, please try again!");
			}
		}
		System.out.println("Logging out of " + u.getName().getKey() + "'s account");
	}
	
	/*
	 * Main method of driver class 
	*/
	public static void main(String[] args) {
		
		logger.info("Starting Program...");

		//main loop boolean
		boolean exitMain = false;

		// current user objects
		User curUser = null;
		
		//main loop
		while(!exitMain) {
			
			//prints main menu
			menu.printMain();
			//user option
				//1=login
				//2=register
				//3=exit
			String option = sc.nextLine();
			
			//login and execute main logic methods
			if(option.equals("1")) { 
				boolean loggingExit = false;
				while(!loggingExit) {
					curUser = login(curUser);
					if(!curUser.equals(null))
						loggingExit = true;
				}
				
				//logged in!
				if(!curUser.equals(null)) {
					if(curUser.getAccessLevel() == 0) {//Customer
						runClient(curUser);
					}
					else if(curUser.getAccessLevel() == 1) {//SuperUser
						runAdmin(curUser);
					}
				}
			}
			//Register for a new user 
			//should only be Clients
			else if (option.equals("2")) {
				if(!register())
					System.out.println("Something went wrong! Please try again.");
				else
					System.out.println("Registration successful!");
			}
			//Exit the main loop
			else if (option.equals("3")) {
				exitMain = true;
			}
			else {
				System.out.println("Invalid option, please try again!");
			}
		}
		System.out.println("Thanks for choosing our services! Have a good day!");
		logger.info("Exiting Program Now...");
	}//end main
}
