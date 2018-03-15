package com.revature.beans;

import java.util.ArrayList;

public class Employee implements User {

	private String username;
	private String password;
	private Integer empId;
	private String email;
	private String firstname;
	private String lastname;
	
	private ArrayList<Reimbursement> reimbursements;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		//this.empId = empId;
	}
	public Employee(String username, String password, String email,
			String firstname, String lastname) {
		this(username, password, email);
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", empId=" + empId + "]";
	}
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
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(int i) {
		this.empId = i;
	}
	@Override
	public int getAccess() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public String getLastname() {
		// TODO Auto-generated method stub
		return this.lastname;
	}
	@Override
	public String getFirstname() {
		// TODO Auto-generated method stub
		return this.firstname;
	}
	public ArrayList<Reimbursement> getReimbursements() {
		return reimbursements;
	}
	public void setReimbursements(ArrayList<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	
	
	
	
}
