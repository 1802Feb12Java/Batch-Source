package com.revature.expensereimbursement.model;

public class ERSUserRole {
	int id;
	String role;
	public ERSUserRole(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "ERSUserRole [id=" + id + ", role=" + role + "]";
	}
	

}
