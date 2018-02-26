package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class CustomerServices {
	final static Logger logger = Logger.getLogger(CustomerServices.class);
	BankAccountDAO bankAccountDAO;
	static Scanner scanner = new Scanner(System.in);
	
	public CustomerServices(Connection connection) {
		bankAccountDAO = new BankAccountDAO(connection);
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
					System.out.println("Error. Please enter a valid choice.");
				}
			}
			float amountToDeposit = 0f;
			boolean depositFlag = true;
			while(depositFlag) {
				System.out.println("How much would you like to deposit?");
				amountToDeposit = Float.valueOf(scanner.nextLine());
				if (amountToDeposit < 1) {
					System.out.println("That's not a valid amount.");
				}
				else {
					depositFlag = false;
				}
			}
			bankAccountDAO.updateBalance(bankAccounts.get(accountChoice).getAccountId(),
								 		amountToDeposit,
								 		bankAccounts.get(accountChoice).getBalance());
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
				}
				else {
					withdrawlFlag = false;
				}
			}
			bankAccountDAO.updateBalance(bankAccounts.get(accountChoice).getAccountId(),
								 		-amountToWithdraw,
								 		bankAccounts.get(accountChoice).getBalance());
		}
	}
	
	public void makeTransfer(List<BankAccount> bankAccounts) {
		int amountToTransfer = 0;
		int transferFromAccountId = -1;
		int transferToAccountId = -1;
		
		if (bankAccounts.isEmpty()) {
			System.out.println("You have no open/approved accounts at this time");
		}
		else {
			System.out.println("Please select an account to transfer FROM\nType an accountId");
			for(BankAccount account : bankAccounts) {
				System.out.println(account.toString() + "\n");
			}
			while(transferFromAccountId < 0) {
				transferFromAccountId = Integer.valueOf(scanner.nextLine());
				if (transferFromAccountId < 0 ) {
					System.out.println("Error. Please enter a valid choice.");
				}
			}
		}
		
		System.out.println("Would you like to transfer to an account you own?");
		int menuChoice = 0;
		while (menuChoice != 1 && menuChoice != 2) {
			System.out.println("1. Yes\n2. No");
			menuChoice = Integer.valueOf(scanner.nextLine());
		}
		//transfer to own account
		if (menuChoice == 1) {
			//display accounts that are approved and not the one already selected
		while(transferToAccountId < 0 || transferFromAccountId == transferToAccountId) {
			for(BankAccount account : bankAccounts) {
				System.out.println(account.toString() + "\n");
			}

			System.out.println("Please select and account to transfer TO");

				transferToAccountId = Integer.valueOf(scanner.nextLine());
				if (transferToAccountId < 0 || transferFromAccountId == transferToAccountId) {
					System.out.println("Error. Please enter a valid choice. From account should not equal To account.");
				}
			}
			boolean transferFlag = true;

			while (transferFlag) {
				System.out.println("Enter an amount to transfer");
				amountToTransfer = Integer.valueOf(scanner.nextLine());
				if (amountToTransfer < 1 || amountToTransfer > bankAccountDAO.getBalance(transferFromAccountId)) {
					System.out.println("Sorry that's not a valid amount.");
					System.out.println("Enter an amount that is less than your current balance and more than 0;");
				}
				else {
					transferFlag = false;
				}
			}
			
			//perform actual transfer
			bankAccountDAO.updateBalance(transferFromAccountId, -amountToTransfer, bankAccountDAO.getBalance(transferFromAccountId));
			bankAccountDAO.updateBalance(transferToAccountId, amountToTransfer, bankAccountDAO.getBalance(transferToAccountId));
			return; //break out of function and go back to menu
		}
		//transfer to other member's account
		else {
			
		}
	}
}
