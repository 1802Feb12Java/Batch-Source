package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class AdministratorDAO extends UserDAO {
	final static Logger logger = Logger.getLogger(AdministratorDAO.class);
	
	protected AdministratorDAO(Connection connection) {
		super(connection);
	}
	
	public List<String> getUsers() {
		List<String> users = new ArrayList<String>();
		String getUsersString = "SELECT * FROM userAccounts";
		try {
			PreparedStatement getUsersQuery = connection.prepareStatement(getUsersString);
			ResultSet resultSet = getUsersQuery.executeQuery();
			while(resultSet.next()) {
				int userId = resultSet.getInt(1);
				String userName = resultSet.getString(3);
				String firstName = resultSet.getString(4);
				String lastName = resultSet.getString(5);
				String userType = resultSet.getString(6);
				
				switch (userType) {
				case "C":
					userType = "Customer";
					break;
				case "E":
					userType = "Employee";
					break;
				case "A":
					userType = "Administrator";
					break;
				default:
					break;
				}
				
				String userString = "User ID: " + userId + "\nUsername: " + userName +
						"\nFirst Name: " + firstName + "\nLast Name: " + lastName + 
						"\nUser Type: " + userType + "\n";
				
				users.add(userString);
				
		}
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return users;
	}

}
