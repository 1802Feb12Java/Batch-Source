package com.revature.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {
	// Read
	boolean logIn(String username, String password) throws SQLException;

	String[] getUserInformation(String username) throws SQLException;

	String getRole(String username) throws SQLException;

	String getAllUsers() throws SQLException;

	// Update
	void updateInformation(String username, String password, String firstName, String lastName, String email)
			throws SQLException;
}