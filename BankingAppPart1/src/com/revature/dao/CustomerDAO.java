package com.revature.dao;

import com.revature.users.User;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
	//CRUD operations
	public void addUser(User user) throws SQLException;
	public User getUser(String uName)throws SQLException;
	public void updateUser(User user)throws SQLException;
	public void deleteUser(User user)throws SQLException;
	public List<User> getAllUsers()throws SQLException;

}
