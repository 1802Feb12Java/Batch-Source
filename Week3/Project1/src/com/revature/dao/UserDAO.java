package com.revature.dao;

import java.sql.SQLException;

import com.revature.bean.User;

public interface UserDAO {
	// Read
	boolean logIn(String username, String password) throws SQLException;
	User getUserInformation(String username) throws SQLException;

	// Update
	void updateInformation(String username) throws SQLException;
}