package com.revature.banking.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class CustomerDB extends UserDB {
	static Logger log = Logger.getLogger(CustomerDB.class.getName());

	/**
	 * Creates an "application" for an account by creating an account that has not
	 * been approved yet
	 * 
	 * @param user
	 *            username associated with account being applied for
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void applyForAccount(String user) throws SQLException {
		Scanner input = new Scanner(System.in);
		int singleOrJoint = 0;
		boolean isBeingAGenius = true;
		while (isBeingAGenius) {
			System.out.println("What type of account would you like to apply for?\n[1: Single, 2: Joint]");
			singleOrJoint = Integer.valueOf(input.nextLine());

			if (singleOrJoint >= 1 && singleOrJoint <= 2) {
				if (singleOrJoint == 1) {
					CallableStatement cs2 = UserDB.conn.prepareCall("{call ADD_BANK_ACCOUNT_SINGLE(?)}");
					cs2.setString(1, user);
					cs2.execute();
					System.out.println("Applied for a single account.");
					isBeingAGenius = false;
				} else {
					// get other user
					System.out.print("\nPlease enter the secondary user:\t");
					String user2 = input.nextLine();
					if (UserDB.getAccountType(user2) == -2) {
						System.out.println("\nSecondary user not found.");
						break;
					}
					CallableStatement cs3 = UserDB.conn.prepareCall("{call ADD_BANK_ACCOUNT_JOINT(?, ?)}");
					cs3.setString(1, user);
					cs3.setString(2, user2);
					cs3.execute();
					System.out.println("\nApplied for a joint account.");
					isBeingAGenius = false;

				}
			} else {
				System.out.println("\nPlease choose 1 (single) or 2 (joint).");
			}
		}

		log.info("USER [" + user + "] APPLIED FOR ACCOUNTTYPE [" + (singleOrJoint - 1) + "]");
	}

	/**
	 * Checks for existing user and prompts for information to register
	 * 
	 * @param username
	 *            intended username of new user
	 * @param password
	 *            password used to create a new user
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void createCustomer(String username, String password) throws SQLException {
		// if already exists
		if (getAccountType(username) != -2) {
			System.out.println("User already exists.");
		}
		// create if doesn't exist
		else {
			String[] insertThis = applicationProcess();
			String statement = "{call ADD_BANK_USER (?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs4 = UserDB.conn.prepareCall(statement);
			cs4.setString(1, username);
			cs4.setString(2, password);
			cs4.setString(3, insertThis[0]);
			cs4.setString(4, insertThis[1]);
			cs4.setString(5, insertThis[2]);
			cs4.setString(6, insertThis[3]);
			cs4.setString(7, insertThis[4]);
			cs4.execute();
			System.out.println("User created.");

			log.info("CREATED USER [" + username + "]");
		}
	}

	/**
	 * Validates input for users creating a new user and for administrators updating
	 * users
	 * 
	 * @return a String array containing first name, last name, address number,
	 *         address street, and zip code
	 */
	public static String[] applicationProcess() {
		boolean done = false;
		Scanner sc = new Scanner(System.in);
		String name = null;
		String address = null;
		String[] returnThis = new String[5];

		while (!done) {
			System.out.println("Please enter first and last name. [Enter \"c\" to cancel]");
			name = sc.nextLine();
			
			if (name.toUpperCase().equals("C")) {
				done = true;
				return null;
			}
			
			String[] splitName = name.split(" ");
			if (splitName.length != 2) {
				continue;
			}
			System.out.println("Please enter address in this format[1234:Street Rd:90210]");
			address = sc.nextLine();
			String[] splitAddress = address.split(":");
			if (splitAddress.length != 3) {
				continue;
			}

			System.out.println("Does this look correct? (Y/N)\n\tFirst Name:\t" + splitName[0] + "\n\tLast Name:\t"
					+ splitName[1]);
			System.out.println("\n\tAddress:\t" + String.valueOf(splitAddress[0]) + " "
					+ String.valueOf(splitAddress[1]) + "\n\tZip Code:\t" + String.valueOf(splitAddress[2]));

			String yesOrNo = sc.nextLine();
			yesOrNo = yesOrNo.substring(0, 1).toUpperCase();

			switch (yesOrNo) {
			case "Y":
				done = true;
				returnThis[0] = splitName[0];
				returnThis[1] = splitName[1];
				returnThis[2] = splitAddress[0];
				returnThis[3] = splitAddress[1];
				returnThis[4] = splitAddress[2];
				break;
			case "N":
				break;
			default:
				System.out.println("Please enter correct input.");
			}
		}
		return returnThis;
	}

	/**
	 * Allows user to withdraw money from one of their own accounts. Uses
	 * {@link #setBalance(int, float)} after validation and calculation
	 * 
	 * @param username
	 *            username of the owner of the accounts being withdrawn from
	 * @return the amount that is withdrawn. NOT THE FINAL BALANCE!
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static float withdraw(String username) throws SQLException {
		Scanner scan = new Scanner(System.in);
		float amount = 0;
		ArrayList<Integer> balanceList = UserDB.getBalances(username);
		
		//no accounts
		if (balanceList.size() == 0) {
			return 0.0F;
		}
		
		System.out.print("Select account to withdraw from (number to the left of [account #]):\t");
		int accountOption = Integer.valueOf(scan.nextLine());
		if (accountOption > 0 && accountOption <= balanceList.size()) {
			int accountChosen = balanceList.get(accountOption - 1);
			System.out.println("Account:\t" + accountChosen);

			System.out.print("Enter withdrawal amount:\t");
			amount = Float.valueOf(scan.nextLine());
			if (amount > 0 && amount <= UserDB.get1Balance(accountChosen)) {
				float finalAmount = UserDB.get1Balance(accountChosen) - amount;
				setBalance(accountChosen, finalAmount);
				System.out.println("\n$" + amount + " withdrawn from account " + accountChosen);
				log.info("USER [" + username + "] WITHDREW [" + amount + "] FROM ACCOUNT [" + accountChosen + "]");
				System.out.println("Final Balance:\t" + finalAmount);
			} else {
				System.out.println("Invalid input");
				amount = 0;
			}
		} else {
			System.out.println("Incorrect account choice.");
			amount = 0;
		}
		return amount;
	}

	/**
	 * Allows customer to add money to their own accounts. Uses
	 * {@link #setBalance(int, float)} method after validation and calculations
	 * 
	 * @param username
	 *            username of owner of accounts
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void deposit(String username) throws SQLException {
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> balanceList = UserDB.getBalances(username);

		if (balanceList.size() == 0) {
			return;
		}

		System.out.print("Select account to deposit to (number to the left of [account #]):\\t");
		int accountOption = Integer.valueOf(scan.nextLine());
		if (accountOption > 0 && accountOption <= balanceList.size()) {
			int accountChosen = balanceList.get(accountOption - 1);
			System.out.println("Account:\t" + accountChosen);

			System.out.print("Enter deposit amount:\t");
			float amount = Float.valueOf(scan.nextLine());
			if (amount > 0) {
				float finalAmount = UserDB.get1Balance(accountChosen) + amount;
				setBalance(accountChosen, finalAmount);
				System.out.println("\n$" + amount + " deposited to account " + accountChosen);
				log.info("USER [" + username + "] DEPOSITED [" + amount + "] TO ACCOUNT [" + accountChosen + "]");
				System.out.println("Final Balance:\t" + finalAmount);
			} else {
				System.out.println("Invalid input");
			}
		} else {
			System.out.println("Incorrect account choice.");
		}
	}

	/**
	 * Transfers balance to account specified and validated through user input. Uses
	 * {@link #withdraw(String)} and {@link #setBalance(int, float)} methods
	 * 
	 * @param username
	 *            username of the account where the transaction is originating from
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void transfer(String username) throws SQLException {
		Scanner beamMeUpScotty = new Scanner(System.in);
		System.out.print("Enter recipient account number (9 digits):\t");
		int recipient = Integer.valueOf(beamMeUpScotty.nextLine());
		// check if account number exists
		if (UserDB.getApproved(recipient)) {
			float amount = withdraw(username);
			float afterMath = get1Balance(recipient) + amount;
			setBalance(recipient, afterMath);
			log.info("USER [" + username + "] TRANSFERRED [" + amount + "] TO ACCOUNT [" + recipient + "]");
		} else {
			System.out.println("Account number not eligible to receive funds");
		}
	}

	/**
	 * Sets balance of specified account. Used in {@link #withdraw(String)} and
	 * {@link #deposit(String)} methods
	 * 
	 * @param accountChosen
	 *            account number of the account being transacted upon
	 * @param finalAmount
	 *            value obtained through math within Java client that will be set in
	 *            the database for specified account
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void setBalance(int accountChosen, float finalAmount) throws SQLException {
		String statement = "{call SETBALANCE_BANK_ACCOUNT (?, ?)}";
		CallableStatement cs4 = UserDB.conn.prepareCall(statement);
		cs4.setInt(1, accountChosen);
		cs4.setFloat(2, finalAmount);
		cs4.execute();
	}

	/**
	 * Allows the user to delete accounts associated with that user that have a
	 * balance of $0
	 * 
	 * @param username
	 *            username associated with empty accounds being deleted
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void deleteEmptyAccounts(String username) throws SQLException {
		String statement = "{call DELETE_EMPTY_ACCOUNTS (?)}";
		CallableStatement cs4 = UserDB.conn.prepareCall(statement);
		cs4.setString(1, username);
		cs4.execute();
		System.out.println("All empty accounts deleted");
		log.info("USER [" + username + "] DELETED EMPTY ACCOUNTS");
	}

	/**
	 * Menu shown for customers after login
	 * 
	 * @param user
	 *            username of customer accessing the menu
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void customerMenu(String user) throws SQLException {
		Scanner input = new Scanner(System.in);
		boolean b2a = true;
		while (b2a) {
			System.out.println(
					"\nWhat would you like to do?\n[0: Balance, 1: Withdraw, 2: Deposit, 3: Transfer, 4: Apply for account, 5: Delete empty accounts, 6: Log out]");
			int branchTwoA = Integer.valueOf(input.nextLine());

			switch (branchTwoA) {
			case 0:
				UserDB.getBalances(user);
				break;
			case 1:
				CustomerDB.withdraw(user);
				break;
			case 2:
				CustomerDB.deposit(user);
				break;
			case 3:
				CustomerDB.transfer(user);
				break;
			case 4:
				CustomerDB.applyForAccount(user);
				break;
			case 5:
				CustomerDB.deleteEmptyAccounts(user);
				break;
			case 6:
				System.out.println("Logging out of " + user);
				log.info("USER [" + user + "] LOGGED OUT");
				b2a = false;
				break;
			default:
				System.out.println("Please enter valid input.");
			}
		}
	}

}