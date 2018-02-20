package com.revature.bankingapp.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bankingapp.UserDriver;

public class Customer extends UserDriver implements User, Serializable{

	private Serializable id= 1L;
	private String userName;
	private String passWord;
	private ArrayList<Account> accounts;
	private Users users;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String userName, String passWord, Users users) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.accounts = new ArrayList<Account>();
		this.users = users;
		users.saveUserData(users);
	}

	public Object getUserName() {
		
		return userName;
	}

	public String getMainMenu() {
		
		return "What would you like to do?"
				+ "\nApply to open Account (C)"
				+ "\nEnter an Account (A)";		
		
	}
	
	public void openAccount(String newAccountName) {
		
		System.out.println("Thank you for apply for a new account. An employee will review your application and approve your account shortly.");
		
		Account newAccount = new Account(this.userName, newAccountName);
		
		users.accountApplication(newAccount, this);
		users.saveUserData(users);
		
	}
	
	public void userDriverStart(Scanner sysScanner) {
		//MAIN MENU
		System.out.println("\nWhat would you like to do?"
				+ "\nApply to open Account (O)"
				+ "\nEnter an Account (A)"
				+ "\nExit (N)");
		String input = sysScanner.next();
		
		//open account option
		if (input.equalsIgnoreCase("o")) {
			System.out.println("\nWhat would you like to name this account?\nExit/Logout (N)");
			input = sysScanner.next();
			if (input.equalsIgnoreCase("n")) {
				userDriverStart(sysScanner);
				return;
			} else {
				openAccount(input);
				//System.out.println("Account successfully put in for approval.");
				userDriverStart(sysScanner);
				//saveUserAccount();
			}
			
		} else if (input.equalsIgnoreCase("a")) {
			enterAccount(sysScanner);
			userDriverStart(sysScanner);
			
		} else if (input.equalsIgnoreCase("n")){
			System.out.println("\nAccount Logged Out");
			return;
		
		} else {
			System.out.println("Please input a valid response.");
			userDriverStart(sysScanner);
		}
		//System.out.println("Shouldn't get here.");
		//userDriverStart(sysScanner);
	}

	public void enterAccount(Scanner sysScanner) {
		
		String input;
		Account workingWith;
		int counter = 0;
		
		//print out all accounts the user has.
		if (accounts.size() < 1) {
			System.out.println("You have no active accounts.\nPlease apply for a new account or wait for a pending accout to be approved.");
			return;
		} else {
			System.out.println("Please choose one of your accounts.\nExit (N)");
			for (Account acc : accounts) {
				System.out.println(acc.getAccountName() + " (" + counter + ").");
				counter++;
			}
		
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
				enterAccount(sysScanner);
			} else {
				insideAccount(sysScanner, accounts.get(intput));
				enterAccount(sysScanner);
			}
		} catch (Exception e){
			System.out.println("Invalid input.");
			enterAccount(sysScanner);
		}
		return;
	}
	
	public void insideAccount(Scanner sysScanner, Account workingWith) {
		System.out.println("What would you like to do?\n"
				+ "Make a Withdraw (W)\n"
				+ "Make a Deposit (D)\n"
				+ "Make a transfer (T)\n"
				+ "Exit (N)\n");
		String input = sysScanner.next();
		
		//
		//Withdraw option
		//
		if (input.equalsIgnoreCase("w")) {
			System.out.println("Please enter an amount (DDD.CC) to withdraw.\nExit (N)\n");
			if (input.equalsIgnoreCase("n")) {
				return;
			} else {
				Double withdraw = sysScanner.nextDouble();
				if (withdraw > workingWith.getBalance()) {
					System.out.println("You can't withdraw more then your current balance: $" + workingWith.getBalance());
					
				} else {
					UserDriver.withdraw(workingWith, withdraw);
					System.out.println("Withdrew $"+withdraw+". Current Balance: $"+workingWith.getBalance());
					users.saveUserData(users);
					insideAccount(sysScanner, workingWith);
				}
				
			}
		//
		//Deposit
		//
		} else if (input.equalsIgnoreCase("d")) {
			System.out.println("Please enter an amount (DDD.CC) to Deposit.\nExit (N)\n");
			Double depo = sysScanner.nextDouble();
			if (input.equalsIgnoreCase("n")) {
				return;
			} else {
				
				UserDriver.deposit(workingWith, depo);
				System.out.println("$" + depo + " was deposited into " + workingWith.getAccountName() + "\n"
						+ "Your total balance is now: $" + workingWith.getBalance());
				insideAccount(sysScanner, workingWith);
			}
			Users.saveUserData(users);
			return;
			
		//
		//TRANSFER
		// 
		} else if (input.equalsIgnoreCase("t")) {
			
			System.out.println("Please provide the username and account to where you wish to transfer funds.");
			System.out.println("Enter account name or (T) to transfer another account you own. Exit (N)");
			input = sysScanner.next();
			
			if(input.equalsIgnoreCase("n")) {
				insideAccount(sysScanner, workingWith);
			} else if (input.equalsIgnoreCase("t")) {
				System.out.println("Please give us the account name. Exit (N)");
				input = sysScanner.next();
				if (input.equalsIgnoreCase("n")) {
					return;
				} else {
					for (Account acc : accounts) {
						if(acc.getAccountName().equals(input)) {
							System.out.println("Enter an amount ($DDD.CC) to transfer. Exit (N)");
							String input2 = sysScanner.next();
							Account acc2 = acc;
							if (input2.equalsIgnoreCase("n")) {
								return;
							}
							Double intput = Double.parseDouble(input2);
							UserDriver.transfer(workingWith, this, acc2, intput);
							//System.out.println(intput + " was transfer to account "+acc2.getUserAccountName()+" from "+workingWith.getAccountName()+".  Current account balance: $"+workingWith.getBalance());
							insideAccount(sysScanner, workingWith);
						}
					}
				}
			} else {
				for(User user: users.getUsers()) {
					if(input.equals(user.getUserName())){
						User userAcc = user;
						System.out.println("Please enter the account name you wish to transfer funds too.");
						input = sysScanner.next();
						for (Account acc : user.getAccounts()) {
							if(acc.getAccountName().equals(input)) {
								System.out.println("Please enter an amount you wish to transfer. Exit(N)");
								input = sysScanner.next();
								if (input.equalsIgnoreCase("n")) {
									insideAccount(sysScanner, workingWith);
									return;
								}
								try {
									Double intput = Double.parseDouble(input);
									UserDriver.transfer(workingWith, userAcc, acc, intput);
									
								} catch (Exception e) {
									System.out.println("Invalid input");
									insideAccount(sysScanner, workingWith);
									//e.printStackTrace(arg0);
								}
								System.out.println("Sorry no account with that name exists.");
								insideAccount(sysScanner, workingWith);
							}
						}
						
					} else {
						
					}
				}
			}
			
			return;
			
		//
		//EXIT
		//
		} else if (input.equalsIgnoreCase("n")) {
			return;
		} else {
			System.out.println("Invalid Input. Try Again");
			insideAccount(sysScanner, workingWith);
		}
		
	}
	
	
	
	public void addToAccounts(Account account) {
			accounts.add(account);
	}

	public void enterAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	
}
