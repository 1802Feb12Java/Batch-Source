package com.revature.objects;

public class Employee {
	private Integer ID;
	private String Username;
	private String Password;
	private String Firstname;
	private String Lastname;
	private String Email;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	@Override
	public String toString() {
		return "Employee " + ID + ":"+ Username + ":"+  Password + ":"+ Firstname +	":"+  Lastname + ":"+ Email;
	}
	public Employee(Integer iD, String username, String password, String firstname, String lastname, String email) {
		super();
		ID = iD;
		Username = username;
		Password = password;
		Firstname = firstname;
		Lastname = lastname;
		Email = email;
	}
	
	
}
	