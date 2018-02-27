package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public interface UserDAO {

	//create user
	void createUser(Connection c, String Username, String Password) throws SQLException;
	
	//create superuser
	void createSuperUser(Connection c, String Username, String Password) throws SQLException;
	
	//update username
	void updateUsername(Connection c, User loggedIn, Scanner sc) throws SQLException;
	
	//update password
	void updatePassword(Connection c, User loggedIn, Scanner sc) throws SQLException;
	
	//login
	User login(Connection c, String username, String password, Scanner sc) throws SQLException;
	
	//logout
	void logout() throws SQLException;
	
	//superusers
	//view user
	void viewUser(Connection c) throws SQLException;
	
	//delete user
	void deleteUser(Connection c, int userid) throws SQLException;
	
}
