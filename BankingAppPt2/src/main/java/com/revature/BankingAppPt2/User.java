package com.revature.BankingAppPt2;

import java.sql.Connection;

public abstract class User {
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected String userType;
	protected int userId;
	protected Connection connection;
	
	protected User(int userId, String userType, Connection connection) {
		this.userId = userId;
		this.connection = connection;
		this.userType = userType;
	}
	
	abstract void runMenu();
	
	public String getUserType() {
		return userType;
	}

	@Override
	public String toString() {
		return "User [userType=" + userType + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}


	
	
}
