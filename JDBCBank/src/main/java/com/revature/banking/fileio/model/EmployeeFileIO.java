package com.revature.banking.fileio.model;

public class EmployeeFileIO extends UserFileIO{
	private static final long serialVersionUID = -6787239680576469833L;
	public EmployeeFileIO(int ID, String username, String password, String email) {
		super(ID, username, password, email, "Employee");
	}
	public EmployeeFileIO(int ID, String username, String password, String email, String role) {
		super(ID, username, password, email,role);
	}
	@Override
	public String toString() {
		return "Employee [ID=" + getUserId() + ", username=" + getUsername() + ", Email=" + getEmail() + ", ]";
	}



}
