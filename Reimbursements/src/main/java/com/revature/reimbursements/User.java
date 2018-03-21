package com.revature.reimbursements;

public class User {
	
	private int U_id;
	private String U_username;
	private String U_password;
	private String U_firstname;
	private String U_lastname;
	private String U_email;
	private int Ur_id;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((U_email == null) ? 0 : U_email.hashCode());
		result = prime * result + ((U_firstname == null) ? 0 : U_firstname.hashCode());
		result = prime * result + U_id;
		result = prime * result + ((U_lastname == null) ? 0 : U_lastname.hashCode());
		result = prime * result + ((U_password == null) ? 0 : U_password.hashCode());
		result = prime * result + ((U_username == null) ? 0 : U_username.hashCode());
		result = prime * result + Ur_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (U_email == null) {
			if (other.U_email != null)
				return false;
		} else if (!U_email.equals(other.U_email))
			return false;
		if (U_firstname == null) {
			if (other.U_firstname != null)
				return false;
		} else if (!U_firstname.equals(other.U_firstname))
			return false;
		if (U_id != other.U_id)
			return false;
		if (U_lastname == null) {
			if (other.U_lastname != null)
				return false;
		} else if (!U_lastname.equals(other.U_lastname))
			return false;
		if (U_password == null) {
			if (other.U_password != null)
				return false;
		} else if (!U_password.equals(other.U_password))
			return false;
		if (U_username == null) {
			if (other.U_username != null)
				return false;
		} else if (!U_username.equals(other.U_username))
			return false;
		if (Ur_id != other.Ur_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [U_id=" + U_id + ", U_username=" + U_username + ", U_password=" + U_password + ", U_firstname="
				+ U_firstname + ", U_lastname=" + U_lastname + ", U_email=" + U_email + ", Ur_id=" + Ur_id + "]";
	}
	public User(int u_id, String u_username, String u_password, String u_firstname, String u_lastname, String u_email,
			int ur_id) {
		super();
		U_id = u_id;
		U_username = u_username;
		U_password = u_password;
		U_firstname = u_firstname;
		U_lastname = u_lastname;
		U_email = u_email;
		Ur_id = ur_id;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getU_id() {
		return U_id;
	}
	public void setU_id(int u_id) {
		U_id = u_id;
	}
	public String getU_username() {
		return U_username;
	}
	public void setU_username(String u_username) {
		U_username = u_username;
	}
	public String getU_password() {
		return U_password;
	}
	public void setU_password(String u_password) {
		U_password = u_password;
	}
	public String getU_firstname() {
		return U_firstname;
	}
	public void setU_firstname(String u_firstname) {
		U_firstname = u_firstname;
	}
	public String getU_lastname() {
		return U_lastname;
	}
	public void setU_lastname(String u_lastname) {
		U_lastname = u_lastname;
	}
	public String getU_email() {
		return U_email;
	}
	public void setU_email(String u_email) {
		U_email = u_email;
	}
	public int getUr_id() {
		return Ur_id;
	}
	public void setUr_id(int ur_id) {
		Ur_id = ur_id;
	}
	
	

}
