package com.revature.ers.dao;

import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONObject;

import com.revature.ers.users.User;

public interface UserDAO {
	//CRUD operations
	public void addUser(User user) throws SQLException;
	public User getUser(int u_ID) throws SQLException;
	public void updateUser(User user)throws SQLException;
	public void deleteUser(int u_id)throws SQLException;
	public List<User> getAllEmployees()throws SQLException;
	
	//user identification and validation operations
	public boolean validateUser(String u_userName, String u_password)throws SQLException;
	public int getUserRole(String u_userName)throws SQLException;
	public int getUserID(String u_userName)throws SQLException;
}
