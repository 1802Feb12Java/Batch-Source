package com.revature;

import java.sql.SQLException;
import java.util.ArrayList;


public interface UsersDAO {

	public void addUser(Users u) throws SQLException;
	public Users getUsers(int id) throws SQLException;
	public void updateUserPassword(Users u) throws SQLException;
	public void updateUserEmail(Users u) throws SQLException;
	public void deleteUsers(int id) throws SQLException;
	public ArrayList<Users> getAllUsers() throws SQLException; 
	
}
