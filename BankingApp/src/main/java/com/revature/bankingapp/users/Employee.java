package com.revature.bankingapp.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends Customer implements User{

	
	private Serializable id= 1L;
	private String userName;
	private String passWord;
	private Users users;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String userName, String passWord, Users users) {
		super(userName, passWord, users);
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.users = users;
		users.saveUserData(users);
	}

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getMainMenu() {
		
		return "What would you like to do?"
				+ " \n ";
	}
	//////
	//////
	//////
	//////
	//////
	public void userDriverStart(Scanner sysScanner) {

		System.out.println("What would you like to do?\n"
				+ "View Users (U)\n"
				+ "Approve Accounts (A)\n"
				+ "Logout (N)\n");
		String input = sysScanner.next();
		
		if (input.equalsIgnoreCase("n")) {
			return;
			
	////////////
	//view users
	////////////
			
		} else if (input.equalsIgnoreCase("u")) {
			int counter = 0;
			for (User user : users.getUsers()) {
				System.out.println("User: "+user.getUserName()+" ("+counter+")");
				counter++;
			}
			System.out.println("Choose a user (#). Exit (N)");
			input = sysScanner.next();
			if (input.equalsIgnoreCase("n")) {
				userDriverStart(sysScanner);
				return;
			}
			try {
				Integer intput = Integer.parseInt(input);
				User user = users.getUsers().get(intput);
				System.out.println("User username: "+user.getUserName());
				System.out.println("User Password: "+user.getPassWord());
				int kounter=0;
				try {
					for (Account acc : user.getAccounts()) {
						System.out.println("Account "+kounter+": "+acc.getAccountName()+" - $"+acc.getBalance());
						kounter++;
					}
				} catch (NullPointerException npe) {
					System.out.println("No accounts associated with this username");
				}
				System.out.println("Press any key to return.");
				input = sysScanner.next();
				userDriverStart(sysScanner);
				return;
				
			} catch (Exception e) {
				System.out.println("Invalid input.");
				userDriverStart(sysScanner);
				return;
			}
			
			
	//////////////////		
	//approve accounts
	/////////////////	
		} else if (input.equalsIgnoreCase("a")) {
			
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
			//System.out.println("Deny Account with (D#).");
			input = sysScanner.next();
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
			
		} else {
			System.out.println("Invalid Input");
			userDriverStart(sysScanner);
		}
		
	}

	public void enterAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
