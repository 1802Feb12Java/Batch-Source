package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public abstract class UserDAO {
	final static Logger logger = Logger.getLogger(Employee.class);
	protected Connection connection;
	protected UserDAO(Connection connection) {
		this.connection = connection;
	}
	public String getUsername(int userId) {
		String userName = "";
		String userNameQueryString = "SELECT userName FROM userAccounts WHERE userId = ?";
		PreparedStatement userNameQuery;
		try {
			userNameQuery = connection.prepareStatement(userNameQueryString);
			userNameQuery.setInt(1, userId);
			ResultSet resultSet = userNameQuery.executeQuery();
			if (resultSet.next()) {
				userName = resultSet.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Error getting username from UserDAO");
			logger.error("Error getting username from UserDAO");
			logger.error(e.toString());
			
		}	
		
		return userName;
	}
	
	public void setUserName(int userId) {
		
	}
	
	public String getFirstName(int userId) {
		String firstName = "";
		String firstNameQueryString = "SELECT firstName FROM userAccounts WHERE userId = ?";
		PreparedStatement firstNameQuery;
		try {
			firstNameQuery = connection.prepareStatement(firstNameQueryString);
			firstNameQuery.setInt(1, userId);
			ResultSet resultSet = firstNameQuery.executeQuery();
			if (resultSet.next()) {
				firstName = resultSet.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Error getting first name from UserDAO");
			logger.error("Error getting first name from UserDAO");
			logger.error(e.toString());
			
		}	
		
		return firstName;
	}
	
	public void setFirstName(int userId) {
		
	}
	
	public String getLastName(int userId) {
		String lastName = "";
		String lastNameQueryString = "SELECT lastName FROM userAccounts WHERE userId = ?";
		PreparedStatement lastNameQuery;
		try {
			lastNameQuery = connection.prepareStatement(lastNameQueryString);
			lastNameQuery.setInt(1, userId);
			ResultSet resultSet = lastNameQuery.executeQuery();
			if (resultSet.next()) {
				lastName = resultSet.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Error getting last name from UserDAO");
			logger.error("Error getting last name from UserDAO");
			logger.error(e.toString());
			
		}	
		
		return lastName;
	}
	
	public void setLastName(int userId) {
		
	}
	
	public void setPassword(int userId) {
		
	}
	
	public int getUserId(String userName) {
		int userId = 0;
		String getUserIdString = "SELECT userId FROM userAccounts WHERE userName = ?";
		PreparedStatement getUserIdQuery;
		try {
			getUserIdQuery = connection.prepareStatement(getUserIdString);
			getUserIdQuery.setString(1, userName);
			ResultSet resultSet = getUserIdQuery.executeQuery();
			if (resultSet.next()) {
				userId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		
		return userId;
	}
}
