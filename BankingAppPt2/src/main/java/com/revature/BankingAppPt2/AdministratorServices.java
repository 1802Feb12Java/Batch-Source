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
		
	}
	
	public void updateUser() {
		
	}
	
	public void deleteUser() {
		
	}
}
