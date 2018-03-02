package com.revature.dao;

import java.util.List;

import com.revature.beans.User;

import java.sql.SQLException;

public interface UserDao {
	
	// insert new user
	public void insertNewUser(int roleId, String firstname, String lastname, String email, String username, String password) throws SQLException;
	
	// get user
	public User getUser(int id) throws SQLException;
	
	// retrieve users
	public List<User> retrieveAllUsers() throws SQLException;
	
	// update user
	public void updateUser(User user) throws SQLException;
	
	// delete user
	public void deleteUser(int id) throws SQLException;
	
	// check for account

}
