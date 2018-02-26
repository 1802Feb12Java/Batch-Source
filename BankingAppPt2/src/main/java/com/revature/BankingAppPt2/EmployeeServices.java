package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class EmployeeServices {
	final static Logger logger = Logger.getLogger(EmployeeServices.class);
	static Scanner scanner = new Scanner(System.in);
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;
	private BankAccountDAO bankAccountDAO;
	
	public EmployeeServices(Connection connection) {
		this.employeeDAO = new EmployeeDAO(connection);
		this.customerDAO = new CustomerDAO(connection);
		this.bankAccountDAO = new BankAccountDAO(connection);
	}
	public void approveAccounts() {
		List<BankAccount> pendingAccounts = employeeDAO.getPendingAccounts();
		//get list of pending accounts
		if (pendingAccounts.isEmpty()) {
			System.out.println("There are no accounts pending at this time.");
		}
		
		while(!pendingAccounts.isEmpty()) {
			int i = 0;
			System.out.println("Please select an account to approve.");
			for (BankAccount bankAccount : pendingAccounts) {
				System.out.println(i + ". " + bankAccount.toString() + "\n");
				i++;
			}
			
			int accountChoice = Integer.valueOf(scanner.nextLine());
			System.out.println("\nYou chose account: " + pendingAccounts.get(accountChoice).getAccountId());
			System.out.println("What would you like to do?\n1. Approve\n2. Deny");
			
			int approvalChoice = 0;
			while (approvalChoice !=1 && approvalChoice != 2) {
				approvalChoice = Integer.valueOf(scanner.nextLine());
				switch (approvalChoice) {
				case 1:
					employeeDAO.approveAccount("A", pendingAccounts.get(accountChoice).getAccountId());
					break;
				case 2:
					employeeDAO.approveAccount("D", pendingAccounts.get(accountChoice).getAccountId());
					break;
				default:
					System.out.println("Error. Please make a valid choice.");
					break;
				}
			}
			pendingAccounts.remove(accountChoice);
		}
	}
	
	public void viewCustomerInformation() {
		System.out.println("To view customer information, type a customer's user name.");
		String customerUserName = scanner.nextLine();
		String customerString = "";
		int customerId = -1;
		while (customerId < 1) {
			customerId = customerDAO.getUserId(customerUserName);
			if (customerId < 1) {
				System.out.println("Error. No such customer. Try again.");
			}
		}
		customerString = "UserID: " + customerId + "\nUsername: " + customerUserName +
						"\nFirst Name: " + customerDAO.getFirstName(customerId) +
						"\nLast Name: " + customerDAO.getLastName(customerId);
		
		System.out.println(customerString);
		System.out.println("\nThe Accounts on file for this customer are:\n");
		List<BankAccount> bankAccountList = bankAccountDAO.getBankAccountList(customerId);
		for (BankAccount account : bankAccountList) {
			System.out.println(account.toString());
			System.out.println("\n");
		}

	}
}
