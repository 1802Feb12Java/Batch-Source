package com.revature.bean;

import java.io.Serializable;

public class UserRole implements Serializable {
	private static final long serialVersionUID = -2098619719461545566L;
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

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", role=" + role + "]";
	}
}
