package com.revature.bankingapp.users;

import java.io.Serializable;
import java.util.Scanner;

import com.revature.bankingapp.UserDriver;

public class Admin extends Employee implements Serializable {

	private Serializable id = 1L;
	private Users users;
	private String username;
	private String password;
	
	
	public Admin(String userName, String passWord, Users users) {
		super(userName, passWord, users);
		this.password = passWord;
		this.users = users;
		this.username = userName;
		// TODO Auto-generated constructor stub
		users.saveUserData(users);
	}
	
	
	public void userDriverStart(Scanner sysScanner) {
		
		System.out.println("What would you like to do?\n"
				+ "Appove Accounts (A)?\n"
				+ "Delete Accounts (D)?\n"
				+ "Account management (M)\n"
				+ "Logout (N)");
		
		String input = sysScanner.next();
		if (input.equalsIgnoreCase("n")) {
			return;
		} else if (input.equalsIgnoreCase("a")) {
			approveAccounts(sysScanner);
			userDriverStart(sysScanner);
			return;
		} else if (input.equalsIgnoreCase("d")) {
			deleteAccounts(sysScanner);
			userDriverStart(sysScanner);
			return;
		} else if (input.equalsIgnoreCase("m")) {
			userManagement(sysScanner);
			userDriverStart(sysScanner);
			return;	
			
		}
		
		
	}
	
	
	private void userManagement(Scanner sysScanner) {
		System.out.println("What would you like to do?\n"
				+ "Withdraw (W)\n"
				+ "Deposit (D)\n"
				+ "Transfer (T)\n"
				+ "Exit (D)");
		String input = sysScanner.next();
		if (input.equalsIgnoreCase("w")) {
			System.out.println("Enter an user account name.");
			String usernameW = sysScanner.next();
			
			for (User user : users.getUsers()) {
				if (usernameW.equals(user.getUserName())) {
					System.out.println("Enter an account name");
					String accountW = sysScanner.next();
					for (Account acc : user.getAccounts()) {
						if (acc.getAccountName().equals(accountW)) {
							System.out.println("Enter an amount to withdraw");
							input = sysScanner.next();
							try {
								Double amount = Double.parseDouble(input);
								UserDriver.withdraw(acc, amount);
								System.out.println("Withdrew $"+amount+" from account "+acc.getAccountName()+" owned by "+acc.getUserAccountName()+" leaving the account with a balance of "+acc.getBalance());
								userDriverStart(sysScanner);
								return;
							} catch (Exception e) {
								System.out.println("Invalid Input");
								userDriverStart(sysScanner);
								return;
							}
						}
					}
				}
			}
			
			
		} else if (input.equalsIgnoreCase("d")) {
			
			System.out.println("Enter an user account name.");
			String usernameW = sysScanner.next();
			
			for (User user : users.getUsers()) {
				if (usernameW.equals(user.getUserName())) {
					System.out.println("Enter an account name");
					String accountW = sysScanner.next();
					for (Account acc : user.getAccounts()) {
						if (acc.getAccountName().equals(accountW)) {
							System.out.println("Enter an amount to deposit");
							input = sysScanner.next();
							try {
								Double amount = Double.parseDouble(input);
								UserDriver.deposit(acc, amount);
								System.out.println("Deposited $"+amount+" from account "+acc.getAccountName()+" owned by "+acc.getUserAccountName()+" leaving the account with a balance of "+acc.getBalance());
								userDriverStart(sysScanner);
								return;
							} catch (Exception e) {
								System.out.println("Invalid Input");
								userDriverStart(sysScanner);
								return;
							}
						}
					}
				}
			}
			
		} else if (input.equalsIgnoreCase("t")) {
			System.out.println("Enter an user account name.");
			String username1 = sysScanner.next();
			System.out.println("Enter a second account name to transfer too.");
			String username2 = sysScanner.next();
			
			for (User user : users.getUsers()) {
				if (username1.equals(user.getUserName())) {
					System.out.println("Enter an account name");
					String accountW = sysScanner.next();
					System.out.println("Enter the second accounts name");
					String account2 = sysScanner.next();
					for (Account acc : user.getAccounts()) {
						if (acc.getAccountName().equals(accountW)) {
							for (User user2 : users.getUsers()) {
								if(username2.equals(user2.getUserName())) {
									for (Account acc2 : user2.getAccounts()) {
										if (acc2.getAccountName().equals(account2)) {
											try {
												System.out.println("Enter an amount to transfer.");
												input = sysScanner.next();
												Double amount = Double.parseDouble(input);
												UserDriver.transfer(acc, user2, acc2, amount);
												//System.out.println("Deposited $"+amount+" from account "+acc.getAccountName()+" owned by "+acc.getUserAccountName()+" leaving the account with a balance of "+acc.getBalance());
												userDriverStart(sysScanner);
												return;
											} catch (Exception e) {
												System.out.println("Invalid Input");
												userDriverStart(sysScanner);
												return;
											}
										}
									}
								}
							}
						}
					}
					System.out.println("No matches found. Try Again");
					userManagement(sysScanner);
				}
			}
			
			
			
		} else if (input.equalsIgnoreCase("n")) {
			userDriverStart(sysScanner);
			return;
		} else {
			System.out.println("Invalid Input");
			userManagement(sysScanner);
			return;
		}
		
		
		
	}


