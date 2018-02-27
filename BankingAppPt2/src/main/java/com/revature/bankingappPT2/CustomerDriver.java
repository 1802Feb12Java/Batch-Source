package com.revature.bankingappPT2;

import java.sql.SQLException;
//import java.sql.Connection;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDriver {

	private static User user;
	private static Scanner sysScanner;
	//private static Integer accountIDCounter;
	
	public CustomerDriver() {
		super();
	}
	public CustomerDriver(User user, Scanner sysScanner) {
		super();
		this.user = user;
		this.sysScanner = sysScanner;
	}
	
	//static constructor?
	public static void mainMenu(User user, Scanner sysScanner) throws SQLException {
		CustomerDriver.user = user;
		CustomerDriver.sysScanner = sysScanner;
		mainMenuExt();
	}
	
	private static void mainMenuExt() throws SQLException {
		System.out.println("\nWelcome "+user.getUsername()+" would you like to do?"
				+ "\n(1) Apply to open Account"
				+ "\n(2) Enter an Account"
				+ "\n(3) Delete an Empty Account"
				+ "\n(4) Exit");
		
		switch(UserDriver.getInput(sysScanner)) {
		case 1:
			openAccount();
			break;
		case 2:
			enterAccount();
			break;
		case 3:
			deleteEmptyAccount();
			break;
		case 4:
			System.out.println("Logout\n");
			return;
			//break;
		}
		
		mainMenu(user, sysScanner);
	}
	
	private static void openAccount() throws SQLException {
		System.out.println("\nWhat would you like to name this account?\n(N) Exit/Logout");
		String newAccountName = sysScanner.next();
		if (newAccountName.equalsIgnoreCase("n")) {
			//mainMenu(sysScanner);
			return;
		} else {
			System.out.println("Thank you for apply for a new account.");
			Account newAccount = new Account(user.getUsername(), newAccountName, 00000000, user.getCustomerID());
			//System.out.println("Account successfully put in for approval.");
			user.addAccount(newAccount);
			UserDriver.getBank().addAccount(newAccount);
			UserDriver.getDao().addAccountToTable(newAccount, newAccount.getBalance());
			newAccount.setApproved(true);
			return;
			//saveUserAccount();
		}
		
	}
	
	public static void enterAccount() {
		
		String input;
		//Account workingWith;
		int counter = 0;
		ArrayList<Account> accounts = user.getListOfAccounts();
		//print out all accounts the user has.
		if (accounts.size() < 1) {
			System.out.println("You have no active accounts.\nPlease apply for a new account");
			return;
		} else {
			System.out.println("Please choose one of your accounts.");
			for (Account acc : accounts) {
				System.out.println("(" + counter + ") "+acc.getAccountName()+" - Balance: $"+acc.getBalance());
				counter++;
			}
			System.out.println("\n(N) Exit");
		}
		
		//picking the account, need an int to pull from an index of account
		input = sysScanner.next();
		
		if (input.equalsIgnoreCase("n")) {
			return;
		}
		try {
			int intput = Integer.parseInt(input);
			
			if (intput < 0 || intput >= counter) {
				System.out.println("Please enter a valid input");
				enterAccount();
			} else {
				//System.out.println("\ninsideAccount();");
				insideAccount(accounts.get(intput), sysScanner);
				enterAccount();
			}
		} catch (Exception e){
			System.out.println("Invalid input.");
			enterAccount();
		}
		return;
	}
	
	//Options for modifying an account
	static void insideAccount(Account workingWith, Scanner sysScanner) throws SQLException {
			
		System.out.println("What would you like to do?\n"
				+ "(1) Make a Withdraw\n"
				+ "(2) Make a Deposit\n"
				+ "(3) Make a transfer\n"
				+ "(4) Exit\n");
		String input;
		switch(UserDriver.getInput(sysScanner)) {
		
		
		//
		//Withdraw option
		//
		case 1:
			
			System.out.println("Please enter an amount (DDD.CC) to withdraw.\nExit (N)\n");
			//input = sysScanner.next();
			Double withdraw = UserDriver.getDouble(sysScanner);
			if (withdraw <= 0.00) {
				System.out.println("Please withdraw a non-zero amount.");
				insideAccount(workingWith, sysScanner);
				return;
			} else {
				
				if (withdraw > workingWith.getBalance()) {
					System.out.println("You can't withdraw more then your current balance: $" + workingWith.getBalance());
					insideAccount(workingWith, sysScanner);
					return;
				} else {
					UserDriver.withdraw(workingWith, withdraw);
					System.out.println("Withdrew $"+withdraw+". Current Balance: $"+workingWith.getBalance());
					UserDriver.getDao().updateAccountBalance(workingWith.getBalance(), workingWith.getAccountID());
					insideAccount(workingWith, sysScanner);
					return;
				}
			}
		//
		//Deposit
		//

		case 2:
			System.out.println("Please enter an amount (DDD.CC) to Deposit.\nExit (N)\n");
			Double depo = sysScanner.nextDouble();
			if (depo <= 0) {
				System.out.println("Please enter a non-zero amount.");
				insideAccount(workingWith, sysScanner);
				return;
			} else {
				
				UserDriver.deposit(workingWith, depo);
				System.out.println("$" + depo + " was deposited into " + workingWith.getAccountName() + "\n"
						+ "Your total balance is now: $" + workingWith.getBalance());
				UserDriver.getDao().updateAccountBalance(workingWith.getBalance(), workingWith.getAccountID());
				insideAccount(workingWith, sysScanner);
				return;
			}

		//
		//TRANSFER
		// 
		case 3:	
			System.out.println("Please provide the username and account to where you wish to transfer funds.");
			System.out.println("Enter account name or (T) to transfer another account you own. Exit (N)");
			input = sysScanner.next();
			
			if(input.equalsIgnoreCase("n")) {
				insideAccount(workingWith, sysScanner);
			} else if (input.equalsIgnoreCase("t")) {
				System.out.println("Please give us the account name. Exit (N)");
				input = sysScanner.next();
				if (input.equalsIgnoreCase("n")) {
					insideAccount(workingWith, sysScanner);
					return;
				} else {
					for (Account acc : user.getListOfAccounts()) {
						if(acc.getAccountName().equals(input)) {
							System.out.println("Enter an amount ($DDD.CC) to transfer. Exit (0)");
							
							Account acc2 = acc;

							Double intput = UserDriver.getDouble(sysScanner);
							
							if(intput <= 0.00) {
								System.out.println("Please enter a non-zero amount.");
								insideAccount(workingWith, sysScanner);
								return;
							}
							if(UserDriver.transfer(workingWith, user, acc2, intput)) {
								UserDriver.getDao().updateAccountBalance(workingWith.getBalance(), workingWith.getAccountID());
								UserDriver.getDao().updateAccountBalance(acc2.getBalance(), acc2.getAccountID());
								insideAccount(workingWith, sysScanner);
								return;
							} else {
							//System.out.println(intput + " was transfer to account "+acc2.getUserAccountName()+" from "+workingWith.getAccountName()+".  Current account balance: $"+workingWith.getBalance());
								System.out.println("Could not complete Transfer\n");
								insideAccount(workingWith, sysScanner);
							return;
							}
						}
					}
					System.out.println("You do not have an accoun with name: "+input);
					insideAccount(workingWith, sysScanner);
					return;
				}
			} else {
				//TRANSFER TO ANOTHER USER ACCOUNT AND ACCOUNT
				
				if(UserDriver.doesUsernameExist(input)) { //UserDriver.getBank().getUserAccounts().containsKey(input)
					//if(input.equals(user.getUserName())){
						User user2 = UserDriver.getDao().rebuildUserAccount(input);//UserDriver.getBank().getUserAccounts().get(input);
						System.out.println("Please enter the account name you wish to transfer funds too.");
						input = sysScanner.next();
						for (Account acc : user2.getListOfAccounts()) {
							if(acc.getAccountName().equals(input)) {
								System.out.println("Please enter an amount you wish to transfer. Exit(N)");
								//input = sysScanner.next();
								Double trans = UserDriver.getDouble(sysScanner);
								if (trans <=0) {
									System.out.println("Please enter a non-zero amount.");
									insideAccount(workingWith, sysScanner);
									return;
								}
//								try {
//									UserDriver.transfer(workingWith, user2, acc, trans);
//									
//								} catch (Exception e) {
//									System.out.println("Invalid input");
//									insideAccount(workingWith, sysScanner);
//									//e.printStackTrace(arg0);
//								}
								if(UserDriver.transfer(workingWith, user2, acc, trans)) {
									UserDriver.getDao().updateAccountBalance(workingWith.getBalance(), workingWith.getAccountID());
									UserDriver.getDao().updateAccountBalance(acc.getBalance(), acc.getAccountID());
									insideAccount(workingWith, sysScanner);
									return;
								}
								System.out.println("Sorry no account with that name exists.");
								insideAccount(workingWith,sysScanner);
							} 
//							System.out.println("Enter an amount ($DDD.CC) to transfer. Exit (0)");
//							
//							Account acc2 = acc;
//
//							Double intput = UserDriver.getDouble(sysScanner);
//							
//							if(intput <= 0.00) {
//								System.out.println("Please enter a non-zero amount.");
//								insideAccount(workingWith, sysScanner);
//								return;
//							}
//							if(UserDriver.transfer(workingWith, user2, acc2, trans)) {
//								UserDriver.getDao().updateAccountBalance(workingWith.getBalance(), workingWith.getAccountID());
//								UserDriver.getDao().updateAccountBalance(acc2.getBalance(), acc2.getAccountID());
//								insideAccount(workingWith, sysScanner);
//								return;
//							} else {
//							//System.out.println(intput + " was transfer to account "+acc2.getUserAccountName()+" from "+workingWith.getAccountName()+".  Current account balance: $"+workingWith.getBalance());
//								System.out.println("Could not complete Transfer\n");
//								insideAccount(workingWith, sysScanner);
//							return;
//							}
							
						}
						System.out.println("Account name not found.");
						insideAccount(workingWith,sysScanner);
						return;
					} else {
						System.out.println("Account name not found.");
						insideAccount(workingWith,sysScanner);
						return;
					}
				}
			break;
		//
		//EXIT
		//
		case 4:
			break;
		default:
			System.out.println("Invalid Input. Try Again");
			insideAccount(workingWith,sysScanner);
			break;
		}
		return;
		
	}
	private static void deleteEmptyAccount() {
		ArrayList<Account> accounts = user.getListOfAccounts();
		Integer counter = 0;
		ArrayList<Account> deletableAccounts = new ArrayList<Account>();
		System.out.println("Choose an account to delete.");
		for (Account acc : accounts) {
			if(acc.getBalance()<0.0001) {
				System.out.println("(" + counter + ") "+acc.getAccountName()+" - Balance: $"+acc.getBalance());
				deletableAccounts.add(acc);
				counter++;
			}
		}
		if(counter==0) {
			System.out.println("No empty accounts");
		}
		System.out.println("("+counter+") Exit.");
		try{
			Integer intput = sysScanner.nextInt();
			if(intput >= 0 && intput < counter) {
				UserDriver.getDao().deleteAccount(deletableAccounts.get(intput).getAccountID());
				accounts.remove(deletableAccounts.get(intput));
				System.out.println("Account successfully deleted.");
				return;
			} else {
				System.out.println("Invalid Input.");
				//insideAccount(workingWith);
				return;
			}
		} catch(Exception e) {
			System.out.println("Invalid Input.");
			//insideAccount(workingWith);
			return;
		}
		
	}
	
}