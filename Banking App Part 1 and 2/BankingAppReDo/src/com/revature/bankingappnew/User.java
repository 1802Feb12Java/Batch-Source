package com.revature.bankingappnew;

import java.sql.SQLException;

/*
 * 
 */
public class User{
	
	private final int userID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String userType;
	
	public User() {
		this.username = "username";
		this.password = "password";
		this.firstName = "First Name";
		this.lastName = "Last Name";
		this.userType = "User";
		this.userID = 00000;
	}
	
	public User(String username, String password, String firstName, String lastName, String type) throws SQLException {
		this.username = Validation.validateUsername(username);
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = type;
		com.revature.bankingappnew.daoimplementations.UserLogImp.createUserRecord(username, password, type, firstName, lastName);
		this.userID = com.revature.bankingappnew.daoimplementations.UserLogImp.getUserObject(username).getUserID();
	}
	
	public User(int userID, String username, String password, String firstName, String lastName, String type) throws SQLException {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = type;
		this.userID = userID;
	}//end constructor. this constructor will be used to create objects from DB records
	
	public void viewUserDetails() {
		System.out.println("User Information:");
		System.out.println();
		System.out.println("User type: " + userType);
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println("Username: " + username);
		System.out.println();
	}//end viewUserDetails

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) throws SQLException {
		com.revature.bankingappnew.daoimplementations.UserLogImp.updateUserRecord(username, this.password, this.userType, this.firstName, this.lastName);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws SQLException {
		com.revature.bankingappnew.daoimplementations.UserLogImp.updateUserRecord(this.username, password, this.userType, this.firstName, this.lastName);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) throws SQLException {
		com.revature.bankingappnew.daoimplementations.UserLogImp.updateUserRecord(this.username, this.password, this.userType, firstName, this.lastName);
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) throws SQLException {
		com.revature.bankingappnew.daoimplementations.UserLogImp.updateUserRecord(this.username, this.password, this.userType, this.firstName, lastName);
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) throws SQLException {
		com.revature.bankingappnew.daoimplementations.UserLogImp.updateUserRecord(this.username, this.password, userType, this.firstName, this.lastName);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

	public int getUserID() {
		return userID;
	}

}//end class
