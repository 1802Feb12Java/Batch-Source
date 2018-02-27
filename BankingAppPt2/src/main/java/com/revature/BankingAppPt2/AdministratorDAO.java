package com.revature.BankingAppPt2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class AdministratorDAO extends UserDAO {
	final static Logger logger = Logger.getLogger(AdministratorDAO.class);
	
	protected AdministratorDAO(Connection connection) {
		super(connection);
	}
	
	public List<String> getUsers() {
		List<String> users = new ArrayList<String>();
		String getUsersString = "SELECT * FROM userAccounts";
		try {
			PreparedStatement getUsersQuery = connection.prepareStatement(getUsersString);
			ResultSet resultSet = getUsersQuery.executeQuery();
			while(resultSet.next()) {
				int userId = resultSet.getInt(1);
				String userName = resultSet.getString(3);
				String firstName = resultSet.getString(4);
				String lastName = resultSet.getString(5);
				String userType = resultSet.getString(6);
				
				switch (userType) {
				case "C":
					userType = "Customer";
					break;
				case "E":
					userType = "Employee";
					break;
				case "A":
					userType = "Administrator";
					break;
				default:
					break;
				}
				
				String userString = "User ID: " + userId + "\nUsername: " + userName +
						"\nFirst Name: " + firstName + "\nLast Name: " + lastName + 
						"\nUser Type: " + userType + "\n";
				users.add(userString);
		}
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return users;
	}
	
	public void insertUser(String password, String userName, String firstName, String lastName, String userType) {
		String userInsertString = "INSERT INTO userAccounts (userPassword, userName, firstName, lastName, userType) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement userInsertQuery = connection.prepareStatement(userInsertString);
			userInsertQuery.setString(1, password);
			userInsertQuery.setString(2, userName);
			userInsertQuery.setString(3, firstName);
			userInsertQuery.setString(4, lastName);
			userInsertQuery.setString(5, userType);
			userInsertQuery.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error in adminDAO");
			logger.error(e.toString());
		}
	}
	
	public boolean userNameIsAvailable(String userName) {
		boolean isAvailable = true;
		PreparedStatement selectUserName = null;
		String userNameSelectString = "SELECT userName FROM userAccounts WHERE userName = ?";
		try {
				selectUserName = connection.prepareStatement(userNameSelectString);
				selectUserName.setString(1, userName);
				ResultSet userNameAvailable = selectUserName.executeQuery();
				if (userNameAvailable.next()) {
					isAvailable = false;
				}
		} catch (SQLException e) {
			logger.error("Error in checking username - this is probably a SQL or connection problem");
		}
		return isAvailable;
	}
	
	public String getUsertype(int userId) {
		String getUserTypeString = "SELECT accountType FROM userAccounts WHERE accountId = ?";
		String userType = "";
		try {
			PreparedStatement getUserTypeQuery = connection.prepareStatement(getUserTypeString);
			getUserTypeQuery.setInt(1, userId);
			ResultSet resultSet = getUserTypeQuery.executeQuery();
			if (resultSet.next()) {
				userType = resultSet.getString(1);
			}
			
		} catch (SQLException e) {
			
		}
		
		return userType;
	}
	
	public void updateUser(String userName, String firstName, String lastName, String userType, int userId) {
		String updateUserString = "UPDATE userAccounts SET userName = ?, firstName = ?, lastName = ?, userType = ? WHERE userId = ?";
		try {
			PreparedStatement updateUserQuery = connection.prepareStatement(updateUserString);
			updateUserQuery.setString(1, userName);
			updateUserQuery.setString(2, firstName);
			updateUserQuery.setString(3, lastName);
			updateUserQuery.setString(4, userType);
			updateUserQuery.setInt(5, userId);
			updateUserQuery.executeQuery();
			logger.info("USER with ID: " + userId + " was updated.");
		} catch (SQLException e) {
			logger.error(e.toString());
		}
	}

	public void deleteUser(int userId) {
		try {
			CallableStatement callDelete = connection.prepareCall("{call delete_user(?)}");
			callDelete.setInt(1, userId);
			callDelete.execute();
			logger.info("User with ID: " + userId + " was deleted.");
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
	}
}