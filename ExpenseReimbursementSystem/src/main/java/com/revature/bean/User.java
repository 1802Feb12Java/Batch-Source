package com.revature.bean;

/*
 * User Bean
 * stores all relevant information about the user
 * roles should be converted(look up) from the db and stored as a string
 */

public class User {
	//members
	private int userID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	
	//default const
	public User(){
		this.userID = 0;
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.role = "";
	}

	//copy Constructor
	public User(User u){
		this.userID = u.userID;
		this.username = u.username;
		this.password = u.password;
		this.firstName = u.firstName;
		this.lastName = u.lastName;
		this.email = u.email;
		this.role = u.role;
	}

	//parm Constructor
	public User(int uID, String usr, String pw, String fname, String lname, String email, String role){
		this.userID = uID;
		this.username = usr;
		this.password = pw;
		this.firstName = fname;
		this.lastName = lname;
		this.email = email;
		this.role = role;
	}

	//Getters
	public int getUserID(){
		return this.userID;
	}
	
	public String getUsername(){
		return this.username;
	}

	public String getPassword(){
		return this.password;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public String getLastName(){
		return this.lastName;
	}

	public String getEmail(){
		return this.email;
	}

	public String getRole(){
		return this.role;
	}
	

	//Setters
	public void setUserID(int newID){
		this.userID = newID;
	}

	public void setUsername(String newUsername){
		this.username = newUsername;
	}

	public void setPassword(String newPassword){
		this.password = newPassword;
	}

	public void setFirstName(String newFirstName){
		this.firstName = newFirstName;
	}

	public void setLastName(String newLastName){
		this.lastName = newLastName;
	}

	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	public void setRole(String newRole) {
		this.role = newRole;
	}

	//ToString override
	@Override
	public String toString() {
		StringBuilder s  = new StringBuilder(
							"User ID:\t" + Integer.toString(this.userID) + 
							"Username:\t" + this.username +
							"Password:\t" + this.password + 
							"First Name:\t" + this.firstName + 
							"Last Name:\t" + this.lastName+
							"Email:\t" + this.email + 
							"Role:\t" + this.role);
		return s.toString();
	}

}
