package com.revature.bankingappnew.daoimplementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import com.revature.bankingappnew.Account;
import com.revature.bankingappnew.ConnectionHandling;
import com.revature.bankingappnew.Customer;
/*
 * 
 */
public class AccountLogImp {
	
	private static Logger log = Logger.getLogger(AccountLogImp.class.getName());
	
	public static void createAccountRecord(String accountStatus, String accountType, double balance, String ... username) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "INSERT INTO accountLog (accountID, userID, adtnlUser, accountStatus, accountType, balance)"
				+ " VALUES (getNextAccountID(), ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(3, accountStatus);
		statement.setString(4, accountType);
		statement.setDouble(5, balance);
		if(username.length > 1) {
			String usernameOne = username[0];
			String usernameTwo = username[1];
			statement.setInt(1, UserLogImp.getUserID(usernameOne));
			statement.setInt(2, UserLogImp.getUserID(usernameTwo));
		} else {
			String usernameOnly = username[0];
			statement.setInt(1, UserLogImp.getUserID(usernameOnly));
			statement.setNull(2, Types.INTEGER);
		}
		
		
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("The new " + accountType + " account has been successfully added to the account log!");
			System.out.println("***************************************************************");
			System.out.println();
			log.info("Data Base Update: A new record has been added to the accountLog table.");
			log.debug("Debug Message: A new record was added to the accountLog table.");
		}
		
	}//end createAccountRecord method
	
	public static void viewUserAccounts(String username) throws SQLException {
		//this method allows for 'all' to be given as the username, this will allow admins
		//or employees to view all accounts in the account log
		Connection conn = ConnectionHandling.getConnection();
		String sql;
		PreparedStatement statement;
		if(username.equalsIgnoreCase("all")) {
			sql = "SELECT * FROM accountLog";
			statement = conn.prepareStatement(sql);
		} else {
			sql = "SELECT * FROM accountLog WHERE userID = ? OR adtnlUser = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, UserLogImp.getUserID(username));
			statement.setInt(2, UserLogImp.getUserID(username));
		}
		
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			int accountID = result.getInt(1); //may need to change this get
			int userID = result.getInt(2);
			String accountStatus = result.getString(4);
			String accountType = result.getString(5);
			double balance = result.getDouble(6);
			System.out.println();
			System.out.println("Account Number: " + accountID + "       " + "Account Type: " + accountType);
			System.out.println("Account Status: " + accountStatus);
			System.out.println("Current Balance: $" + balance);
			System.out.println("Users on Account: ");
			UserLogImp.viewUserRecord(UserLogImp.getUserName(userID));
			int adtnlUser = result.getInt(3); //may get error if null
			if(result.wasNull()) {
				adtnlUser = 0;
			} else {
				String userName2 = UserLogImp.getUserName(adtnlUser);
				UserLogImp.viewUserRecord(userName2);
			}
			System.out.println();
		}//end while
		
		
	}//end viewUserAccounts method

	public static void updateAccountInfo(int accountID, String accountStatus, String accountType, String adtnlUsername) throws SQLException {
		//Must enter "" if not adding an additional user
		Connection conn = ConnectionHandling.getConnection();
		String sql = "UPDATE accountLog SET adtnlUser = ?, accountStatus = ?, accountType = ? WHERE accountID = ?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		if(adtnlUsername.equals("")) {
			statement.setNull(1, Types.INTEGER);
		} else {
			statement.setInt(1, UserLogImp.getUserID(adtnlUsername));
		}
		statement.setString(2, accountStatus);
		statement.setString(3, accountType);
		statement.setInt(4, accountID);
		
		int rowsUpdated = statement.executeUpdate();
		if(rowsUpdated > 0) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("          This account's information has been updated.");
			System.out.println("***************************************************************");
			System.out.println();
			log.info("Data Base Update: A record has been updated in the accountLog table.");
			log.debug("Debug Message: A record was updated in the accountLog table.");
		}
	}//end updateAccountInfo method

	public static void updateAccountBalance(int accountID, double newBalance) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "UPDATE accountLog SET balance = ? WHERE accountID = ?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, newBalance);
		statement.setInt(2, accountID);
		
		int rowsUpdated = statement.executeUpdate();
		if(rowsUpdated > 0) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("           The new account balance is $" + newBalance);
			System.out.println("***************************************************************");
			System.out.println();
			
			log.info("Data Base Update: A record has been updated in the accountLog table.");
			log.debug("Debug Message: A record was updated in the accountLog table.");
		}
	}//end updateAccountBalance method
	
	public static void deleteAccountRecord(int accountID) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "DELETE FROM accountLog WHERE accountID = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, accountID);
		int rowsDeleted = statement.executeUpdate();
		if(rowsDeleted > 0) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("           	 This account was successfully deleted.");
			System.out.println("***************************************************************");
			System.out.println();
			log.info("Data Base Update: A record has been deleted from the AccountLog table.");
			log.debug("Debug Message: A record was deleted from the AccountLog table.");
		}
		
	}//end deleteAccountRecord method

	public static int getMaxAccountID() throws SQLException{
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT MAX(accountID) FROM accountLog";
		PreparedStatement statement = conn.prepareStatement(sql);		
		int accountID = 0;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			accountID = result.getInt(1); //may need to change this get
		}//end while
		
		return accountID;
	}//end getMaxAccountID method
	
	public static Account getAccountObject(int accntID) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT * FROM accountLog WHERE accountID = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, accntID);
		Account a;
		ResultSet result = statement.executeQuery();
		result.next();
			int accountID = result.getInt(1); //may need to change this get
			int userID = result.getInt(2);
			Customer c1 = UserLogImp.getCustomerObject(UserLogImp.getUserName(userID));
			String accountStatus = result.getString(4);
			String accountType = result.getString(5);
			float balance = result.getFloat(6);
			int adtnlUser = result.getInt(3); //may get error if null
			if(result.wasNull()) {
				a = new Account (accountID, c1, accountStatus, accountType, balance);
			} else {
				Customer c2 = UserLogImp.getCustomerObject(UserLogImp.getUserName(adtnlUser));
				a = new Account(accountID, c1, c2, accountStatus, accountType, balance);
			}
			log.debug("Debug Message: Creating an account object from an accountLog record.");
			return a;
			
	}//end getAccountObject method
	
}//end class
