package com.revature.ers.users;

import java.sql.CallableStatement;
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
		
	public void addUser(User user) throws SQLException {
		//create the connection
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		CallableStatement cs = null;
		
		//Form the string and the prepared statement
		String sql = "{CALL ADD_USER(?,?,?,?,?,?)}";
		cs = conn.prepareCall(sql);

		//assign the values from the new user form
		cs.setString(1, user.getU_userName());
		cs.setString(2, user.getU_password());
		cs.setString(3, user.getU_firstName());
		cs.setString(4, user.getU_lastName());
		cs.setString(5, user.getU_email());
		
		//TODO: assign the role as new employee (UR_ID = 3)
		cs.setInt(6, user.getUr_ID());
		
		//log the user creation
		log.info("User " + user.getU_userName() + " created.");
		
		//execute the callable statement
		cs.executeUpdate();
	}

	public User getUser(int u_ID) throws SQLException {
		User user = new User();
		
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ID FROM ERS_USERS WHERE U_ID = ?";
		
		ps.setInt(1, u_ID);
		rs = ps.executeQuery(sql);
		
		if (!rs.next()) {
			return null;
		}
		
		else {
			user.setU_firstName(rs.getString("U_FIRSTNAME"));
			user.setU_lastName(rs.getString("U_LASTNAME"));
			user.setU_email(rs.getString("U_EMAIL"));
			user.setUr_ID(rs.getInt("UR_ID"));
			return user;
		}
	}

	public void updateUser(User user) throws SQLException {
		//create the connection
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		CallableStatement cs = null;
		
		//Form the string and the callable statement
		String sql = "{CALL UPDATE_USER(?,?,?,?,?,?,?)}";
		cs = conn.prepareCall(sql);

		//assign the values from the new user form
		cs.setInt(1, user.getU_id());
		cs.setString(2, user.getU_userName());
		cs.setString(3, user.getU_password());
		cs.setString(4, user.getU_firstName());
		cs.setString(5, user.getU_lastName());
		cs.setString(6, user.getU_email());
		cs.setInt(7, user.getUr_ID());
		
		cs.executeUpdate();
	}

	public void deleteUser(int u_id) throws SQLException {
		// TODO Auto-generated method stub
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
		ResultSet rs = null;
		
		//create the prepared statement
		String sql = "SELECT U_PASSWORD FROM ERS_USERS WHERE U_USERNAME = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, u_userName);
		
		//execute the query
		rs = ps.executeQuery();
		
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
	
	public int getUserID(String u_userName)throws SQLException {
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		PreparedStatement ps = null;
		int id = 0;
		
		//create the prepared statement
		String sql = "SELECT U_ID FROM ERS_USERS WHERE U_USERNAME = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, u_userName);
		
		//execute the query
		ResultSet rs = ps.executeQuery();
		rs.next();
		id = rs.getInt("U_ID");
		rs.close();
		ps.close();
		return id;
		
	}

}
