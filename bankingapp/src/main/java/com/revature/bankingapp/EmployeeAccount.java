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

public class EmployeeAccount {
	private static Scanner scanner = new Scanner(System.in);
	private PasswordChecker passwordChecker = new PasswordChecker();
	public void viewCustomerInfo() {
		System.out.println("To view customer info, please enter your username.");
		String userName = scanner.nextLine();
		
		File file = new File("./employeeProfiles/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "employee")) {
				System.out.println("Enter a customer ID to view customer information");
				String customerId = scanner.nextLine();
				file = new File("./customerProfiles/" + customerId);
				if (file.exists()) {
					try {
						System.out.println("Customer Profile:");
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String customerInformation = reader.readLine();
						String[] customerArray = customerInformation.split(":");
						System.out.println("Username: " + customerArray[0] + 
											"\nPassword: " + customerArray[1] +
											"\nFirst Name: " + customerArray[2] +
											"\nLast Name: " + customerArray[3]);
						reader.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				else {
					System.out.println("Sorry. No such customer.");
					MenuSystem.runMenu();
				}
				System.out.println("Accounts associated with this customer: ");
				file = new File("./customerAccounts/" + customerId);
				if (file.exists()) {
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						while ((line = reader.readLine()) != null) {
							System.out.println(line);
						}
						reader.close();
						MenuSystem.runMenu();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void approveCustomerAccount() {
		System.out.println("To approve or deny accounts, please enter your username.");
		String userName = scanner.nextLine();
		
		File file = new File("./employeeProfiles/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "employee")) {
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
						AccountChecker ac = new AccountChecker();
						for (String accountToWrite : accounts) {
							bufferedWriter.write(accountToWrite + "\n");
							if (ac.isJointAccount(accountToWrite)) {
								ac.applyToJointOwner(accountToWrite, customerId);
							}
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
