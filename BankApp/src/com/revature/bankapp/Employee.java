package com.revature.bankapp;

public class Employee {

	static int numEmployees;
	private int eIN;
	private String username;
	private String password;
	private	char accessLevel='e';
	public Employee(int eIN, String username, String password) {
		super();
		numEmployees++;
		this.eIN = eIN;
		this.username = username;
		this.password = password;
	}
	public Employee() {
		super();
		numEmployees++;
		this.eIN = numEmployees;
		this.username = "";
		this.password = "";
	}
	public int geteIN() {
		return eIN;
	}
	public void seteIN(int eIN) {
		this.eIN = eIN;
	}
	public char getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(char accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	public void viewAcct(Account a)
	{
		if (this.accessLevel=='e' || this.accessLevel=='a')
		{
			System.out.println(a.toString());
		}
	}
	public String getUsername() {
		return username;
	}
	@Override
	public String toString() {
		return "Employee [eIN=" + eIN + ", username=" + username + ", password=" + password + ", accessLevel="
				+ accessLevel + "]";
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
	
	
	
	
	
}
