package com.revature.controllers;

public class User {

	public int uId;
	public String uUsername;
	public String uPassword;
	public String uFirstName;
	public String ULastName;
	public String uEmail;
	public String uRole;
	
	@Override
	public String toString() {
		return "UserBean\n\tuId:\t" + uId + "\n\tuUsername:\t" + uUsername + "\n\tuPassword:\t" + uPassword
				+ "\n\tuFirstName:\t" + uFirstName + "\n\tULastName:\t" + ULastName + "\n\tuEmail:\t" + uEmail
				+ "\n\tuRole:\t" + uRole;
	}

	boolean logIn(String username, String password) {
		boolean loggedIn = false;

		return loggedIn;
	}

	int getRole(String username) {
		int roleNumber = -1;

		return roleNumber;
	}

}