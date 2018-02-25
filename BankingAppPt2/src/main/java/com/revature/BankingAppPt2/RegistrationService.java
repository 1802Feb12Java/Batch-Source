package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class RegistrationService {
	private Connection connection;
	final static Logger logger = Logger.getLogger(RegistrationService.class);
	static Scanner scanner = new Scanner(System.in);
	
	public RegistrationService(Connection connection) {
		this.connection = connection;
	}
	
	public RegistrationService() {
		super();
		this.connection = DatabaseConnection.getDatabaseConnection();
	}
	
	public void registerCustomer() {
		String userName = null, userPassword, firstName, lastName;
		boolean userNameIsAvailable = false;
		
		//create skeleton for prepared statements
		PreparedStatement selectUserName = null;
		PreparedStatement insertNewUser = null;
		String userNameSelectString = "SELECT userName FROM userAccounts WHERE userName = ?";
		String customerInsertString = "INSERT INTO userAccounts "
				+ "(userPassword, userName, firstName, lastName, userType) "
				+ "VALUES(?, ?, ?, ?, 'C')";
		
		//get username
		while (!userNameIsAvailable) {
			try {
				System.out.println("Please enter a user name");
				userName = scanner.nextLine();
				selectUserName = connection.prepareStatement(userNameSelectString);
				selectUserName.setString(1, userName);
				ResultSet userNameAvailable = selectUserName.executeQuery();
				if (userNameAvailable.next()) {
					System.out.println("Sorry. That username is already taken.");
				}
				else {
					userNameIsAvailable = true;
				}
			} catch (SQLException e) {
				
				logger.error("Error in checking username - this is probably a SQL or connection problem");
			}
		}
		
		//get password
		System.out.println("Please enter a password for your account.");
		userPassword = scanner.nextLine();
		
		//get user first and last name
		System.out.println("Please enter your first name.");
		firstName = scanner.nextLine();
		System.out.println("Please enter your last name");
		lastName = scanner.nextLine();
		
		//prepare to insert new user
		try {
			insertNewUser = connection.prepareStatement(customerInsertString);
			insertNewUser.setString(1, userPassword);
			insertNewUser.setString(2, userName);
			insertNewUser.setString(3, firstName);
			insertNewUser.setString(4, lastName);
			int successfulInsert = insertNewUser.executeUpdate();
			if (successfulInsert > 0) {
				System.out.println("Account created successfully.");
				System.out.println("You may now log in to the system.");
				System.out.println("Returning to main menu.");
				logger.info("New Account Created From: " + firstName + " " + lastName + "(" + userName + ")");
			}
			else {
				System.out.println("Error creating account. Check log for details.");
				throw new Exception();
			}
		} catch (SQLException e) {
			System.out.println("Error inserting customer.");
			logger.error("Error inserting customer into database.");
			logger.error(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error executing prepared insert statment");
			logger.error(e.toString());
		}
	}

}
