package com.revature.banking.jdbc.model;

import java.io.Serializable;

public class Customer extends User implements Serializable {
	private static final long serialVersionUID = 56L;
	protected String firstName, lastName, email;
	public Customer(int customerId, String username, String password,  String email, String firstName, String lastName) {
		super(customerId, username, password, email, "Customer");
		this.firstName = firstName;
		this.lastName = lastName;
		this.email=email;
	}
	public Customer(int customerId, String username, int password, String email, String firstName, String lastName) {
		super(customerId, username, password, email, "Customer");
		this.firstName = firstName;
		this.lastName = lastName;
		this.email=email;
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
		return this.getRole() + "[ID=" + getUserId() + ", Username=" + this.getUsername() + ", Name=" + firstName + ", " + lastName  + ", Email=" + email + "]";
	}
}
