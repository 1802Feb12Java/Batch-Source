package com.revature.BankingAppPt2;

public abstract class User {
	private String userType;
	private String userName;
	private String firstName;
	private String lastName;
	
	abstract void runMenu();
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	@Override
	public String toString() {
		return "User [userType=" + userType + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}
	
	
}
