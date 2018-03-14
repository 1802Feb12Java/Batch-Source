package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.Manager;

public class UserDAOImp {
	
	private static Logger log = Logger.getLogger(UserDAOImp.class.getName());
	static ConnectionFactory cf = ConnectionFactory.getInstance();
	
	public static void createUserRecord(String username, String password, String firstname, String lastname, String email, int userRoleID) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO ERS_USERS (U_ID, U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ID)"
			+ " VALUES (GET_NEW_U_ID(), ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, password);
		statement.setString(3, firstname);
		statement.setString(4, lastname);
		statement.setString(5, email);
		statement.setInt(6, userRoleID);
		
		int rowsInserted = statement.executeUpdate();
		if(rowsInserted > 0) {
			System.out.println("Record Successfully Added.");
			log.info("New User Record Created Successfully.");
		} else {
			System.out.println("Error: Record Not Added.");
			log.debug("ERROR: There was a problem with creating a new user record.");
		}
		
		
	}//end createUserRecord method
	
	public static void updateUserPassword(int u_id, String newPass) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "UPDATE ERS_USERS SET U_PASSWORD = ? WHERE U_ID = ?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, newPass);
		statement.setInt(2, u_id);
		
		int rowsUpdated = statement.executeUpdate();
		if(rowsUpdated > 0) {
			System.out.println("Password for user #" + u_id + " has been successfully updated.");
			log.info("Password For User #" + u_id + " Updated Successfully.");
		} else {
			System.out.println("Error: Password Not Updated.");
			log.debug("ERROR: There was a problem updating a password.");
		}
		
	}//end updateUserPassword method
	
	public static void updateUserInfo(int u_id, String new_username, String firstname, String lastname, String newEmail) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "UPDATE ERS_USERS SET U_USERNAME = ?, U_FIRSTNAME = ?, U_LASTNAME = ?, U_EMAIL = ? WHERE U_ID = ?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, new_username);
		statement.setString(2, firstname);
		statement.setString(3, lastname);
		statement.setString(4, newEmail);
		statement.setInt(5, u_id);
		
		int rowsUpdated = statement.executeUpdate();
		if(rowsUpdated > 0) {
			System.out.println("User #" + u_id + "'s information has been successfully updated.");
			log.info("Information For User #" + u_id + " Updated Successfully.");
		} else {
			System.out.println("Error: Info Not Updated.");
			log.debug("ERROR: There was a problem updating a user's information.");
		}
	}//end updateUserInfo method
	
	
	public static int getUserID(String username) throws SQLException {
		//or by email? or by name? ??
		//return ID
		Connection conn = cf.getConnection();
		String sql = "SELECT U_ID FROM ERS_USERS WHERE U_USERNAME = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		
		int userID = 0;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			userID = result.getInt(1);
		}//end while
		
		if(userID == 0) {
			log.debug("ERROR: User ID Was Not Accessed Successfully.");
		}
		
		return userID;
	}//end getUserID
	
	public static int getUserRoleID(String username) throws SQLException {
		//return ID
		Connection conn = cf.getConnection();
		String sql = "SELECT UR_ID FROM ERS_USERS WHERE U_USERNAME = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		
		int userRoleID = 3;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			userRoleID = result.getInt(1);
		}//end while
		
		if(userRoleID == 3) {
			log.debug("ERROR: User Role ID Was Not Accessed Successfully.");
		}
		
		return userRoleID;
	}//end getUserRoleID
	
	public static String viewUserRecord(String username) throws SQLException {
		//view a user record, by user name
		Connection conn = cf.getConnection();
		
		//String sql = "SELECT * FROM ERS_USERS WHERE U_USERNAME = ?";
		String sql = "SELECT * FROM ERS_USERS"
				+ " LEFT JOIN ERS_USER_ROLES ON ERS_USERS.UR_ID = ERS_USER_ROLES.UR_ID"
				+ " WHERE U_USERNAME = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		
		String user = null;
		
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			int userID = result.getInt(1); //may need to change this get
			String userName = result.getString(2);
			String firstName = result.getString(4);
			String lastName = result.getString(5);
			String email = result.getString(6);
			int userRole = result.getInt(7); 
			String name = firstName + " " + lastName;
			String role;
			switch(userRole) {
			case 0:
				role = "Employee";
				break;
			case 1:
				role = "Manager";
				break;
			default:
				role = "Not Specified";
				break;
			}
			
			user = userID + ":" + userName + ":" + name + ":" + email + ":" + role;
			
		}//end while
		
		return user;
	}//end viewUserRecord method

	public static ArrayList<String> viewAllUserRecords(int filter) throws SQLException {
		//view a user record, by user name
		Connection conn = cf.getConnection();
		String sql = "";
		if(filter == 1) {
			sql = "SELECT * FROM ERS_USERS WHERE UR_ID = 0";
		} else if (filter == 2) {
			sql = "SELECT * FROM ERS_USERS WHERE UR_ID = 1";
		} else if (filter == 3) {
			sql = "SELECT * FROM ERS_USERS";
		}
		
		PreparedStatement statement = conn.prepareStatement(sql);
		
		
		ResultSet result = statement.executeQuery();
		ArrayList<String> userList = new ArrayList<String>();
		while(result.next()) {
			int userID = result.getInt(1); //may need to change this get
			String userName = result.getString(2);
			String firstName = result.getString(4);
			String lastName = result.getString(5);
			String email = result.getString(6);
			int userRole = result.getInt(7); 
			String name = firstName + " " + lastName;
			String role;
			switch(userRole) {
			case 0:
				role = "Employee";
				break;
			case 1:
				role = "Manager";
				break;
			default:
				role = "Not Specified";
				break;
			}
			
			userList.add(userID + ":" + userName + ":" + name + ":" + email + ":" + role);
			
		}//end while
		
		return userList;
	}//end viewAllUserRecords method
	
	public static Employee getEmployeeObject(String username) throws SQLException{
				Connection conn = cf.getConnection();
				
				String sql = "SELECT * FROM ERS_USERS"
						+ " WHERE U_USERNAME = ?";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, username);
				
				Employee e = null;
				ResultSet result = statement.executeQuery();
				while(result.next()) {
					int userID = result.getInt(1); //may need to change this get
					String userName = result.getString(2);
					String pass = result.getString(3);
					String firstName = result.getString(4);
					String lastName = result.getString(5);
					String email = result.getString(6);
					int ur_id = result.getInt(7);
					e = new Employee(userID, userName, pass, firstName, lastName, email, ur_id);
				}//end while
				return e;
	}//end getEmployeeObject method
	
	public static Manager getManagerObject(String username) throws SQLException{
		Connection conn = cf.getConnection();
		
		String sql = "SELECT * FROM ERS_USERS"
				+ " WHERE U_USERNAME = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		
		Manager m = null;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			int userID = result.getInt(1); //may need to change this get
			String userName = result.getString(2);
			String pass = result.getString(3);
			String firstName = result.getString(4);
			String lastName = result.getString(5);
			String email = result.getString(6);
			int ur_id = result.getInt(7);
			m = new Manager(userID, userName, pass, firstName, lastName, email, ur_id);
		}//end while
		return m;
}//end getManagerObject method
	
	public static ArrayList<String> getUsernames() throws SQLException{
		
		/* Get connection */
		Connection conn = cf.getConnection();
		
		/* Make SQL statement */
		String sql = "SELECT U_USERNAME FROM ERS_USERS";
		PreparedStatement statement = conn.prepareStatement(sql);
		
		/* Execute statement and retrieve result set */		
		ArrayList<String> usernamesList = new ArrayList<String>();
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			usernamesList.add(result.getString(1));
		}//end while
		
		if(usernamesList.size() == 0) {
			log.debug("ERROR: Usernames Were Not Accessed Successfully.");
		}
		
		return usernamesList;
		
	}//end getUsernames method
	
}//end class
