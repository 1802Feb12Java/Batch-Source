package com.revature.DAOs;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAOClass implements UserDAO {
	private static Connection conn;
	
	public void createUser(User u) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("insert into ERS_USERS values (?,?,?,?,?,?,?)");
		PreparedStatement idps = conn.prepareStatement("select useridsequence.nextval from dual");
		ResultSet rs = idps.executeQuery();
		int nextId = 0;
		if(rs.next()) {
			nextId = rs.getInt(1);
		}
		ps.setInt(1, nextId);
		ps.setString(2, u.getUsername());
		ps.setString(3, u.getPassword());
		ps.setString(4, u.getFirstname());
		ps.setString(5, u.getLastname());
		ps.setString(6, u.getEmail());
		ps.setInt(7, u.getRoleId());
		
		ps.executeQuery();
	}

	public User readUser(int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE u_id = (?)");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		String[] info = new String[5];
		int roleId = 0;
		if(rs.next()) {
			info[0] = rs.getString(2);	//username
			info[1] = rs.getString(3);	//password
			info[2] = rs.getString(4);	//firstname
			info[3] = rs.getString(5);	//lastname
			info[4] = rs.getString(6);	//email
			roleId = rs.getInt(7);
		}
		return new User(id, info[0], info[1], info[2], info[3], info[4], roleId);
	}

	public void updateFirstname(String newVal, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update ERS_USERS set u_firstname = (?) where u_id = (?)");
		ps.setString(1, newVal);
		ps.setInt(2, id);
		ps.executeQuery();
	}
	
	public void updateLastname(String newVal, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update ERS_USERS set u_lastname = (?) where u_id = (?)");
		ps.setString(1, newVal);
		ps.setInt(2, id);
		ps.executeQuery();
	}
	
	public void updateUsername(String newVal, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update ERS_USERS set u_username = (?) where u_id = (?)");
		ps.setString(1, newVal);
		ps.setInt(2, id);
		ps.executeQuery();
	}
	
	public void updatePassword(String newVal, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update ERS_USERS set u_password = (?) where u_id = (?)");
		ps.setString(1, newVal);
		ps.setInt(2, id);
		ps.executeQuery();
	}
	
	public void updateEmail(String newVal, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update ERS_USERS set u_email = (?) where u_id = (?)");
		ps.setString(1, newVal);
		ps.setInt(2, id);
		ps.executeQuery();
	}

	public ArrayList<User> getAllUsers() throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_USERS");
		ResultSet rs = ps.executeQuery();
		ArrayList<User> allUsers = new ArrayList<User>();
		
		while (rs.next()) {
			int id = rs.getInt(1);
			String username = rs.getString(2);
			String password = rs.getString(3);
			String firstname = rs.getString(4);
			String lastname = rs.getString(5);
			String email = rs.getString(6);
			int roleId = rs.getInt(7);
			allUsers.add(new User(id, username, password, firstname, lastname, email, roleId));
		}
		
		return allUsers;
	}
	
	public UserDAOClass(Connection c){
		if(conn == null) {
			UserDAOClass.conn = c;
		}
	}
}
