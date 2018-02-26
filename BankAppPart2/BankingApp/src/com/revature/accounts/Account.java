package com.revature.accounts;

import java.io.Serializable;

//never want anyone to instantiate an Account Object
public abstract class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//instance variables
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	public Account() {

	}
	
	public String getUsername() {
		return username;
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

	public boolean setUsername(String username) {
		//username max length will be 32 characters
		if(username.length() < 32) {
			this.username = username;
		}
		else {
			System.out.println("ERROR: Invalid Username");
			return false;
		}
		return true;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean setPassword(String password) {
		if(password.length() < 32) {
			this.password = password;
		}
		else {
			System.out.println("ERROR: Invalid Password");
			return false;
		}
		return true;
	}
	
	//abstract methods
	//everyone Account type will have a different PRIORITY_LEVEL
	public abstract byte getPriorityLevel();
	
}
