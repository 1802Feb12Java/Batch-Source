package com.revature.beans;

import java.sql.SQLException;

public class Manager extends User {

	public Manager(String username, String password, String firstName, String lastName, String email) throws SQLException {
		super(username, password, firstName, lastName, email, 1);
		
	}

	public Manager(int u_id, String username, String pass, String fname, String lname, String email, int ur_id) {
		this.setUser_ID(u_id);
		this.setUsername(username);
		this.setPassword(pass);
		this.setFirstName(fname);
		this.setLastName(lname);
		this.setEmail(email);
		this.setUserRole_ID(ur_id);
	}
	
	public void approveDenyPendingRequests() {
		
	}
	
	public void viewAllPendingRequests() {
		
	}
	
	public void viewImagesFromARequest() {
		
	}
	
	public void viewAllResolvedRequests() {
		
	}
	
	public void viewAnEmployeesRequests() {
		
	}
	
	public void viewAllEmployeeInfo() {
		
	}
	
}//end class
