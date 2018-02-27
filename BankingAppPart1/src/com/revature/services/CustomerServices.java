package com.revature.services;

import java.sql.*;
import java.util.List;
import com.revature.dao.CustomerDAO;
import com.revature.users.User;
import com.revature.util.ConnFactory;

public class CustomerServices implements CustomerDAO {

	ConnFactory cf = new ConnFactory();
	Connection conn = cf.getConnection();

	public void addUser(User user) throws SQLException {
		String accHolder = "N";
		
		//unlikely to be used upon user creation, but including just in case
		if(user.isAccountHolder()) {
			accHolder = "Y";
		}
		
		//set the string to use in the prepared statement and create the statement
		String sql = "INSERT INTO USERS(userID, userName, pass, firstName, lastName, streetAddress, city, state, phoneNumber, userType, accountHolder) VALUES(GENERATE_USER_ID.nextVal,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		//use the user values to set the sql variables
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getFirstName());
		ps.setString(4, user.getLastName());
		ps.setString(5, user.getStreetAddress());
		ps.setString(6, user.getCity());
		ps.setString(7, user.getState());
		ps.setString(8, user.getPhoneNumber());
		ps.setString(9, user.getUserType());
		ps.setString(10, accHolder);
		
		ps.execute();
		ps.close();
	}

	public User getUser(String uName) throws SQLException {
		String sql = "SELECT * FROM USERS WHERE USERNAME=?";
		User user = null;
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, uName);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next() == false) {
			System.out.println("User not found.");
			return user;
		}
		
		else {
			user = new User();
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("pass"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setStreetAddress(rs.getString("streetAddress"));
			user.setCity(rs.getString("city"));
			user.setState(rs.getString("state"));
			user.setPhoneNumber(rs.getString("phoneNumber"));
			user.setUserType(rs.getString("userType"));
		}
		
		if(rs.getString("accountHolder") == "Y") {
			user.setAccountHolder(true);
		}

		
		else {
			user.setAccountHolder(false);
		}

		ps.close();
		rs.close();
		return user;
	}

	public void updateUser(User user) throws SQLException {
		//set the string to use in the prepared statement and create the statement
		String sql = "UPDATE USERS SET userName = ?, pass = ?, firstName = ?, lastName =?, streetAddress = ?, city = ?, state = ?, phoneNumber = ?, userType = ?, accountHolder = ? WHERE userID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		//use the user values to set the sql variables
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getFirstName());
		ps.setString(4, user.getLastName());
		ps.setString(5, user.getStreetAddress());
		ps.setString(6, user.getCity());
		ps.setString(7, user.getState());
		ps.setString(8, user.getPhoneNumber());
		ps.setString(9, user.getUserType());
		ps.setBoolean(10, user.isAccountHolder());
		ps.setInt(11, user.getUserID());

		
		ps.executeUpdate();
		ps.close();
		
	}

	public void deleteUser(String userName) throws SQLException {
		//TODO
	}

	public List<User> getAllUsers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
