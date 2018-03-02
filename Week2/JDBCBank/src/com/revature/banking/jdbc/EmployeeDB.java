package com.revature.banking.jdbc;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

public class EmployeeDB {
	static Logger log = Logger.getLogger(EmployeeDB.class.getName());

	/**
	 * Method to show all unapproved accounts and allow employee/admin to approve or
	 * deny
	 * 
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void getUnapprovedAccounts() throws SQLException {
		Scanner scin = new Scanner(System.in);
		System.out.println("\t\tUnapproved Accounts\n--------------------------------------------------");
		PreparedStatement ps1 = UserDB.conn.prepareStatement("SELECT * FROM BANK_ACCOUNT WHERE APPROVED=0");
		ResultSet rs1 = ps1.executeQuery();
		int option = 0;
		ArrayList<Integer> unapprovedAccounts = new ArrayList<Integer>();

		while (rs1.next()) {
			option++;
			System.out.println("[Option " + option + "]");
			if (Integer.valueOf(rs1.getString(1)) == 1) {
				// joint
				System.out.println("Account Type:\tJoint");
				System.out.println("Primary User:\t" + rs1.getString(3));
				System.out.println("Secondary User:\t" + rs1.getString(4));
			} else {
				// single user
				System.out.println("Account Type:\tSingle");
				System.out.println("User:\t\t" + rs1.getString(3));
			}

			System.out.println("Account Number:\t" + rs1.getString(6));
			System.out.println();
			unapprovedAccounts.add(Integer.valueOf(rs1.getString(6)));
		}
		System.out.println("--------------------------------------------------");

		if (option == 0) {
			System.out.println("No pending accounts");
		}

		boolean isValid = false;
		while (!isValid && option > 0) {
			System.out.println("Which account would you like to approve/deny? Select using number beside \"option\". [0: quit]");
			int chooseAccount = Integer.valueOf(scin.nextLine());
			if (chooseAccount > 0 && chooseAccount <= unapprovedAccounts.size()) {
				System.out.println("Account:\t" + unapprovedAccounts.get(chooseAccount - 1));
				System.out.println("[0: Deny | 1: Approve | 2: quit]");
				int chooseOption = Integer.valueOf(scin.nextLine());
				if (chooseOption >= 0 && chooseOption < 2) {
					approveOrDenyAccount(unapprovedAccounts.get(chooseAccount - 1), chooseOption);
					isValid = true;
				} else if (chooseOption == 2) {
					isValid = true;
				}
			} else if (chooseAccount == 0) {
				isValid = true;
			}
		}

	}

	/**
	 * Method used in communicating with database to mark account as approved. Used
	 * in method {@link #getUnapprovedAccounts()} after receiving user input and
	 * validation
	 * 
	 * @param accountNumber
	 *            account number to be approved or denied
	 * @param approveDeny
	 *            1 if account is approved; 0 if account is denied
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void approveOrDenyAccount(int accountNumber, int approveDeny) throws SQLException {
		// 1 = approve; 0 = deny
		if (approveDeny == 0) {
			PreparedStatement ps1 = UserDB.conn.prepareStatement("DELETE FROM BANK_ACCOUNT WHERE ACCOUNTNUMBER=?");
			ps1.setInt(1, accountNumber);
			ps1.execute();
			System.out.println("Account [" + accountNumber + "] denied.");
			log.info("ACCOUNT [" + accountNumber + "] DENIED");
		} else {
			PreparedStatement ps1 = UserDB.conn
					.prepareStatement("UPDATE BANK_ACCOUNT SET APPROVED=1 WHERE ACCOUNTNUMBER=?");
			ps1.setInt(1, accountNumber);
			ps1.execute();
			System.out.println("Account [" + accountNumber + "] approved.");
			log.info("ACCOUNT [" + accountNumber + "] APPROVED");
		}
	}

	/**
	 * User interface for employees shown after login. Console-based interface that
	 * allows employees to perform necessary activities
	 * 
	 * @param user
	 *            username of the employee that logged in
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void employeeMenu(String user) throws SQLException {
		Scanner input = new Scanner(System.in);
		System.out.println("\nEmployee");
		boolean stillInLoop = true;
		while (stillInLoop) {
			System.out
					.println("\nWhat would you like to do?\n[1: View User, 2: Approve/Deny Applications, 3: Log Out]");
			int option = Integer.valueOf(input.nextLine());
			switch (option) {
			case 1:
				System.out.print("Enter account username:\t");
				String viewed = input.nextLine();
				UserDB.viewUserInformation(viewed);
				break;
			case 2:
				EmployeeDB.getUnapprovedAccounts();
				break;
			case 3:
				System.out.println("Logging out of " + user);
				log.info("USER [" + user + "] LOGGED OUT");
				stillInLoop = false;
				break;
			default:
				System.out.println("Please enter correct input.");
				break;
			}
		}
	}

}