package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class AdministratorServices {
	final static Logger logger = Logger.getLogger(AdministratorServices.class);
	static Scanner scanner = new Scanner(System.in);
	AdministratorDAO administratorDAO;
	
	AdministratorServices(Connection connection) {
		administratorDAO = new AdministratorDAO(connection);
	}
	
	public void viewAllUsers() {
		List<String> users = administratorDAO.getUsers();
		for (String user : users) {
			System.out.println(user);
		}
	}
	
	public void createUser() {
		System.out.println("User Creation");
		String userName = "";
		boolean isAvailable = false;
		//loop to check available user name
		do {
			System.out.println("Enter a userName for the user");
			userName = scanner.nextLine();
			isAvailable = administratorDAO.userNameIsAvailable(userName);
			if (!isAvailable) {
				System.out.println("Sorry. That username is taken. Please enter another one.");
			}
		} while (!isAvailable);
		System.out.println("Enter a password for this account");
		String password = scanner.nextLine();
		System.out.println("Enter a first Name");
		String firstName = scanner.nextLine();
		System.out.println("Enter a last name");
		String lastName = scanner.nextLine();
		int menuOption = 0;
		while (menuOption < 1 || menuOption > 3) {
			System.out.println("Please select a user type");
			System.out.println("1. Customer");
			System.out.println("2. Employee");
			System.out.println("3. Administrator");
			menuOption = Integer.valueOf(scanner.nextLine());
		}
		switch (menuOption) {
		case 1:
			administratorDAO.insertUser(password, userName, firstName, lastName, "C");
			logger.info("Customer created by admin: " + userName + " First Name: " + firstName + " Last Name: " + lastName);
			break;
		case 2:
			administratorDAO.insertUser(password, userName, firstName, lastName, "E");
			logger.info("Employee created by admin: " + userName + " First Name: " + firstName + " Last Name: " + lastName);
			break;
		case 3:
			administratorDAO.insertUser(password, userName, firstName, lastName, "A");
			logger.info("Administrator created by admin: " + userName + " First Name: " + firstName + " Last Name: " + lastName);
			break;
		default:
			break;
		}
		
	}
	
	public void updateUser() {
		System.out.println("To update a user, fill in the requested fields.");
		System.out.println("To skip a field, hit enter without typing anything.");
		boolean userNameIsValid = false;
		int userId = 0;
		
		String userName = "";
		while (!userNameIsValid) { 
			System.out.println("Please enter a userName to update a user.");
			userName = scanner.nextLine();
			userId = administratorDAO.getUserId(userName);
			if (userId == 0) {
				System.out.println("Error. User name not found. Try again.");
			}
			else {
				userNameIsValid = true;
			}
		}
		//update user name
		userNameIsValid = false;
		while (!userNameIsValid) {
			System.out.println("Enter a new user name");
			String newUserName = scanner.nextLine();
			if (newUserName.isEmpty()) {
				userNameIsValid = true;
			}
			else {
				if (administratorDAO.userNameIsAvailable(newUserName)) {
					userName = newUserName;
					userNameIsValid = true;
				}
				else {
					System.out.println("Sorry. That username is taken. Please enter another one.");
				}
			}
		}
		
		//Update first Name
		System.out.println("This user's first name is: " + administratorDAO.getFirstName(userId) + "\nEnter a new first name.");
		String firstName = scanner.nextLine();
		if (firstName.isEmpty()) {
			firstName = administratorDAO.getFirstName(userId);
		}
		
		//update last name
		System.out.println("This user's last name is: " + administratorDAO.getLastName(userId) + "\nEnter a new last name.");
		String lastName = scanner.nextLine();
		if (lastName.isEmpty()) {
			lastName = administratorDAO.getLastName(userId);
		}
		
		//update customer type
		String userType = administratorDAO.getUsertype(userId);
		String newUserType = "";
		switch (userType) {
		case "A":
			newUserType = " an administrator";
			break;
		case "C":
			newUserType = "a customer";
			break;
		case "E":
			newUserType = "an employee";
			break;
		default:
			break;
		}
		System.out.println("This user is currently " + newUserType);
		int menuOption = 0;
		while (menuOption < 1 || menuOption > 4) {
			System.out.println("Select a new user type");
			System.out.println("1. Administrator");
			System.out.println("2. Customer");
			System.out.println("3. Employee");
			System.out.println("4. No Change");
			menuOption = Integer.valueOf(scanner.nextLine());
		}
		switch (menuOption) {
		case 1:
			newUserType = "A";
			break;
		case 2:
			newUserType = "C";
			break;
		case 3:
			newUserType = "E";
			break;
		case 4:
			newUserType = userType;
			break;
		default:
			break;
		}
		administratorDAO.updateUser(userName, firstName, lastName, newUserType, userId);
	}
	
	public void deleteUser() {
		int userId = 0;
		while(userId < 1) {
			System.out.println("Enter a username to delete a user.");
			String userName = scanner.nextLine();
			userId = administratorDAO.getUserId(userName);
			if (userId == 0) {
				System.out.println("Sorry. No such user. Try again.");
			}
		}
		administratorDAO.deleteUser(userId);
		
	}
	

}
