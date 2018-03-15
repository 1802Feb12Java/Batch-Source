package com.revature.ers.model;

public class User_Roles {
	private int ur_id;
	private String ur_role;
	
	public User_Roles() {}
	/**
	 * @param ur_id
	 * @param ur_role
	 */
	public User_Roles(int ur_id, String ur_role) {
		this.ur_id = ur_id;
		this.ur_role = ur_role;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [ur_id=" + ur_id + ", ur_role=" + ur_role + "]";
	}
	
}
