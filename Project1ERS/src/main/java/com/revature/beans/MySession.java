package com.revature.beans;

public class MySession {

	private String username;
	private String userType;
	private int userID;
	
	public MySession(String username, String userType, int userID) {
		super();
		this.username = username;
		this.userType = userType;
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	
	
	
}//end class
