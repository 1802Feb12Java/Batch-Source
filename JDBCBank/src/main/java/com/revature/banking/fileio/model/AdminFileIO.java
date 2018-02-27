package com.revature.banking.fileio.model;


public class AdminFileIO extends EmployeeFileIO {
	private static final long serialVersionUID = -926500983082199475L;
	public AdminFileIO(int ID, String username, String password, String email) {
		super(ID, username, password, email, "Admin");
	}

	public String toString() {
			return this.getRole() + " [ AdminID=" + getUserId() + ", username=" + getUsername() + ", Email=" + getEmail() + " ]";
		}
}
