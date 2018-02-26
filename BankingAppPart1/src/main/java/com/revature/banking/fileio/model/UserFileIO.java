package com.revature.banking.model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -2857543285068929262L;
	private final int userId;
	private String username, email;
	private final String role;
	private int password;
	public User(int id, String username, String password, String email, String role) {
		super();
		this.userId = id;
		this.username=username;
		this.role = role;
		this.password=password.hashCode();
		this.email=email;
		//System.out.println(toString() + " " + this.password);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserId() {
		return userId;
	}
	public void setPassword(String password) {
		System.out.println("Changed password to "+ password);
		this.password=password.hashCode();
	}
	public boolean checkPassword(String password) {
		if(password.hashCode()==this.password)
			return true;
		else
			return false;
	}
	public String getRole() {
		return role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "\t" + userId + ", " + username + ", " + role ;
	}

}
