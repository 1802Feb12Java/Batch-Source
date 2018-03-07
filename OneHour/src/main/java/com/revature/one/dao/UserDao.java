package com.revature.one.dao;

import java.sql.SQLException;

import com.revature.one.beans.User;

public interface UserDao {
	public void addUser(User user) throws SQLException;

	public User getUser(int userId) throws SQLException;

}