	private void deleteAccounts(Scanner sysScanner) {

		System.out.println("Choose an account to delete (#). Exit (N)");
		int counter = 0;
		for (User user : users.getUsers()) {
			System.out.println("User: "+user.getUserName()+" ("+counter+")");
			counter++;
		}
		
		String input = sysScanner.next();
		if(input.equalsIgnoreCase("n")) {
			userDriverStart(sysScanner);
			return;
		}
		try {
			int intput = Integer.parseInt(input);
			if (intput < 0 || intput > users.getUsers().size() - 1) {
				System.out.println("Invalid Input");
				userDriverStart(sysScanner);
				return;
			}
			System.out.println("Deleting "+users.getUsers().get(intput).getUserName());
			users.getUsers().remove(intput);
			users.saveUserData(users);
			
		} catch (Exception e) {
			System.out.println("Invalid Input.");
			userDriverStart(sysScanner);
			return;
		}
		
	}


	private void approveAccounts(Scanner sysScanner) {
		
		System.out.println("Accounts waiting approval. Exit(N)");
		int counter=0;
		if (users.getNewAccounts().size() < 1) {
			System.out.println("No new Accounts at this time.");
			userDriverStart(sysScanner);
			return;
		}
		for (Account acc : users.getNewAccounts()) {
			System.out.println(acc.getUserAccountName()+"'s application for account: "+acc.getAccountName()+". ("+counter+").");
			counter++;
		}
		String input = sysScanner.next();
		if (input.equalsIgnoreCase("n")) {
			userDriverStart(sysScanner);
			return;
		}
		try {
			int intput = Integer.parseInt(input);
			if(intput < 0 || intput > users.getNewAccounts().size()-1) {
				System.out.println("Invalid Input");
				userDriverStart(sysScanner);
				return;
			}
			System.out.println("Deny (D)\nApprove (A)\n Exit (N)");
			input = sysScanner.next();
			if (input.equalsIgnoreCase("n")) {
				userDriverStart(sysScanner);
				return;
			} else if (input.equalsIgnoreCase("a")) {
				System.out.println("Approved "+users.getNewAccounts().get(intput).getAccountName());
				users.approveAccount(users.getNewAccounts().get(intput), users.getNewAccounts().get(intput).getUserAccountName());
				System.out.println("Enter any key to exit");
				input = sysScanner.next();
				userDriverStart(sysScanner);
			} else if (input.equalsIgnoreCase("d")) {
				users.denyApproval(users.getNewAccounts().get(intput));
				userDriverStart(sysScanner);
				return;
			} else {
				System.out.println("Invalid Input");
				userDriverStart(sysScanner);
				return;
			}
			//return;
		} catch (Exception e) {
			System.out.println("Invalid Input");
			userDriverStart(sysScanner);
			return;
		}
		
		
	}
	

}





