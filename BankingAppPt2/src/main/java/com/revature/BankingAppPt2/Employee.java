package com.revature.BankingAppPt2;

import java.sql.Connection;

public class Employee extends User{
	private EmployeeDAO employeeDAO;
	
	public Employee(int userId, String userType, Connection connection) {
		super(userId, userType, connection);
		employeeDAO = new EmployeeDAO(connection);
		
	}
	@Override
	void runMenu() {
		System.out.println("This is a ");
		
	}

}
