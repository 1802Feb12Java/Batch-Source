package com.revature.beans;

public class User {
	
	public int id;
	public String username;
	public String password;
	public String firstName;
	public String lastName;
	public String email;
	public int roleId;
	
	public User(int id, String username, String password, String firstName, String lastName, String email, int roleId) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.roleId=roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", roleId=" + roleId + "]";
	};

}
