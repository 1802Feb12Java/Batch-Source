package com.revature.bean;

public class UserRole {
	private Integer id;
	private String role;
	
	public UserRole(Integer id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	
	public UserRole() {
		this(null, null);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
