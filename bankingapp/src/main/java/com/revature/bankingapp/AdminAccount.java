package com.revature.bankingapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AdminAccount {
	static Scanner scanner = new Scanner(System.in);
	private PasswordChecker passwordChecker = new PasswordChecker();
	private AccountChecker accountChecker = new AccountChecker();;
	
	public void registerEmployee() {
		System.out.println("To register a new employee, enter your admin username");
		String userName = scanner.nextLine();
		File file = new File("./adminProfiles/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "admin")) {
				System.out.println("Enter the username of the employee.");
				String employeeUsername = scanner.nextLine();
				System.out.println("Enter a password for the employee");
				String employeePassword = scanner.nextLine();
				file = new File("./employeeProfiles/" + employeeUsername);
				try {
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
					bufferedWriter.write(employeeUsername + ":" + employeePassword);
					bufferedWriter.close();
					System.out.println("Employee Created Successfully");
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
			System.out.println("Sorry. No such admin.");
		}
	}

	public void viewEmployeeInformation() {
		System.out.println("To view employee enter your admin username");
		String userName = scanner.nextLine();
		File file = new File("./adminProfiles/" + userName);
		if (file.exists()) {
			if(passwordChecker.checkPassword(userName, "admin")) {
				System.out.println("Enter the name of the employee to view info");
				String employeeName = scanner.nextLine();
				try {
					BufferedReader br = new BufferedReader(new FileReader("./employeeProfiles/" + employeeName));
					String line ;
					while ((line = br.readLine()) != null) {
						System.out.println(line);
					}
					br.close();
					MenuSystem.runMenu();
				} catch (FileNotFoundException e) {
					System.out.println("No such employee");
					MenuSystem.runMenu();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				MenuSystem.runMenu();
			}
		}
		else {
			System.out.println("Nope.");
			MenuSystem.runMenu();
		}
		
	}

	public void adjustBalance() {
		System.out.println("To adjust balances, please enter your username.");
		String userName = scanner.nextLine();
		
		File file = new File("./adminProfiles/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "admin")) {
				System.out.println("Enter a customer ID to adjust balance");
				String customerId = scanner.nextLine();
				file = new File("./customerAccounts/" + customerId);
				if (file.exists()) {
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						ArrayList<String> accounts = new ArrayList<String>();
						while ((line = reader.readLine()) != null) {
							accounts.add(line);
						}
						
						//perform adjustment
						System.out.println("Select an account to adjust balance. Enter a numeric option");
						int i = 0;
						for (String account : accounts) {
							System.out.println(String.valueOf(i) + " " + account);
							i++;
						}
						int accountChoice = scanner.nextInt();
						scanner.nextLine();
						System.out.println("You chose the following account:");
						System.out.println(accounts.get(accountChoice));
						System.out.println("Enter amount to adjust balance");
						System.out.println("Use positive amounts for credit");
						System.out.println("Use negative amounts for debit");
						int adjustmentAmount = scanner.nextInt();
						scanner.nextLine();
						String[] accountInfo = accounts.get(accountChoice).split(" ");
						int finalAmount = Integer.valueOf(accountInfo[3]);
						finalAmount += Integer.valueOf(adjustmentAmount);
						accountInfo[3] = String.valueOf(finalAmount);
						String accountToAdd = "";
						for (String info : accountInfo) {
							accountToAdd += info + " ";
						}
						accounts.remove(accountChoice);
						accounts.add(accountToAdd);
						if(accountChecker.isJointAccount(accountToAdd)) {
							accountChecker.applyToJointOwner(accountToAdd, customerId);
						}
						BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
						for (String account : accounts) {
							bufferedWriter.write(account+"\n");
						}
						
						bufferedWriter.close();
						MenuSystem.runMenu();
						reader.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					System.out.println("No Such Customer!");
				}
			}
		}
		
	}

	public void deleteAccount() {
		System.out.println("To delete accounts, please enter your username.");
		String userName = scanner.nextLine();
		
		File file = new File("./adminProfiles/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "admin")) {
				System.out.println("Enter a customer ID to delete accounts");
				String customerId = scanner.nextLine();
				file = new File("./customerAccounts/" + customerId);
				if (file.exists()) {
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						ArrayList<String> accounts = new ArrayList<String>();
						while ((line = reader.readLine()) != null) {
							accounts.add(line);
						}
						
						//perform approve/deny operations
						System.out.println("Select an account to delete. Enter a numeric option");
						int i = 0;
						for (String account : accounts) {
							System.out.println(String.valueOf(i) + " " + account);
							i++;
						}
						int accountChoice = scanner.nextInt();
						scanner.nextLine();
						System.out.println("You chose the following account:");
						System.out.println(accounts.get(accountChoice));
						System.out.println("Are you sure you want to delete? Y/N?");
						String deleteChoice = scanner.nextLine();
						if (deleteChoice.contains("y") || deleteChoice.contains("Y")) {
							accounts.remove(accountChoice);
						}
						else {
							MenuSystem.runMenu();
						}
						
						//save results to customer file
						BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
						for (String accountToWrite : accounts) {
							bufferedWriter.write(accountToWrite + "\n");
						}
						bufferedWriter.close();
						reader.close();
						MenuSystem.runMenu();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				else {
					System.out.println("No such customer!");
					MenuSystem.runMenu();
				}
			}
		}
		else {
			System.out.println("No such customer!");
			MenuSystem.runMenu();
		}
		
		
	}

	public void approveAccounts() {
		System.out.println("To approve or deny accounts, please enter your username.");
		String userName = scanner.nextLine();
		
		File file = new File("./adminProfiles/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "admin")) {
				System.out.println("Enter a customer ID to aapprove accounts");
				String customerId = scanner.nextLine();
				file = new File("./customerAccounts/" + customerId);
				if (file.exists()) {
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						ArrayList<String> accounts = new ArrayList<String>();
						ArrayList<String> pendingAccounts = new ArrayList<String>();
						while ((line = reader.readLine()) != null) {
							accounts.add(line);
						}
						
						//extract pending accounts from customer file
						for (String account : accounts) {
							String[] accountInfo = account.split(" ");
							if (accountInfo[4].equals("PENDING")) {
								String addToPending = "";
								for (int i = 0; i < accountInfo.length; i++) {
									addToPending += accountInfo[i] + " ";
								}
								pendingAccounts.add(addToPending);
							}
						}
						
						//perform approve/deny operations
						System.out.println("Select an account to approve or deny. Enter a numeric option");
						int i = 0;
						for (String account : pendingAccounts) {
							System.out.println(String.valueOf(i) + " " + account);
							i++;
						}
						int accountChoice = scanner.nextInt();
						scanner.nextLine();
						System.out.println("You chose the following account:");
						System.out.println(pendingAccounts.get(accountChoice));
						System.out.println("What would you like to do? Enter a numeric option");
						System.out.println("1. Approve");
						System.out.println("2. Deny");
						int approveChoice = scanner.nextInt();
						scanner.nextLine();
						String account = pendingAccounts.get(accountChoice);
						String[] info = account.split(" ");;
						if(approveChoice == 1) { //approve
							info[4] = "APPROVED";
						}
						else if(approveChoice == 2) { //deny
							info[4] = "DENIED";
						}
						else {
							System.out.println("Invalid choice");
							MenuSystem.runMenu();
						}
						//save results to customer file
						Iterator<String> itr = accounts.iterator();
						while (itr.hasNext()) {
							String accountFileInfo = (String) itr.next();
							String[] comparisonString = accountFileInfo.split(" ");
							if (comparisonString[2].equals(info[2])) {
								itr.remove();
							}
						}
						accounts.add(String.join(" ", info));
						if(accountChecker.isJointAccount(String.join(" ", info))) {
							accountChecker.applyToJointOwner(String.join(" ", info), customerId);
						}
						BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
						for (String accountToWrite : accounts) {
							bufferedWriter.write(accountToWrite + "\n");
						}
						bufferedWriter.close();
						reader.close();
						MenuSystem.runMenu();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				else {
					System.out.println("No such customer!");
					MenuSystem.runMenu();
				}
			}
		}
		else {
			System.out.println("No such customer!");
			MenuSystem.runMenu();
		}
		
	}

	public void doTransfer() {
		System.out.println("To force a transfer, enter an admin username");
		String username = scanner.nextLine();
		File file = new File("./adminProfiles/" + username);
		if (file.exists()) {
			if (passwordChecker.checkPassword(username, "admin"))
			transferMoney();
		}
		else {
			System.out.println("Sorry. No such user.");
			MenuSystem.runMenu();
		}
	}
	
	private void transferMoney() {
		System.out.println("Enter the username of the customer to transfer money.");
		String userName = scanner.next();
		scanner.nextLine();
		
		File file = new File("./customerAccounts/" + userName);
		if (file.exists()) {
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
					if (!accountChecker .canDoTransactions(accounts.get(accountFromSelection))) {
						System.out.println("Sorry. Accounts must be approved first");
						MenuSystem.runMenu();
					}
					String[] fromAccount = accounts.get(accountFromSelection).split(" "); 
					System.out.println("Enter index number to select an account to tranfer TO");
					int accountToSelection = scanner.nextInt();
					scanner.nextLine();
					if (!accountChecker.canDoTransactions(accounts.get(accountToSelection))) {
						System.out.println("Sorry. Accounts must be approved first");
						MenuSystem.runMenu();
					}
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
						if (accountChecker.isJointAccount(accountToAdd)) {
							accountChecker.applyToJointOwner(accountToAdd, userName);
						}
						
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
			if (!accountChecker.canDoTransactions(accounts.get(accountFromSelection))) {
				System.out.println("Sorry. Accounts must be approved first");
				MenuSystem.runMenu();
			}
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
				if (!accountChecker.canDoTransactions(accounts.get(accountToSelection))) {
					System.out.println("Sorry. Accounts must be approved first");
					MenuSystem.runMenu();
				}
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
					if (accountChecker.isJointAccount(accountToAdd)) {
						accountChecker.applyToJointOwner(accountToAdd, fromMember.getName());
					}
					
					
					//add to account
					String toAccountToAdd = "";
					for(String accountInfo : toAccountString) {
						toAccountToAdd += accountInfo + " ";
					}
					accountsTo.add(toAccountToAdd);
					if (accountChecker.isJointAccount(accountToAdd)) {
						accountChecker.applyToJointOwner(accountToAdd, toMember);
					}
					
					
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
