package com.revature.BankingAppPt2;

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
	
	LoginService(Connection connection) {
		this.connection = connection;
	}
	LoginService() {
		super();
		this.connection = DatabaseConnection.getDatabaseConnection();
	}
	
	public User logIn() {
		String userName = null, password = null, userType = null;
		//get user info
		System.out.println("Enter your username:");
		userName = scanner.nextLine();
		System.out.println("Enter your password");
		password = scanner.nextLine();
		
		//query userAccounts database
		PreparedStatement loginQuery = null;
		String loginString = "SELECT userPassword, userType FROM userAccounts WHERE userName = ?";
		try {
			String queryResultPassword = null;
			loginQuery = connection.prepareStatement(loginString);
			loginQuery.setString(1, userName);
			ResultSet resultSet = loginQuery.executeQuery();
			
			if (resultSet.next()) {
				queryResultPassword = resultSet.getString(1);
				userType = resultSet.getString(2);
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
				System.out.println("Wrong password!");
				logger.info("Failed log in occured with user info username:" + userName + ", password: " + password);
			}
			
		} catch (SQLException e) {
			logger.error(e.toString());
		} catch (Exception e) {
			System.out.println("Error. No Such User.");
			logger.info("A non existent user tried to log in using username: " + userName);
		}
		return createUser(userName, userType);
	}
	
	private User createUser(String userName, String userType) {
		User user = null;
		switch (userType) {
		case "C":
			user = new Customer();
			break;
		case "E":
			user = new Employee();
			break;
		case "A":
			user = new Administrator();
			break;
		default:
			System.out.println("Error creating user from database.");
			logger.error("Error in createUser.");
			break;
		}
		return user;
	}
}
