package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class CustomerServices {
	final static Logger logger = Logger.getLogger(CustomerServices.class);
	BankAccountDAO bankAccountDAO;
	CustomerDAO customerDAO;
	static Scanner scanner = new Scanner(System.in);
	
	public CustomerServices(Connection connection) {
		bankAccountDAO = new BankAccountDAO(connection);
		customerDAO = new CustomerDAO(connection);
	}
	
	public void makeDeposit(List<BankAccount> bankAccounts) {
		if (bankAccounts.isEmpty()) {
			System.out.println("You have no open/approved accounts at this time");
		}
		else {
			System.out.println("Please select an account to make a deposit");
			int i = 0;
			for(BankAccount account : bankAccounts) {
				if (account.getApprovalStatus().equals("A")) {
					System.out.println(i + ". " + account.toString() + "\n");
					i++;
				}
			}
			int accountChoice = -1;
			while(accountChoice < 0 || accountChoice > i) {
				accountChoice = Integer.valueOf(scanner.nextLine());
				if (accountChoice < 0 && accountChoice > i) {
					throw new AccountException();
				}
			}
			float amountToDeposit = 0f;
			boolean depositFlag = true;
			while(depositFlag) {
				System.out.println("How much would you like to deposit?");
				amountToDeposit = Float.valueOf(scanner.nextLine());
				if (amountToDeposit < 1) {
					throw new AccountException();
				}
				else {
					depositFlag = false;
				}
			}
			bankAccountDAO.updateBalance(bankAccounts.get(accountChoice).getAccountId(),
								 		amountToDeposit,
								 		bankAccounts.get(accountChoice).getBalance());
			System.out.println("Deposit of " + amountToDeposit + " successful\n");
		}
	}
	
	public void makeWithdrawl(List<BankAccount> bankAccounts) {
		if (bankAccounts.isEmpty()) {
			System.out.println("You have no open/approved accounts at this time");
		}
		else {
			System.out.println("Please select an account to make a withdrawl");
			int i = 0;
			for(BankAccount account : bankAccounts) {
				if (account.getApprovalStatus().equals("A")) {
					System.out.println(i + ". " + account.toString() + "\n");
					i++;
				}
			}
			int accountChoice = -1;
			while(accountChoice < 0 || accountChoice > i) {
				accountChoice = Integer.valueOf(scanner.nextLine());
				if (accountChoice < 0 && accountChoice > i) {
					System.out.println("Error. Please enter a valid choice.");
				}
			}
			float amountToWithdraw = 0f;
			boolean withdrawlFlag = true;
			while(withdrawlFlag) {
				System.out.println("How much would you like to withdraw? Enter a positive number.");
				amountToWithdraw = Float.valueOf(scanner.nextLine());
				if (amountToWithdraw < 1 || amountToWithdraw > bankAccounts.get(accountChoice).getBalance()) {
					System.out.println("Please enter an amount that is grater than 0 and less than your current balance.");
					throw new AccountException();
				}
				else {
					withdrawlFlag = false;
				}
			}
			bankAccountDAO.updateBalance(bankAccounts.get(accountChoice).getAccountId(),
								 		-amountToWithdraw,
								 		bankAccounts.get(accountChoice).getBalance());
			System.out.println("Withdrawl of: " + amountToWithdraw + " successful\n");
		}
	}
	
	public void deleteAccount(List<BankAccount> bankAccounts) {
		System.out.println("Only accounts which have a zero balance may be deleted.");
		System.out.println("Please select and account");
		List<BankAccount> emptyAccounts = new ArrayList<BankAccount>();
		for (BankAccount account : bankAccounts) {
			if (account.getBalance() == 0) {
				emptyAccounts.add(account);
			}
		}
		int i = 0;
		if (emptyAccounts.isEmpty()) {
			System.out.println("You have no accounts with a 0 balance currently");
			System.out.println("Please transfer or withdraw money to delete an account");
		}
		else {
			System.out.println("Please select an account to delete");
			for (BankAccount account : emptyAccounts) {
				System.out.println(i + ". " + account.toString() + "\n");
				i++;
			}
			int menuChoice = -1;
			while (menuChoice < 0 || menuChoice > i) {
				menuChoice = Integer.valueOf(scanner.nextLine());
				if (menuChoice < 1 || menuChoice > i) {
					System.out.println("Error. Please enter a valid choice.");
				}
			}
			customerDAO.deleteAccount(emptyAccounts.get(menuChoice).getAccountId());
			System.out.println("Account Deleted.\n");
		}
	}
}
