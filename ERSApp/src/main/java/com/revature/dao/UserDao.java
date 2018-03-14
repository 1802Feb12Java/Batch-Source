package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.User;

/**
 * CRUD Ops for User POJO
 */
public interface UserDao {

	public void addUser(Connection con, User user) throws SQLException;

	public User getUser(Connection con, int userId) throws SQLException;

	public void updateUser(Connection con, User user) throws SQLException;

	public void deleteUser(Connection con, int userId) throws SQLException;

	public List<User> getAllUsers(Connection con) throws SQLException;
}
