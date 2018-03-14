package com.revature.beans;

import java.sql.SQLException;

public class Employee extends User {

	public Employee(String username, String password, String firstName, String lastName, String email) throws SQLException {
		super(username, password, firstName, lastName, email, 0);
		
	}
	
	public Employee(int u_id, String username, String pass, String fname, String lname, String email, int ur_id) {
		this.setUser_ID(u_id);
		this.setUsername(username);
		this.setPassword(pass);
		this.setFirstName(fname);
		this.setLastName(lname);
		this.setEmail(email);
		this.setUserRole_ID(ur_id);
	}
	
	public void submitReimbursementRequest() {
		
	}
	
	public void uploadImageToRequest() {
		
	}
	
	public void viewPendingReimbursementRequests() {
		
	}
	
	public void viewResolvedReimbursementRequests() {
		
	}
	
	public void viewPersonalInformation() {
		
	}
	

}//end class
