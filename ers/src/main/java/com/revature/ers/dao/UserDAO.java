package com.revature.ers.dao;

import java.sql.SQLException;

import com.revature.ers.users.User;

public interface UserDAO {
	public void addUser(User user) throws SQLException;
}
