package com.revature.beans;

import java.sql.Date;

/**
 * User POJO
 */
public class User {

	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Date birthdate;
	private boolean isSuper;
	private Date dateCreated;

	public User() {
		isSuper = false;
	}

	public User(int userId, String username, String password, String firstName, String lastName, Date birthdate,
			boolean isSuper, Date dateCreated) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.isSuper = isSuper;
		this.dateCreated = dateCreated;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date date) {
		this.birthdate = date;
	}

	public boolean isSuper() {
		return isSuper;
	}

	public int getSuper() {
		return isSuper ? 1 : 0;
	}

	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}

	public void setSuper(int isSuper) {
		this.isSuper = (isSuper == 1);
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof User))
			return false;

		User u = (User) o;

		return userId == u.getUserId() && firstName.equals(u.getFirstName()) && lastName.equals(u.getLastName())
				&& (birthdate == null && u.getBirthdate() == null)
				|| birthdate.equals(u.getBirthdate()) && isSuper == u.isSuper();
		// TODO date created compare?

	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthdate=" + birthdate + ", isSuper=" + isSuper
				+ ", dateCreated=" + dateCreated + "]";
	}

}
