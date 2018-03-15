package com.revature.ers.model;

public class Users {
	private int u_id;
	private String u_username;
	private String u_password;
	private String u_firstName;
	private String u_lastName;
	private String u_email;
	private int ur_id;
	
	private String ur_role;
	
	public Users() {}
	
	
	
	/**
	 * @param u_id
	 * @param u_username
	 * @param u_password
	 * @param u_firstName
	 * @param u_lastName
	 * @param u_email
	 * @param ur_id
	 * @param ur_role
	 */
	public Users(int u_id, String u_username, String u_password, String u_firstName, String u_lastName, String u_email, String ur_role) {
		this.u_id = u_id;
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_firstName = u_firstName;
		this.u_lastName = u_lastName;
		this.u_email = u_email;
		this.ur_role = ur_role;
	}

	/**
	 * @param u_id
	 * @param u_username
	 * @param u_password
	 * @param u_firstName
	 * @param u_lastName
	 * @param u_email
	 * @param ur_id
	 */
	public Users(int u_id, String u_username, String u_password, String u_firstName, String u_lastName, String u_email,
			int ur_id) {
		this.u_id = u_id;
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_firstName = u_firstName;
		this.u_lastName = u_lastName;
		this.u_email = u_email;
		this.ur_id = ur_id;
	}
	
	/**
	 * @return the u_id
	 */
	public int getU_id() {
		return u_id;
	}
	/**
	 * @param u_id the u_id to set
	 */
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	/**
	 * @return the u_username
	 */
	public String getU_username() {
		return u_username;
	}
	/**
	 * @param u_username the u_username to set
	 */
	public void setU_username(String u_username) {
		this.u_username = u_username;
	}
	/**
	 * @return the u_password
	 */
	public String getU_password() {
		return u_password;
	}
	/**
	 * @param u_password the u_password to set
	 */
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	/**
	 * @return the u_firstName
	 */
	public String getU_firstName() {
		return u_firstName;
	}
	/**
	 * @param u_firstName the u_firstName to set
	 */
	public void setU_firstName(String u_firstName) {
		this.u_firstName = u_firstName;
	}
	/**
	 * @return the u_lastName
	 */
	public String getU_lastName() {
		return u_lastName;
	}
	/**
	 * @param u_lastName the u_lastName to set
	 */
	public void setU_lastName(String u_lastName) {
		this.u_lastName = u_lastName;
	}
	/**
	 * @return the u_email
	 */
	public String getU_email() {
		return u_email;
	}
	/**
	 * @param u_email the u_email to set
	 */
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	/**
	 * @return the ur_id
	 */
	public int getUr_id() {
		return ur_id;
	}
	/**
	 * @param ur_id the ur_id to set
	 */
	public void setUr_id(int ur_id) {
		this.ur_id = ur_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Users [u_id=" + u_id + ", u_username=" + u_username + ", u_password=" + u_password + ", u_firstName="
				+ u_firstName + ", u_lastName=" + u_lastName + ", u_email=" + u_email + ", ur_id=" + ur_id + "]";
	}



	/**
	 * @return the ur_role
	 */
	public String getUr_role() {
		return ur_role;
	}



	/**
	 * @param ur_role the ur_role to set
	 */
	public void setUr_role(String ur_role) {
		this.ur_role = ur_role;
	}
	
}
