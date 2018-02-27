package com.revature.banking.model;


public class Admin extends Employee {
	private static final long serialVersionUID = -926500983082199475L;
	public Admin(int ID, String username, String password, String email) {
		super(ID, username, password, email, "Admin");
	}

	public String toString() {
			return this.getRole() + " [ AdminID=" + getUserId() + ", username=" + getUsername() + ", Email=" + getEmail() + " ]";
		}
}
