package com.revature.dao;

import java.sql.SQLException;

import com.revature.controllers.User;

public interface UserDAO {
	// Read
	boolean logIn(String username, String password) throws SQLException;
	String[] getUserInformation(String username) throws SQLException;
	String getRole() throws SQLException;

	// Update
	void updateInformation(String username) throws SQLException;
}