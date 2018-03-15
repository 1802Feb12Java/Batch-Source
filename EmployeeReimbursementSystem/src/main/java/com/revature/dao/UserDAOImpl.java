package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.revature.beans.User;
import com.revature.utility.ConnectionFactory;

public class UserDAOImpl implements UserDAO{

	static ConnectionFactory cf = ConnectionFactory.getInstance();
	
	public ArrayList<User> getUsers() {
		// TODO Auto-generated method stub
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_USERS");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				
				user.setUserID(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setUserRoleID(rs.getInt(7));
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			}
		
		return users;
		
	}
	
	public ArrayList<User> getEmployees() {
		// TODO Auto-generated method stub
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE UR_ID = ?");
			ps.setInt(1, 0);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				
				user.setUserID(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setUserRoleID(rs.getInt(7));
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			}
		
		return users;
		
	}
	
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("ConnectionBegin");
			Connection conn = cf.getConnection();
			System.out.println("ConnectionEnd");
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ERS_USERS Values(?, ?, ?, ?, ?, ?, ?)");
			
			ps.setInt(1, user.getUserID());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getFirstName());
			ps.setString(5, user.getLastName());
			ps.setString(6, user.getEmail());
			ps.setInt(7, user.getUserRoleID());
			
			System.out.println("ExecuteBegin");
			ps.execute();
			System.out.println("ExecuteEnd");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Add user Failed");
			e.printStackTrace();
		}
		
	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE ERS_USERS SET U_USERNAME = ?, U_PASSWORD = ?, U_FIRSTNAME = ?,"
					+ " U_LASTNAME = ?, U_EMAIL = ?, UR_ID = ? WHERE U_ID = ?");
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getUserRoleID());
			ps.setInt(7, user.getUserID());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public User getUserByUserPass(String username, String password) {
		try {
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE U_USERNAME = ? AND U_PASSWORD = ?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			//if we found a user
			if(rs.next()) {
				User user = new User();

				user.setUserID(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setUserRoleID(rs.getInt(7));
				
				return user;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public User getByID(int id) {
		try {
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE U_ID = ?");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			//if we found a user
			if(rs.next()) {
				User user = new User();

				user.setUserID(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setUserRoleID(rs.getInt(7));
				
				return user;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
