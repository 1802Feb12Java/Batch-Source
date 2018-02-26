package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Administrator extends User {
	final static Logger logger = Logger.getLogger(Administrator.class);
	static Scanner scanner = new Scanner(System.in);
	AdministratorServices administratorServices;
	protected Administrator(int userId, String userType, Connection connection) {
		super(userId, userType, connection);
		administratorServices = new AdministratorServices(connection);
	}

	@Override
	void runMenu() {
		int menuOption = 0;
		while (menuOption != 5) {
			System.out.println("Please enter a number to perform an adminstrative action");
			System.out.println("1. View all registered users");
			System.out.println("2. Create a new user");
			System.out.println("3. Update a user's information");
			System.out.println("4. Delete a user");
			System.out.println("5. Log Out");
			menuOption = Integer.valueOf(scanner.nextLine());
			switch (menuOption) {
			case 1:
				administratorServices.viewAllUsers();
				break;
			case 2:
				administratorServices.createUser();
				break;
			case 3:
				administratorServices.updateUser();
				break;
			case 4:
				administratorServices.deleteUser();
				break;
			default:
				System.out.println("Please enter a valid option");
				break;
			}
		}
	}
}
