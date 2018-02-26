package com.revature.model;

public class User {
	private int userId;
	private String userName;
	private String firstName;
	private String lastName;
	
	protected User() {
		this(0, null, null, null);
	}
	
	protected User(int userId, String userName, String fName, String lName) {
		this.userName = userName;
		firstName = fName;
		lastName = lName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}
