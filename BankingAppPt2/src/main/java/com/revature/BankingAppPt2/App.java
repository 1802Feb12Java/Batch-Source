package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class App 
{
	final static Logger logger = Logger.getLogger(App.class);
	static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args )
    {
    	//establish connection to be passed around to functions
       Connection connection = DatabaseConnection.getDatabaseConnection();
       int menuOption = 0;
       while (menuOption != 3) {
    	   System.out.println("Please enter a numeric option to continue.");
    	   System.out.println("1. Register a new customer");
    	   System.out.println("2. Log in");
    	   System.out.println("3. Quit");
    	   try {
			menuOption = Integer.valueOf(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Number conversion error in log in menu");
			logger.error("Number conversion error in log in menu.");
		}
    	   switch (menuOption) {
		case 1:
			RegistrationService customerServices = new RegistrationService(connection);
			customerServices.registerCustomer();
			break;
		case 2:
			LoginService loginService = new LoginService(connection);
			User user = loginService.logIn();
			if (user == null) {
				break;
			}
			else {
				user.runMenu();
			}
			break;
		case 3:
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error closing connection");
				logger.error(e.toString());
			}
			break;
		default:
			System.out.println("Error. Not a valid option. Please try again");
			break;
		}
       }
    }
}
