package com.revature.bankapp;

public class Administrator extends Employee {

	private int eIN;
	private String username;
	private String password;
	private	char accessLevel='a';
	public Administrator(int i, String string, String string2) {
		Employee.numEmployees++;
		eIN = numEmployees;
		this.username = string;
		this.password = string2;
		
	}
	@Override
	public String toString() {
		return "Administrator [eIN=" + eIN + ", username=" + username + ", password=" + password + ", accessLevel="
				+ accessLevel + "]";
	}
	public int geteIN() {
		return eIN;
	}
	public void seteIN(int eIN) {
		this.eIN = eIN;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(char accessLevel) {
		this.accessLevel = accessLevel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
