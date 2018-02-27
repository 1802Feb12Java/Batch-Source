package com.revature.BankingAppPt2;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class LoginService {
	final static Logger logger = Logger.getLogger(LoginService.class);
	static Scanner scanner = new Scanner(System.in);
	private Connection connection;
	Console console;
	LoginService(Connection connection) {
		this.connection = connection;
	}
	LoginService() {
		super();
		this.connection = DatabaseConnection.getDatabaseConnection();
	}
	
	public User logIn() {
		String userName = null, password = null, userType = null;
		int userId = -1;
		//get user info
		System.out.println("Enter your username:");
		userName = scanner.nextLine();
		System.out.println("Enter your password");
		password = scanner.nextLine();
		
		//query userAccounts database
		PreparedStatement loginQuery = null;
		String loginString = "SELECT userPassword, userType, userID FROM userAccounts WHERE userName = ?";
		try {
			String queryResultPassword = null;
			loginQuery = connection.prepareStatement(loginString);
			loginQuery.setString(1, userName);
			ResultSet resultSet = loginQuery.executeQuery();
			
			if (resultSet.next()) {
				queryResultPassword = resultSet.getString(1);
				userType = resultSet.getString(2);
				userId = resultSet.getInt(3);
			}
			else {
				//no user found
				throw new Exception();
			}
			
			//validate password
			if(password.equals(queryResultPassword)) {
				System.out.println("Login Successful.");
				logger.info("New log in from " + userName + " with account type: " + userType);
			}
			else {
				System.out.println("Error logging in. Try again.");
				logger.info("Failed log in occured with user info username:" + userName + ", password: " + password);
				String [] args = new String[0];
				App.main(args);
			}
			
		} catch (SQLException e) {
			logger.error(e.toString());
		} catch (Exception e) {
			System.out.println("Error logging in. Try again.");
			logger.info("A non existent user tried to log in using username: " + userName);
			String [] args = new String[0];
			App.main(args);
		}
		return createUser(userId, userType);
	}
	
	private User createUser(int userId, String userType) {
		User user = null;
		switch (userType) {
		case "C":
			user = new Customer(userId, userType, connection);
			break;
		case "E":
			user = new Employee(userId, userType, connection);
			break;
		case "A":
			user = new Administrator(userId, userType, connection);
			break;
		default:
			System.out.println("Error creating user from database.");
			logger.error("Error in createUser.");
			break;
		}
		return user;
	}
}
