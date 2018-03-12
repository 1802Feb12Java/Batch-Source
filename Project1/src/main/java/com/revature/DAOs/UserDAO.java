package com.revature.DAOs;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.User;

public interface UserDAO {
	public void createUser(User u) throws SQLException;
    public User readUser(int id) throws SQLException;
    public void updateFirstname(String newVal, int id) throws SQLException;	
	public void updateLastname(String newVal, int id) throws SQLException;	
	public void updateUsername(String newVal, int id) throws SQLException;	
	public void updatePassword(String newVal, int id) throws SQLException;
	public void updateEmail(String newVal, int id) throws SQLException;
    public ArrayList<User> getAllUsers() throws SQLException;
}
