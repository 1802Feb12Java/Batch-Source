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
				
			}
			else {
				MenuSystem.runMenu();
			}
		}
		
	}

	public void editCustomerAccount() {
		System.out.println("To register a new employee, enter your admin username");
		String userName = scanner.nextLine();
		File file = new File("./adminProfiles/" + userName);
		if (file.exists()) {
			if(passwordChecker.checkPassword(userName, "admin")) {
				System.out.println("What would you like to do?");
				System.out.println("Enter a nu");
			}
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

	public void forceTransfer() {
		// TODO Auto-generated method stub
		
	}

	public void deleteAccount() {
		System.out.println("To delete accounts, please enter your username.");
		String userName = scanner.nextLine();
		
		File file = new File("./adminProfiles/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "admin")) {
				System.out.println("Enter a customer ID to approve accounts");
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

}
