package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Employee extends User{
	private EmployeeServices employeeServices;
	private EmployeeDAO employeeDAO;
	static Scanner scanner = new Scanner(System.in);
	final static Logger logger = Logger.getLogger(Employee.class);
	public Employee(int userId, String userType, Connection connection) {
		super(userId, userType, connection);
		this.employeeServices = new EmployeeServices(connection);
		this.employeeDAO = new EmployeeDAO(connection);
		this.firstName = employeeDAO.getFirstName(userId);
		this.lastName = employeeDAO.getLastName(userId);
		this.userName = employeeDAO.getUsername(userId);
		
	}
	@Override
	void runMenu() {
		int menuOption = 0;
		System.out.println("Hello, " + this.firstName);
		while (menuOption != 3) {
			System.out.println("Which employee function would you like to perform?");
			System.out.println("1. Approve or Deny Accounts");
			System.out.println("2. View customer information");
			System.out.println("3. Log Out");
			menuOption = Integer.valueOf(scanner.nextLine());
			switch (menuOption) {
			case 1:
				employeeServices.approveAccounts();
				break;
			case 2:
				employeeServices.viewCustomerInformation();
				break;
			default:
				System.out.println("Error. Please enter a valid option");
				break;
			}
		}
		
	}

}
