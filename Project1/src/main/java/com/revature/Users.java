package com.revature;

public class Users {
	
	public int id;
	public String username;
	public String password;
	public String fName;
	public String lName;
	public String email;
	public int roleID;
	
	public Users(int id, String username, String password, String fName, String lName, String email, int roleID) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.roleID = roleID;
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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public int getRoleID() {
		return roleID;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", fName=" + fName + ", lName="
				+ lName + ", email=" + email + ", roleID=" + roleID + "]";
	}
	
}
