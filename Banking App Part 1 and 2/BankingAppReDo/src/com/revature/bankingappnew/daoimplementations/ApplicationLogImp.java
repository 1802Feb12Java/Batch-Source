package com.revature.bankingappnew.daoimplementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import com.revature.bankingappnew.Application;
import com.revature.bankingappnew.ConnectionHandling;
import com.revature.bankingappnew.Customer;

public class ApplicationLogImp {
	
	private static Logger log = Logger.getLogger(ApplicationLogImp.class.getName());
	
	public static void createApplicationRecord(String applicationType, String ... username) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "INSERT INTO applicationLog (applicationID, userID, applicationType, adtnlUser, status)"
				+ " VALUES (getNextAppID(), ?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(2, applicationType);
		statement.setString(4, "Pending");
		if(username.length > 1) {
			String usernameOne = username[0];
			String usernameTwo = username[1];
			statement.setInt(1, UserLogImp.getUserID(usernameOne));
			statement.setInt(3, UserLogImp.getUserID(usernameTwo));
		} else {
			String usernameOnly = username[0];
			statement.setInt(1, UserLogImp.getUserID(usernameOnly));
			statement.setNull(3, Types.INTEGER);
		}
		
		
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("The new application has been successfully added to the application log!");
			System.out.println("***************************************************************");
			System.out.println();
			log.info("Data Base Update: A record has been added to the applicationLog table.");
			log.debug("Debug Message: A record was added to the applicationLog table.");
		}
		
	}//end createApplicationRecord method

	public static void viewUserApplications(String username) throws SQLException {
		//this method allows for 'all' to be given as the username, this will allow admins
		//or employees to view all applications in the application log
		Connection conn = ConnectionHandling.getConnection();
		String sql;
		PreparedStatement statement;
		if(username.equalsIgnoreCase("all")) {
			sql = "SELECT * FROM applicationLog";
			statement = conn.prepareStatement(sql);
		} else {
			sql = "SELECT * FROM applicationLog WHERE userID = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, UserLogImp.getUserID(username));
		}
		int count = 1;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			int applicationID = result.getInt("applicationID"); //may need to change this get
			int userID = result.getInt("userID");
			String applicationType = result.getString("applicationType");
			String status = result.getString("status");
			System.out.println();
			System.out.println("		Application " + count);
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Application Number: " + applicationID + "       " + "Application Type: " + applicationType);
			System.out.println("Application Status: " + status);
			System.out.println("User(s) on Application: ");
			System.out.println("---------------------------------");
			UserLogImp.viewUserRecord(UserLogImp.getUserName(userID));
			int adtnlUser = result.getInt("adtnlUser"); //may get error if null
			if(result.wasNull()) {
				adtnlUser = 0;
			} else {
				String userName2 = UserLogImp.getUserName(adtnlUser);
				UserLogImp.viewUserRecord(userName2);
			}
			System.out.println();
			count++;
		}//end while
	}//end viewUserApplications method

	public static void viewPendingApplications(String username) throws SQLException {
		//this method allows for 'all' to be given as the username, this will allow admins
		//or employees to view all pending applications in the application log
		Connection conn = ConnectionHandling.getConnection();
		String sql;
		PreparedStatement statement;
		if(username.equalsIgnoreCase("all")) {
			sql = "SELECT * FROM applicationLog WHERE status = ?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, "Pending");
		} else {
			sql = "SELECT * FROM applicationLog WHERE userID = ? AND status = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, UserLogImp.getUserID(username));
			statement.setString(2, "Pending");
		}
		int count = 1;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			int applicationID = result.getInt("applicationID"); //may need to change this get
			int userID = result.getInt("userID");
			String applicationType = result.getString("applicationType");
			String status = result.getString("status");
			System.out.println();
			System.out.println("		Application " + count);
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Application Number: " + applicationID + "       " + "Application Type: " + applicationType);
			System.out.println("Application Status: " + status);
			System.out.println("User(s) on Application: ");
			System.out.println("---------------------------------");
			UserLogImp.viewUserRecord(UserLogImp.getUserName(userID));
			int adtnlUser = result.getInt("adtnlUser"); //may get error if null
			if(result.wasNull()) {
				adtnlUser = 0;
			} else {
				String userName2 = UserLogImp.getUserName(adtnlUser);
				UserLogImp.viewUserRecord(userName2);
			}
			System.out.println();
			count++;
		}//end while
	}//end viewPendingApplications method
	
	public static void updateApplicationStatus(int applicationID, String newStatus) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "UPDATE applicationLog SET status = ? WHERE applicationID = ?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, newStatus);
		statement.setInt(2, applicationID);
		
		int rowsUpdated = statement.executeUpdate();
		if(rowsUpdated > 0) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("           The new application status is " + newStatus);
			System.out.println("***************************************************************");
			System.out.println();
			log.info("Data Base Update: A record has been updated in the applicationLog table.");
			log.debug("Debug Message: A record was updated in the applicationLog table.");
		}
	}//end updateApplicationStatus method

	public static void deleteApplicationRecord(int applicationID) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "DELETE FROM applicationLog WHERE applicationID = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, applicationID);
		int rowsDeleted = statement.executeUpdate();
		if(rowsDeleted > 0) {
			System.out.println();
			System.out.println("**************************DB Message:**************************");
			System.out.println("           This application was successfully deleted.");
			System.out.println("***************************************************************");
			System.out.println();
			log.info("Data Base Update: A record has been deleted from the applicationLog table.");
			log.debug("Debug Message: A record was deleted from the applicationLog table.");
		}
		
	}//end deleteApplicationRecord method

	public static int getMaxApplicationID() throws SQLException{
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT MAX(applicationID) FROM applicationLog";
		PreparedStatement statement = conn.prepareStatement(sql);		
		int applicationID = 0;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			applicationID = result.getInt(1); //may need to change this get
		}//end while
		
		return applicationID;
	}//end getMaxApplicationID method
	
	public static Application getApplicationObject(int appID) throws SQLException {
		Connection conn = ConnectionHandling.getConnection();
		String sql = "SELECT * FROM applicationLog WHERE applicationID = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, appID);
		Application a;
		ResultSet result = statement.executeQuery();
		result.next();
		int applicationID = result.getInt("applicationID"); //may need to change this get
		int userID = result.getInt("userID");
		String applicationType = result.getString("applicationType");
		String status = result.getString("status");
		Customer c1 = UserLogImp.getCustomerObject(UserLogImp.getUserName(userID));
		int adtnlUser = result.getInt("adtnlUser"); //may get error if null
		if(result.wasNull()) {
			a = new Application(applicationID, c1, applicationType, status);
		} else {
			Customer c2 = UserLogImp.getCustomerObject(UserLogImp.getUserName(adtnlUser));
			a = new Application(applicationID, c1, c2, applicationType, status);
		}
		log.debug("Debug Message: Creating an application object from an applicationLog record.");
		return a;
	
	}//end getApplicationObject method
	
}//end class
