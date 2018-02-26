package com.revature.beans;

public class User {
	
	private int id;
	private int roleId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String username;
	private String password;
	
	public User(int id, int roleId, String firstName, String lastName, String email, String phone, String username, String password) {
		this.id=id;
		this.roleId=roleId;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.phone=phone;
		this.username=username;
		this.password=password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", roleId=" + roleId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phone=" + phone + ", username=" + username + ", password=" + password + "]";
	}

}
