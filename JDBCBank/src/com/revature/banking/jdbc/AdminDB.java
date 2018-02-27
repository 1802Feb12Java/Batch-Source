package com.revature.banking.jdbc;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

public class AdminDB {
	static Logger log = Logger.getLogger(AdminDB.class.getName());

	/**
	 * Asks administrator for username to delete specified account
	 * 
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void deleteAccount() throws SQLException {
		Scanner downTheRabbitHole = new Scanner(System.in);
		System.out.print("Enter the username to be deleted:\t");
		String banHammered = downTheRabbitHole.nextLine();

		// if user doesn't exist
		if (UserDB.getAccountType(banHammered) == -2) {
			System.out.println("User " + banHammered + " not found for deletion");
			return;
		}

		PreparedStatement ps3 = UserDB.conn.prepareStatement("DELETE FROM BANK_USER WHERE USERNAME=?");
		ps3.setString(1, banHammered);
		ps3.execute();
		System.out.println(banHammered + " has been deleted");
		log.info("ADMINISTRATOR DELETED USER [" + banHammered + "]");
	}

	/**
	 * User interface for administrators shown after login
	 * 
	 * @param user
	 *            username of administrator accessing the menu
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void adminMenu(String user) throws SQLException {
		Scanner input = new Scanner(System.in);
		System.out.println("\nAdministrator");
		boolean stillInLoop = true;
		while (stillInLoop) {
			System.out.println(
					"\nWhat would you like to do?\n[1: View User, 2: Cancel Account, 3: Edit Account, 4: Approve/Deny Applications 5: Log Out]");
			int option = Integer.valueOf(input.nextLine());
			switch (option) {
			case 1:
				System.out.print("Enter account username:\t");
				String viewed = input.nextLine();
				UserDB.viewUserInformation(viewed);
				break;
			case 2:
				AdminDB.deleteAccount();
				break;
			case 3:
				AdminDB.adminEdit();
				break;
			case 4:
				EmployeeDB.getUnapprovedAccounts();
				break;
			case 5:
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

	/**
	 * Validates administrator input and allows admin to change personal information
	 * of specified user. Calls {@link CustomerDB#applicationProcess()} to validate
	 * input and obtain values being committed to database
	 * 
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void adminEdit() throws SQLException {
		Scanner readThe = new Scanner(System.in);

		// get username and check if exists
		System.out.println("Enter username of account to be edited:");
		String modifyAcc = readThe.nextLine();

		// if user exists
		if (UserDB.getAccountType(modifyAcc) != -2) {
			UserDB.viewUserInformation(modifyAcc);

			String[] updateWithThis = CustomerDB.applicationProcess();
			String statement = "{call UPDATE_BANK_USER (?, ?, ?, ?, ?, ?)}";
			CallableStatement cs4 = UserDB.conn.prepareCall(statement);
			cs4.setString(1, modifyAcc);
			cs4.setString(2, updateWithThis[0]);
			cs4.setString(3, updateWithThis[1]);
			cs4.setString(4, updateWithThis[2]);
			cs4.setString(5, updateWithThis[3]);
			cs4.setString(6, updateWithThis[4]);
			cs4.execute();
			System.out.println("Update successful");
			log.info("USER [" + modifyAcc + "] UPDATED");
		} else {
			return;
		}

	}

}