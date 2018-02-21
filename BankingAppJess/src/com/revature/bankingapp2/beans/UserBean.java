package com.revature.bankingapp2.beans;

import java.io.Serializable;

import com.revature.bankingapp1.Application;
import com.revature.bankingapp2.Validation;

public class UserBean implements Serializable{
	
	private static final long serialVersionUID = -1369560328938863340L;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public UserBean(String username, String password, String firstName, String lastName) {
		super();
		this.username = Validation.validateUsername(username);
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void viewUserDetails() {
		System.out.println("Customer Information:");
		System.out.println();
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println();
	}//end viewUserDetails

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

	@Override
	public String toString() {
		return "UserBean [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}
		
}//end class
