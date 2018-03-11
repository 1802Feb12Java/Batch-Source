package com.revature.ers.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.ers.dao.UserDAO;
import com.revature.ers.util.ConnFactory;

public class UserServices implements UserDAO{
	protected Logger log = Logger.getRootLogger();
		
	public boolean addUser(User user) throws SQLException {
		//create the connection
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		PreparedStatement ps = null;
		
		//Form the string and the prepared statement
		
		//assign the values from the new user form
		
		//assign the role as new employee (UR_ID = 3)
		
		//log the user creation
		
		return true;
	}

	public User getUser(int u_ID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		
		//TODO Log the user update
		return false;
	}

	public boolean deleteUser(int u_id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public List<User> getAllEmployees() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validateUser(String u_userName, String u_password) throws SQLException {
		//log the validation request
		log.info("Attempted login with user name: " + u_userName);

		//open the connection object
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		PreparedStatement ps = null;
		
		//create the prepared statement
		String sql = "SELECT U_PASSWORD FROM ERS_USERS WHERE U_USERNAME = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, u_userName);
		
		//execute the query
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			log.info("User " + u_userName + " not found.");
			ps.close();
			rs.close();
			conn.close();
			return false;
		}
		
		else if (u_password.equals(rs.getString("U_PASSWORD"))){
			log.info("User " + u_userName + " successfully logged in");
			ps.close();
			rs.close();
			conn.close();
			return true;
		}
		
		else {
			log.info("Incorrect password for " + u_userName);
			ps.close();
			rs.close();
			conn.close();
			return false;
		}
		
		//TODO Get user role, if ur_id = 3, have the user reset the password
		
			//TODO change user to regular employee (ur_id = 2)
		
			//TODO update the employee record
	}

	public int getUserRole(String u_userName) throws SQLException {
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		PreparedStatement ps = null;
		int role = 0;
		
		//create the prepared statement
		String sql = "SELECT UR_ID FROM ERS_USERS WHERE U_USERNAME = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, u_userName);
		
		//execute the query
		ResultSet rs = ps.executeQuery();
		rs.next();
		role = rs.getInt("UR_ID");
		rs.close();
		ps.close();
		return role;
	}
}
