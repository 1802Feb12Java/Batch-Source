package com.revature.banking.model;

public class Employee extends User{
	private static final long serialVersionUID = -6787239680576469833L;
	public Employee(int ID, String username, String password, String email) {
		super(ID, username, password, email, "Employee");
	}
	public Employee(int ID, String username, String password, String email, String role) {
		super(ID, username, password, email,role);
	}
	@Override
	public String toString() {
		return "Employee [ID=" + getUserId() + ", username=" + getUsername() + ", Email=" + getEmail() + ", ]";
	}



}
