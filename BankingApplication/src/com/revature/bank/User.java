package com.revature.bank;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 7514140090199215786L;
	public String username = "";
	public String password = "";
	public String firstName = "";
	public String lastName = "";
	
	public User(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
