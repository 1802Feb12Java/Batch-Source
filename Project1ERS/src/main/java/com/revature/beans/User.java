package com.revature.beans;

import java.sql.SQLException;

public class User {
	
	private int user_ID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int userRole_ID; // 0 - Employee; 1 - Manager
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String username, String password, String firstName, String lastName, String email, int userRole_ID) throws SQLException {
		
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole_ID = userRole_ID;
		this.user_ID = com.revature.dao.UserDAOImp.getUserID(username); //change
		
	}//end parameter constructor

	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getUserRole_ID() {
		return userRole_ID;
	}


	public void setUserRole_ID(int userRole_ID) {
		this.userRole_ID = userRole_ID;
	}


	public int getUser_ID() {
		return user_ID;
	}


	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	
	
	
	
	
}//end class
