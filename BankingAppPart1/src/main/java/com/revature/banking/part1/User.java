package com.revature.banking.part1;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = -2857543285068929262L;
	
	private final int ID;
	private String username;
	private final String role;
	private int password;
	
	public User(int iD, String username, String password, String role) {
		super();
		ID = iD;
		this.username=username;
		this.role = role;
		this.password=password.hashCode();
		//System.out.println(toString() + " " + this.password);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getID() {
		return ID;
	}
	public void setPassword(String password) {
		System.out.println("Changed password to "+ password);
		this.password=password.hashCode();
	}
	public boolean checkPassword(String password) {
		//System.out.println("Passoword:\t" +this.password);
		//System.out.println("Entered passoword:\t" +password.hashCode());
		if(password.hashCode()==this.password)
			return true;
		else
			return false;
	}
	
	public String getRole() {
		return role;
	}
	@Override
	public String toString() {
		return "User [ID=" + ID + ", username=" + username + ", role=" + role + "]";
	}

}
