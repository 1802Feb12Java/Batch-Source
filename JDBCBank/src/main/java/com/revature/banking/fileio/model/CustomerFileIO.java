package com.revature.banking.fileio.model;

import java.io.Serializable;

public class CustomerFileIO extends UserFileIO implements Serializable {
	private static final long serialVersionUID = 56L;
	protected String firstName, lastName, email;
	public CustomerFileIO(int customerId, String username, String password, String firstName, String lastName, String email) {
		super(customerId, username, password, email, "Customer");
		this.firstName = firstName;
		this.lastName = lastName;
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
	@Override
	public String toString() {
		return this.getRole() + "[ID=" + getUserId() + ", " + this.getUsername() + ": " + firstName + ", " + lastName  + ", " + email + "]";
	}
}
