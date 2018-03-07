package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.User;

/**
 * CRUD Ops for User POJO
 */
public interface UserDao {

	public void addUser(User user) throws SQLException;

	public User getUser(int userId) throws SQLException;

	public void updateUser(User user) throws SQLException;

	public void deleteUser(int userId) throws SQLException;

	public List<User> getAllUsers() throws SQLException;
}
