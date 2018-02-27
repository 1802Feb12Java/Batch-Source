package com.revature.banking.jdbc.model;


public class Admin extends Employee {
	private static final long serialVersionUID = -926500983082199475L;
	public Admin(int ID, String username, String password, String email) {
		super(ID, username, password, email, "Admin");
	}
	public Admin(int ID, String username, int password, String email) {
		super(ID, username, password, email, "Admin");
	}

	public String toString() {
			return this.getRole() + " [ AdminID=" + getUserId() + ", Username=" + getUsername() + ", Email=" + getEmail() + " ]";
		}
}
