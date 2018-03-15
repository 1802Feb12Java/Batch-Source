package com.revature.beans;

public class User {

	private int userID;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private int roleID; 
	
	
	public User() {
		super();
	}

	public User(int userID, String firstname, String lastname, String username, String password, String email,
			int roleID) {
		super();
		this.userID = userID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roleID = roleID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	@Override
	public String toString() {
		return "User [userID= " + userID + ", firstname= " + firstname + ", lastname= " + lastname + ", username= " + username 
				+ ", password= " + password + ", email= " + email + ", roleID= " + roleID + "]";
	}
}

