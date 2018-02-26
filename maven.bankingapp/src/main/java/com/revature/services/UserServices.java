package com.revature.services;

import java.sql.Connection;
import java.util.List;

import com.revature.beans.User;
import com.revature.dao.UserDao;
import com.revature.util.ConnFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServices implements UserDao {
	
	// Create connection 
	ConnFactory cf = new ConnFactory();
	
	public void insertNewUser(int roleId, String firstName, String lastName, String email, String phone, String username, String password) {
		
		try(Connection conn = cf.getConnection()) {
			
			// create statement
			String sqlInsert = "INSERT INTO bank_user(id, role_id, first_name, last_name, email, phone, username, password) ";
			sqlInsert += "VALUES(userIdSequence.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			
			// instantiate ps object
			PreparedStatement ps = conn.prepareStatement(sqlInsert);
			
			// set sql statement values
			ps.setInt(1, roleId);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, email);
			ps.setString(5, phone);
			ps.setString(6, username);
			ps.setString(7, password);
			
			int rowsInserted = ps.executeUpdate();
			if(rowsInserted > 0) {
				System.out.println();
				System.out.println("******************************************************");
				System.out.println("Your account has been created! You may now log in.");
				System.out.println("******************************************************");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User getUserByUsername(String username) {
		
		try(Connection conn = cf.getConnection()) {
			
			// Create get user query
			String sqlGet = "SELECT * FROM bank_user WHERE username = ?";
			
			// Instantiate ps
			PreparedStatement ps = conn.prepareStatement(sqlGet);
			
			// Set username value in statement
			ps.setString(1, username);
			
			// Instantiate user object
			User user = null;
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("ID");
				int role_id = rs.getInt("ROLE_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String uname = rs.getString("USERNAME");
				String pword = rs.getString("PASSWORD");
				user = new User(id, role_id, firstName, lastName, email, phone, uname, pword);
			}
			
			return user;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public User getUser(int id) throws SQLException {
		return null;
	}

	public List<com.revature.beans.User> retrieveAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUser(com.revature.beans.User user) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int id) throws SQLException {
		
		
	}

}
