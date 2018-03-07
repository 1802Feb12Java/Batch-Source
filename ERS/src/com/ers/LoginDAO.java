package com.ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	public static boolean validate(String userName, String password) {
		boolean status = false;
		try {
			Connection connection = DatabaseConnection.getDatabaseConnection();
			PreparedStatement loginQuery = connection.prepareStatement("select * from ERS_USERS where U_USERNAME = ? and U_PASSWORD = ?");
			loginQuery.setString(1, userName);
			loginQuery.setString(2, password);

			ResultSet resultSet = loginQuery.executeQuery();
			status = resultSet.next();

		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int getUserTypeId(String userName) {
		Connection connection = DatabaseConnection.getDatabaseConnection();
		int userId = 0;
		try {
			PreparedStatement userIdQuery = connection.prepareStatement("SELECT UR_ID FROM ERS_USERS WHERE U_USERNAME = ?");
			userIdQuery.setString(1, userName);
			ResultSet resultSet = userIdQuery.executeQuery();
			if (resultSet.next()) {
				userId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	public static String getUserFirstName(String userName) {
		Connection connection = DatabaseConnection.getDatabaseConnection();
		String userFirstName = "";
		try {
			PreparedStatement userIdQuery = connection.prepareStatement("SELECT U_FIRSTNAME FROM ERS_USERS WHERE U_USERNAME = ?");
			userIdQuery.setString(1, userName);
			ResultSet resultSet = userIdQuery.executeQuery();
			if (resultSet.next()) {
				userFirstName = resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userFirstName;
	}
	
	public static String getUserType(int userTypeId) {
		String userType = "";
		Connection connection = DatabaseConnection.getDatabaseConnection();
		try {
			PreparedStatement userTypeQuery = connection.prepareStatement("SELECT UR_ROLE FROM ERS_USER_ROLES WHERE UR_ID = ?");
			userTypeQuery.setInt(1, userTypeId);
			ResultSet resultSet = userTypeQuery.executeQuery();
			if (resultSet.next()) {
				userType = resultSet.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userType;
	}
}
