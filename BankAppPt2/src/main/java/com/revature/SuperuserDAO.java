package com.revature;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.User;

public interface SuperuserDAO {

	void viewAllUsers() throws SQLException;
	
	void createUser(String name, String password, double bal) throws SQLException;
	
	void giveMoney(User u, double d) throws SQLException;
	void takeMoney(User u, double d) throws SQLException;
	
	void deleteUser(User u) throws SQLException;
	
}
