package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class BankAccountServices {
/*	@SuppressWarnings("unused")
	private Connection connection;*/
	private CustomerDAO customerDAO;
	final static Logger logger = Logger.getLogger(BankAccountServices.class);
	static Scanner scanner = new Scanner(System.in);
	
	BankAccountDAO bankAccountDAO;
	public BankAccountServices(Connection connection) {
		//this.connection = connection;
		this.bankAccountDAO = new BankAccountDAO(connection);
		this.customerDAO =  new CustomerDAO(connection);
	}
	
	public void applyForAccount(int userId) {
		int menuOption = 0;
		String accountType = "";
		int jointOwnerId = 0;
		
		System.out.println("Applying for new account");
		
		while (menuOption != 1 && menuOption != 2) {
			System.out.println("Which type of account would you like to apply for?");
			System.out.println("1. Checking\n2. Savings");
			try {
				menuOption = Integer.valueOf(scanner.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a valid number");
				logger.error("Error converting number in BankAccountServices");
				logger.error(e.toString());
				
			}
			switch (menuOption) {
			case 1:
				accountType = "checking";
				break;
			case 2:
				accountType = "savings";
				break;
			default:
				System.out.println("Error selecting option");
				break;
			}
		}
		
		menuOption = 0;
		while (menuOption != 1 && menuOption != 2) {
			System.out.println("Is this a joint account?");
			System.out.println("1. Yes\n2. No");
			try {
				menuOption = Integer.valueOf(scanner.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a valid number");
				logger.error("Error converting number in BankAccountServices");
				logger.error(e.toString());
				
			}
			
			switch (menuOption) {
			case 1:
				while (jointOwnerId == 0) {
					System.out.println("Please enter the username of the joint account holder.");
					jointOwnerId = customerDAO.getUserId(scanner.nextLine());
					if (jointOwnerId == 0) {
						System.out.println("Error. No Such user");
					}
				}

				break;
			case 2:
				break;
			default:
				System.out.println("Error selecting option");
				break;
			}
		}
		
		
		boolean successful = bankAccountDAO.addAccount(userId, accountType, jointOwnerId);
		
		if (successful) {
			System.out.println("Your account was created sucessfully.");
			System.out.println("Your account is pending approval");
			logger.info("New account pending approval from user with ID: " + userId);
		}
		else {
			System.out.println("Something went wrong with creating your account. Try again later");
			logger.error("Error creating account. DAO returned false");
		}
	}
	public List<BankAccount> getBankAccountList(int userId) {
		return bankAccountDAO.getBankAccountList(userId);
	}
	
}
