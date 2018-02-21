package com.revature.beans;

/**
 * 
 * @author johne
 *
 * track which user is using the application. Can be an customer, employee, or admin
 */
public class User {
	
	private String typeOfUser;
	private String username;	//username to login to bank app
	private String password;	//password to login to bank app
	private int userID = 0;	//customerID will associate with the account
	private static int count = 0;
	/**
	 * @param typeOfUser
	 */
	public User(String typeOfUser, String username, String password) {
		this.typeOfUser = typeOfUser;
		this.username = username;
		this.password = password;
		this.setUserID(count++);
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

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
