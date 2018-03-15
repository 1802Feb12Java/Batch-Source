package com.revature.dao;

import java.sql.SQLException;

public interface UserDAO {
	// Read
	boolean logIn(String username, String password) throws SQLException;

	String[] getUserInformation(String username) throws SQLException;

	String getRole(String username) throws SQLException;

	String getAllUsers() throws SQLException;
	
	String getFirstName(String username) throws SQLException;

	// Update
	void updateInformation(String oldUsername, String newUsername, String password, String firstName, String lastName, String email)
			throws SQLException;
}