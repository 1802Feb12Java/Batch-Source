package com.revature.bankapp.model;


public class User {
	private String typeOfUser;
	private String username;
	private String password;
	
	public User() {}
	
	/**
	 * @param typeOfUser
	 * @param username
	 * @param password
	 */
	public User(String typeOfUser, String username, String password) {
		this.typeOfUser = typeOfUser;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the typeOfUser
	 */
	public String getTypeOfUser() {
		return typeOfUser;
	}

	/**
	 * @param typeOfUser the typeOfUser to set
	 */
	public void setTypeOfUser(String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
