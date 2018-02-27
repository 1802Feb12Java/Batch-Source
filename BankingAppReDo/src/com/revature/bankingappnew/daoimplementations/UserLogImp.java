package com.revature.bankingappnew.daoimplementations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.bankingappnew.Admin;
import com.revature.bankingappnew.ConnectionHandling;
import com.revature.bankingappnew.Customer;
import com.revature.bankingappnew.Employee;
import com.revature.bankingappnew.User;

/*
 * make sure deleteUserRecord works because keys may cause issues
 * 
 */

public class UserLogImp {
	
	private static Logger log = Logger.getLogger(UserLogImp.class.getName());
	
	public static void createUserRecord(String username, String password, String userType, String firstName, String lastName) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "{CALL insertNewUser (?, ?, ?, ?, ?)}";
		
		CallableStatement statement = conn.prepareCall(sql);
		statement.setString(1, username);
		statement.setString(2, password);
		statement.setString(3, userType);
		statement.setString(4, firstName);
		statement.setString(5, lastName);
		
		boolean hasResultSet = statement.execute();
		if (!hasResultSet) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("The new " + userType + " has been successfully added to the user log!");
			System.out.println("***************************************************************");
			System.out.println();
			log.info("Data Base Update: A new user has been successfully added to the userLog table.");
		}
		
	}//end createUserRecord method
	
	public static void viewUserRecord(String username) throws SQLException {
		//this method allows for 'all' to be given as the username, this will allow admins
		//or employees to view all users in the user log
		Connection conn = ConnectionHandling.getConnection();
		String sql;
		PreparedStatement statement;
		if(username.equalsIgnoreCase("all")) {
			sql = "SELECT * FROM userLog";
			statement = conn.prepareStatement(sql);
		} else {
			sql = "SELECT * FROM userLog WHERE userName = ?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, username);
		}
		
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			int userID = result.getInt(1); //may need to change this get
			String userName = result.getString(2);
			String userType = result.getString(4);
			String firstName = result.getString(5);
			String lastName = result.getString(6);
			System.out.println();
			System.out.println("User ID: " + userID + "       " + "User Type: " + userType);
			System.out.println("Username: " + userName);
			System.out.println("Full Name: " + firstName + " " + lastName);
			System.out.println();
		}//end while
	}//end viewUserRecord method
	
	public static void updateUserRecord(String username, String password, String userType, String firstName, String lastName) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "UPDATE userLog SET password = ?, userType = ?, firstName = ?, lastName = ? WHERE userName = ?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(5, username);
		statement.setString(1, password);
		statement.setString(2, userType);
		statement.setString(3, firstName);
		statement.setString(4, lastName);
		
		int rowsUpdated = statement.executeUpdate();
		if(rowsUpdated > 0) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("           This user's information has been updated.");
			System.out.println("***************************************************************");
			System.out.println();
			log.info("Data Base Update: A record in the userLog table has been updated.");
		}
	}//end updateUserRecord method
	
	public static void deleteUserRecord(String username) throws SQLException {
		//need to make sure this will work
		Connection conn = ConnectionHandling.getConnection();
		String sql = "DELETE FROM userLog WHERE userName = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1,  username);
		int rowsDeleted = statement.executeUpdate();
		if(rowsDeleted > 0) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("           	   This user was successfully deleted.");
			System.out.println("***************************************************************");
			System.out.println();
			log.info("Data Base Update: A record has been deleted from the userLog table.");
			log.debug("Debug Message: A record was deleted from the userLog table.");
		}
		
	}//end deleteUserRecord method
	
	public static int getUserID(String username) throws SQLException{
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT * FROM userLog WHERE userName = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		
		int userID = 0;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			userID = result.getInt(1); //may need to change this get
		}//end while
		
		return userID;
	}//end getUserID method
	
	public static String getUserName(int userID) throws SQLException{
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT * FROM userLog WHERE userID = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, userID);
		
		String userName = "";
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			userName = result.getString("userName");
		}//end while
		
		return userName;
	}//end getUserName method

	public static User getUserObject(String username) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT * FROM userLog WHERE userName = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		User u = null;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			int userID = result.getInt(1); //may need to change this get
			String userName = result.getString(2);
			String pass = result.getString(3);
			String type = result.getString(4);
			String firstName = result.getString(5);
			String lastName = result.getString(6);
			
		
			u = new User(userID, userName, pass, firstName, lastName, type);
		}
			log.debug("Debug Message: Creating a user object from a userLog record.");
			return u;
		//}
	}//end getUserObject method
	
	public static Admin getAdminObject(String username) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT * FROM userLog WHERE userName = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet result = statement.executeQuery();
		result.next();
		int userID = result.getInt(1); //may need to change this get
		String userName = result.getString(2);
		String pass = result.getString(3);
		String firstName = result.getString(5);
		String lastName = result.getString(6);
		Admin a = new Admin(userID, userName, pass, firstName, lastName);
		log.debug("Debug Message: Creating an admin object from a userLog record.");
		return a;
	}//end getAdminObject method
	
	public static Employee getEmployeeObject(String username) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT * FROM userLog WHERE userName = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet result = statement.executeQuery();
		result.next();
		int userID = result.getInt(1); //may need to change this get
		String userName = result.getString(2);
		String pass = result.getString(3);
		String firstName = result.getString(5);
		String lastName = result.getString(6);
		Employee e = new Employee(userID, userName, pass, firstName, lastName);
		log.debug("Debug Message: Creating an employee object from a userLog record.");
		return e;
	}//end getEmployeeObject method
	
	public static Customer getCustomerObject(String username) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT * FROM userLog WHERE userName = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet result = statement.executeQuery();
		result.next();
		int userID = result.getInt(1); //may need to change this get
		String userName = result.getString(2);
		String pass = result.getString(3);
		String firstName = result.getString(5);
		String lastName = result.getString(6);
		Customer c = new Customer(userID, userName, pass, firstName, lastName);
		log.debug("Debug Message: Creating a customer object from a userLog record.");
		return c;
	}//end getCustomerObject method
	
	public static boolean usernameIsUnique(String username) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT userName FROM userLog";
		PreparedStatement statement = conn.prepareStatement(sql);
		ArrayList<String> usernameList = new ArrayList<String>();
		ResultSet result = statement.executeQuery(); //may need to add back in sql
		while(result.next()) {
			usernameList.add(result.getString(1));
		}//end while
		boolean b = true;
		for(String s : usernameList) {
			if(s.equalsIgnoreCase(username)) {
				b = false;
			}
		}
		return b;
	}//end usernameIsUnique method
	
	public static void viewDetailedUserRecord(String username) throws SQLException {
		//NEED TO TEST BEFORE I CAN USE
		Connection conn = ConnectionHandling.getConnection();
		String sql1 = "SELECT * FROM accountLog WHERE accountLog.userID = (SELECT userID FROM userLog WHERE userName = ?) OR accountLog.adtnlUser = (SELECT userID FROM userLog WHERE userName = ?)";
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			statement1.setString(1, username);
			statement1.setString(2, username);
			System.out.println("Accounts: ");
		ResultSet result = statement1.executeQuery();
		while(result.next()) {
			int accountID = result.getInt(1); //may need to change this get
			String accountType = result.getString(5);
			double balance = result.getDouble(6);
			System.out.println("Account #: " + accountID + "	" + "Type: " + accountType + "	" + "Current Balance: $" + balance);
		}//end while
		System.out.println();
		String sql2 = "SELECT COUNT(applicationID) FROM applicationLog WHERE applicationLog.status = 'Pending' AND (applicationLog.userID = (SELECT userID FROM userLog WHERE userName = ?) OR applicationLog.adtnlUser = (SELECT userID FROM userLog WHERE userName = ?))";
		PreparedStatement statement2 = conn.prepareStatement(sql2);
		statement2.setString(1, username);
		statement2.setString(2, username);
		
		ResultSet result2 = statement2.executeQuery();
		while(result2.next()) {
			int count = result2.getInt(1);
			System.out.println("Number of Pending Applications: " + count);
		}//end while
		System.out.println();
	}//end viewUserRecord method
	
	
}//end class

