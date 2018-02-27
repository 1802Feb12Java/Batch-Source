package com.revature.banking.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UserDB {
	public static Connection conn = null;
	static Logger log = Logger.getLogger(UserDB.class.getName());

	/**
	 * Static block that initiates connection to database before instantiation
	 */
	static {
		try {
			log.debug("INITIATING CONNECTION TO DATABASE");
			Properties prop = new Properties();
			prop.load(new FileReader("database.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"),
					prop.getProperty("pwd"));
			log.debug("URL<" + prop.getProperty("url") + "> USERNAME<" + prop.getProperty("usr") + "> PASSWORD<"
					+ prop.getProperty("pwd") + ">");
			log.debug("DATABASE CONNECTION SUCCESSFUL");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e);
		} catch (FileNotFoundException e) {
			System.out.println("Database properties file not found");
			log.error(e);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found");
			log.error(e);
		} catch (IOException e) {
			System.out.println("Unable to read from properties file");
			log.error(e);
		}
	}

	/**
	 * Using the username, obtains personal information from the database, excluding
	 * password, and displays in an easy-to-read format.
	 * 
	 * @param username
	 *            username of the account to be viewed
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static void viewUserInformation(String username) throws SQLException {
		PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM BANK_USER WHERE USERNAME=?");
		ps1.setString(1, username);
		ResultSet rs1 = ps1.executeQuery();

		if (rs1.next()) {
			System.out.println("USER ID:\t" + rs1.getString(9));
			System.out.println("NAME:\t\t" + rs1.getString(3) + " " + rs1.getString(4));
			System.out.println("ADDRESS:\t" + rs1.getInt(5) + " " + rs1.getString(6));
			System.out.println("Zipcode:\t" + rs1.getInt(7));
			System.out.println();
		} else {
			System.out.println("User does not exist.");
		}

		log.debug("USER [" + username + "] VIEWED");
	}

	/**
	 * Uses the username to obtain the password in order to compare for login
	 * purposes
	 * 
	 * @param username
	 *            username of the account being checked
	 * @return the password of the specified account
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static String getPasswordToCheck(String username) throws SQLException {
		PreparedStatement ps1 = conn.prepareStatement("SELECT PASSWORD FROM BANK_USER WHERE USERNAME=?");
		ps1.setString(1, username);
		ResultSet rs1 = ps1.executeQuery();
		String returnThis = null;

		if (rs1.next()) {
			// System.out.println("PASSWORD:\t" + rs1.getString(1));
			returnThis = rs1.getString(1);
		} else {
			return null;
		}

		log.debug("PASSWORD OF [" + username + "] OBTAINED");
		return returnThis;
	}

	/**
	 * Using the username, gets the account type for that specified user. -2=no
	 * account; 0=customer; 1=employee; 2=admin
	 * 
	 * @param username
	 *            username of the account being accessed
	 * @return integer value of the account type as explained in description
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static int getAccountType(String username) throws SQLException {
		int returnThis = -2;

		PreparedStatement ps1 = conn.prepareStatement("SELECT ACCOUNTTYPE FROM BANK_USER WHERE USERNAME=?");
		ps1.setString(1, username);
		ResultSet rs1 = ps1.executeQuery();

		if (rs1.next()) {
			returnThis = rs1.getInt(1);
		} else {
			System.out.println("\nUser does not exist.");
		}

		log.debug("OBTAINED ACCOUNT TYPE FOR USER [" + username + "]; RETURNED [" + returnThis + "]");
		return returnThis;
	}

	/**
	 * Gets an ArrayList of the balances associated with approved accounts for the
	 * specified user
	 * 
	 * @param username
	 *            specified username of the account being accessed
	 * @return an ArrayList of balances associated with the specified user
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static ArrayList<Integer> getBalances(String username) throws SQLException {
		ArrayList<Integer> returnThis = new ArrayList<Integer>();
		DecimalFormat df = new DecimalFormat("#,###,###,###,###,##0.00");
		PreparedStatement ps1 = conn.prepareStatement(
				"SELECT ACCOUNTNUMBER, BALANCE FROM BANK_ACCOUNT WHERE (USER1=? OR USER2=?) AND APPROVED=1 ORDER BY ACCOUNTNUMBER");
		ps1.setString(1, username);
		ps1.setString(2, username);
		ResultSet rs1 = ps1.executeQuery();
		int option = 0;

		if (!rs1.next()) {
			System.out.println("No accounts found for this user.");
		} else {
			do {
				option++;
				String balanceVal = df.format(Double.valueOf(rs1.getString(2)));
				System.out.println(option + " - [" + rs1.getString(1) + "]:\t$" + balanceVal);
				System.out.println();
				returnThis.add(Integer.valueOf(rs1.getString(1)));
			} while (rs1.next());
		}
		log.debug(option + " BALANCES OBTAINED FOR USER [" + username + "]");
		return returnThis;
	}

	/**
	 * Obtains a single balance value using account number instead of username
	 * 
	 * @param accountNum
	 *            the integer value of the account number being accessed
	 * @return a float value that is the balance of the account specified
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static float get1Balance(int accountNum) throws SQLException {
		float balance = 0;

		PreparedStatement ps1 = conn.prepareStatement("SELECT BALANCE FROM BANK_ACCOUNT WHERE ACCOUNTNUMBER=?");
		ps1.setInt(1, accountNum);
		ResultSet rs1 = ps1.executeQuery();

		if (!rs1.next()) {
			System.out.println("No accounts found for this user.");
		} else {
			balance = Float.valueOf(rs1.getString(1));
		}

		log.debug("OBTAINED BALANCE [" + balance + "] FOR ACCOUNT NUMBER [" + accountNum + "]");
		return balance;
	}

	/**
	 * Checks if account number specified is approved and returns a boolean
	 * 
	 * @param accountNumber
	 *            account number of the bank account being checked
	 * @return true if the account is approved; false if the account is pending
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static boolean getApproved(int accountNumber) throws SQLException {
		boolean returnThis = false;

		PreparedStatement ps1 = conn
				.prepareStatement("SELECT APPROVED FROM BANK_ACCOUNT WHERE ACCOUNTNUMBER=? AND APPROVED=1");
		ps1.setInt(1, accountNumber);
		ResultSet rs1 = ps1.executeQuery();

		if (rs1.next()) {
			returnThis = true;
		} else {
			returnThis = false;
		}

		log.debug("CHECKED FOR EXISTENCE OF ACCOUNT_NUMBER [" + accountNumber + "]; RETURNED [" + returnThis + "]");

		return returnThis;
	}

	/**
	 * Obtains the first name of the user associated with the specified username
	 * 
	 * @param username
	 *            username associated with the user account
	 * @return first name of the user who owns the account associated with provided
	 *         username
	 * @throws SQLException
	 *             if SQL query fails
	 */
	public static String getFirstName(String username) throws SQLException {
		String returnThis = null;

		PreparedStatement ps1 = conn.prepareStatement("SELECT FIRSTNAME FROM BANK_USER WHERE USERNAME=?");
		ps1.setString(1, username);
		ResultSet rs1 = ps1.executeQuery();

		if (rs1.next()) {
			returnThis = rs1.getString(1);
		} else {
			System.out.println("User does not exist.");
		}

		log.debug("OBTAINED FIRST NAME OF [" + username + "]; RETURNED [" + returnThis + "]");

		return returnThis;
	}

}