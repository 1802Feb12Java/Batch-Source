package com.revature.expensereimbursement.model;

public class ERSUser {
	int userId, userRoleId;
	String username, password, firstName, lastName, email, userRole;
	
	public ERSUser( String username, String password, String firstName, String lastName,
			String email, int userRoleId, String userRole) {
		super();
		this.userRoleId = userRoleId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole=userRole;
	}

	public ERSUser() {
		
	}

	public ERSUser(String username, String password, String firstName, String lastName, String email,
			String userRole) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}

	public ERSUser(int userId, String username, String password, String firstName, String lastName,
			String email, String userRole) {
		super();
		this.userId = userId;
		//this.userRoleId = userRoleId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "ERSUser [userId=" + userId + ", username=" + username + ", password="
				+ password + ", firstName=" + firstName + ", lastName=" + lastName 
				+ ", email=" + email + ", userRole=" + userRole + "]";
	}
	

}
