package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Customer extends User {
	
	final static Logger logger = Logger.getLogger(Customer.class);
	static Scanner scanner = new Scanner(System.in);
	
	private CustomerDAO customerDAO;
	private BankAccountServices bankAccountServices;
	private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	
	protected Customer(int userId, String userType, Connection connection) {
		super(userId, userType, connection);
		this.customerDAO = new CustomerDAO(connection);
		this.bankAccountServices = new BankAccountServices(connection);
		this.firstName = customerDAO.getFirstName(userId);
		this.lastName = customerDAO.getLastName(userId);
		this.userName = customerDAO.getUsername(userId);
		this.bankAccounts = bankAccountServices.getBankAccountList(this.userId);
	}
	
	@Override
	protected void runMenu() {
		System.out.println("Hello, " + firstName + ".");
		System.out.println("How can we assist you today?");
		System.out.println("1. Apply for a new account");
		System.out.println("2. View your accounts");
		System.out.println("3. View your personal information");
		System.out.println("4. Make a deposit");
		System.out.println("5. Make a withdrawl");
		System.out.println("6. Make a transfer");
		System.out.println("7. Log Out");
		
		int menuOption = 0;
		try {
			menuOption = Integer.valueOf(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Number conversion error in customer menu");
			logger.error("Number conversion error in customer menu.");
		}
		
		while (menuOption != 7) {
			switch (menuOption) {
			case 1:
				bankAccountServices.applyForAccount(this.userId);
				this.runMenu();
				break;
			case 2:
				break;
			case 3:
				System.out.println("Here is the information we have on file for you:");
				System.out.println(this.toString());
				this.runMenu();
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			default:
				System.out.println("Please enter a valid option.");
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "Customer [userName=" + this.userName + ", firstName=" + this.firstName + ", lastName=" + this.lastName + "]";
	}
	
	
	
	

}
