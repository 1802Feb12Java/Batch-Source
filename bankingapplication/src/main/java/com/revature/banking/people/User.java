package com.revature.banking.people;

import java.io.Serializable;

/**
 * Stores user credentials
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1752696879761002407L;
	private String username;
	private String password;
	private final String personID;

	public User(String username, String password, String personID) {
		this.username = username;
		this.password = password;
		this.personID = personID;
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

	public String getPersonID() {
		return personID;
	}

	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + ", personID=" + personID + "]";
	}
}
