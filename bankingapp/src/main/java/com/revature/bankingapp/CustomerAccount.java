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
	
	public void viewCustomerAccounts() {
		System.out.println("Enter the username of the customer to view accounts.");
		String userName = scanner.next();
		scanner.nextLine();
		
		File file = new File("./customerAccounts/" + userName);
		if (file.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
				reader.close();
				MenuSystem.runMenu();
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
				if (jointAccount.contains("y") || jointAccount.contains("y"))
					account += " joint ";
				else
					account += " single ";
				account += Math.abs((new Random().nextLong()));
				account += " 0 ";
				account += " PENDING\n";
				writer.write(account);
				writer.close();
				MenuSystem.runMenu();
			} catch (IOException e) {
				e.printStackTrace();
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
				int withdrawl = scanner.nextInt();
				scanner.nextLine();
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
				
				//readd accounts to file
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
			System.out.println("Sorry. No such customer.");
			scanner.nextLine();
			MenuSystem.runMenu();
		}
		
	}
	public void transferMoney() {
		// TODO Auto-generated method stub
		
	}
}
