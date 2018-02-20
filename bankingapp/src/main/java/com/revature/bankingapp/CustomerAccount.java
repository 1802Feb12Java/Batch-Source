package com.revature.bankingapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CustomerAccount {
	private static Scanner scanner = new Scanner(System.in);
	private PasswordChecker passwordChecker = new PasswordChecker();
	
	public void viewCustomerAccounts() {
		System.out.println("Enter the username of the customer to view accounts.");
		String userName = scanner.next();
		scanner.nextLine();
		File file = new File("./customerAccounts/" + userName);
		if (file.exists()) {
			try {
				if (passwordChecker.checkPassword(userName, "customer")) {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
					}
					reader.close();
					MenuSystem.runMenu();
				}
				else {
					MenuSystem.runMenu();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Sorry. No such customer.");
			scanner.nextLine();
			MenuSystem.runMenu();
		}
	}
	
	public void openCustomerAccount() {
		System.out.println("Enter username to apply for an account.");
		String userName = scanner.next();
		scanner.nextLine();
		File file = new File("./customerAccounts/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "customer")) {
				System.out.println("Which type of account would you like? Enter a numeric value.");
				System.out.println("1. Checking\n2. Savings");
				int accountType = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Is this a joint account? Y/N?");
				String jointAccount = scanner.next();
				scanner.nextLine();
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
					String account;
					//set up account info string
					if (accountType == 1)
						account = "checking";
					else
						account = "savings";
					if (jointAccount.contains("Y") || jointAccount.contains("y"))
						account += " joint";
					else
						account += " single";
					account += " " + Math.abs((new Random().nextLong()));
					account += " 0";
					account += " PENDING\n";
					writer.write(account);
					writer.close();
					MenuSystem.runMenu();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				MenuSystem.runMenu();
			}
		}		
		else {
			System.out.println("Sorry. No such customer.");
			MenuSystem.runMenu();
		}	
	}
	public void withdrawMoney() {
		System.out.println("Enter the username of the customer to withdraw money.");
		String userName = scanner.next();
		scanner.nextLine();
		
		File file = new File("./customerAccounts/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "customer")) {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String line;
					ArrayList<String> accounts = new ArrayList<String>();
					while ((line = reader.readLine()) != null) {
						accounts.add(line);
					}
					reader.close();
					System.out.println("Enter index number to select an account");
					int i = 0;
					for (String account : accounts) {
						System.out.println(i + " " + account.toString());
						i++;
					}
					
					int accountSelection = scanner.nextInt();
					scanner.nextLine();
					String[] accountInfo = accounts.get(accountSelection).split(" ");
					accounts.remove(accountSelection);
					int accountMoney = Integer.valueOf(accountInfo[3]);
					System.out.println("How much would you like to withdraw?");
					int withdrawl;
					do {
						withdrawl = scanner.nextInt();
						scanner.nextLine();
						if (withdrawl < 0) {
							System.out.println("Error. Withdrawl must be bigger than 0");
						}
					} while (withdrawl >= 0);
					if (withdrawl > accountMoney) {
						System.out.println("You don't have enough money!");
						MenuSystem.runMenu();
					}
					else
					{
						accountMoney -= withdrawl;
						accountInfo[3] = String.valueOf(accountMoney);
					}
					String accountToAdd = "";
					for (String info : accountInfo) {
						accountToAdd += info + " ";
					}
					accounts.add(accountToAdd);
					
					//write accounts to file
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
					for (String account : accounts) {
						bufferedWriter.write(account+"\n");
					}
					
					bufferedWriter.close();
					MenuSystem.runMenu();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				MenuSystem.runMenu();
			}
		}
		else {
			System.out.println("Sorry. No such customer.");
			scanner.nextLine();
			MenuSystem.runMenu();
		}
		
	}
	public void depositMoney() {
		System.out.println("Enter the username of the customer to deposit money.");
		String userName = scanner.next();
		scanner.nextLine();
		
		File file = new File("./customerAccounts/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "customer")) {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String line;
					ArrayList<String> accounts = new ArrayList<String>();
					while ((line = reader.readLine()) != null) {
						accounts.add(line);
					}
					reader.close();
					System.out.println("Enter index number to select an account");
					int i = 0;
					for (String account : accounts) {
						System.out.println(i + " " + account.toString());
						i++;
					}
					
					int accountSelection = scanner.nextInt();
					scanner.nextLine();
					String[] accountInfo = accounts.get(accountSelection).split(" ");
					accounts.remove(accountSelection);
					int accountMoney = Integer.valueOf(accountInfo[3]);
					System.out.println("How much would you like to deposit?");
					int deposit = scanner.nextInt();
					scanner.nextLine();
					accountMoney += deposit;
					accountInfo[3] = String.valueOf(accountMoney);
					String accountToAdd = "";
					for (String info : accountInfo) {
						accountToAdd += info + " ";
					}
					accounts.add(accountToAdd);
					
					//write accounts to file
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
					for (String account : accounts) {
						bufferedWriter.write(account+"\n");
					}
					
					bufferedWriter.close();
					MenuSystem.runMenu();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				MenuSystem.runMenu();
			}
		}
		else {
			System.out.println("Sorry. No such customer.");
			scanner.nextLine();
			MenuSystem.runMenu();
		}
		
	}
	public void transferMoney() {
		System.out.println("Enter the username of the customer to transfer money.");
		String userName = scanner.next();
		scanner.nextLine();
		
		File file = new File("./customerAccounts/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "customer")) {
				try {
					System.out.println("Do you want to transfer to one of your accounts? Y/N?");
					String transferChoice = scanner.nextLine();
					if (transferChoice.contains("y") || transferChoice.contains("Y")) {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String line;
					ArrayList<String> accounts = new ArrayList<String>();
					while ((line = reader.readLine()) != null) {
						accounts.add(line);
					}
					reader.close();
					System.out.println("Enter index number to select an account to tranfer FROM");
					int i = 0;
					for (String account : accounts) {
						System.out.println(i + " " + account.toString());
						i++;
					}
					
					int accountFromSelection = scanner.nextInt();
					scanner.nextLine();
					String[] fromAccount = accounts.get(accountFromSelection).split(" "); 
					System.out.println("Enter index number to select an account to tranfer TO");
					int accountToSelection = scanner.nextInt();
					scanner.nextLine();
					String[] toAccount = accounts.get(accountToSelection).split(" ");
					System.out.println("Enter an amount to transfer");
					int amount = scanner.nextInt();
					scanner.nextLine();
					if (amount < 0 || amount > Integer.valueOf(fromAccount[3])) {
						System.out.println("Error Bad amount input");
						MenuSystem.runMenu();
					}
					else {
						int from = Integer.valueOf(fromAccount[3]);
						int to = Integer.valueOf(toAccount[3]);
						from -= amount;
						to += amount;
						fromAccount[3] = String.valueOf(from);
						toAccount[3] = String.valueOf(to);
						//remove old info from file
						if (accountToSelection > accountFromSelection) {
							accounts.remove(accountToSelection);
							accounts.remove(accountFromSelection);
						}
						else {
							accounts.remove(accountFromSelection);
							accounts.remove(accountToSelection);
						}
						
						
						
						String accountToAdd = "";
						for(String accountInfo : fromAccount) {
							accountToAdd += accountInfo + " ";
						}
								
						//add from account
						accounts.add(accountToAdd);
						
						//add to account
						accountToAdd = "";
						for(String accountInfo : toAccount) {
							accountToAdd += accountInfo + " ";
						}
						accounts.add(accountToAdd);
						
						//write accounts to file
						BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
						for (String account : accounts) {
							bufferedWriter.write(account+"\n");
						}
						
						bufferedWriter.close();
						MenuSystem.runMenu();
					}
					
				}
					//Transfer to  external Account
					else {
						externalTransfer(file);
					}
			}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			else {
				MenuSystem.runMenu();
			}
		
		}
	}
	private void externalTransfer(File fromMember) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fromMember));
			String line;
			ArrayList<String> accounts = new ArrayList<String>();
			while ((line = reader.readLine()) != null) {
				accounts.add(line);
			}
			reader.close();
			System.out.println("Enter index number to select an account to tranfer FROM");
			int i = 0;
			for (String account : accounts) {
				System.out.println(i + " " + account.toString());
				i++;
			}
			
			int accountFromSelection = scanner.nextInt();
			scanner.nextLine();
			String[] fromAccount = accounts.get(accountFromSelection).split(" "); 
			
			//get external account info
			System.out.println("Type the user ID of the account owner you would like to transfer TO");
			String toMember = scanner.next();
			scanner.nextLine();
			File toAccount = new File("./customerAccounts/" + toMember);
			if (toAccount.exists()) {
				BufferedReader readerTo = new BufferedReader(new FileReader(toAccount));
				String lineTo;
				ArrayList<String> accountsTo = new ArrayList<String>();
				while ((lineTo = readerTo.readLine()) != null) {
					accountsTo.add(lineTo);
				}
				readerTo.close();
				System.out.println("Enter index number to select an account to tranfer TO");
				i = 0;
				for (String account : accountsTo) {
					System.out.println(i + " " + account.toString());
					i++;
				}
				int accountToSelection = scanner.nextInt();
				scanner.nextLine();
				String[] toAccountString = accountsTo.get(accountToSelection).split(" ");
				
				System.out.println("Enter an amount to transfer");
				int amount = scanner.nextInt();
				scanner.nextLine();
				if (amount < 0 || amount > Integer.valueOf(fromAccount[3])) {
					System.out.println("Error Bad amount input");
					MenuSystem.runMenu();
				}
				else {
					int from = Integer.valueOf(fromAccount[3]);
					int to = Integer.valueOf(toAccountString[3]);
					from -= amount;
					to += amount;
					fromAccount[3] = String.valueOf(from);
					toAccountString[3] = String.valueOf(to);
					//remove old info from file
					accountsTo.remove(accountToSelection);
					accounts.remove(accountFromSelection);
					String accountToAdd = "";
					for(String accountInfo : fromAccount) {
						accountToAdd += accountInfo + " ";
					}
							
					//add from account
					accounts.add(accountToAdd);
					
					//add to account
					String toAccountToAdd = "";
					for(String accountInfo : toAccountString) {
						toAccountToAdd += accountInfo + " ";
					}
					accountsTo.add(toAccountToAdd);
					
					//write accounts to file
					try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fromMember))) {
						for (String account : accounts) {
							bufferedWriter.write(account+"\n");
						}

					}
					
					try (BufferedWriter bufferedToWriter = new BufferedWriter(new FileWriter(toAccount))){
						for (String accountTo : accountsTo) {
							bufferedToWriter.write(accountTo+"\n");
						}
					
					}
					//bufferedToWriter.close();
					//bufferedWriter.close();
					MenuSystem.runMenu();
				}
				

			}
			else {
				System.out.println("Sorry. No such user");
				MenuSystem.runMenu();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}