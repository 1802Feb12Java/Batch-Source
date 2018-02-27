package com.revature.bankapp.model;

public class BankAdmin {
	private int adminID;
	private String adminUsername;
	private String adminPW;

	
	public BankAdmin() {}
	
	/**
	 * @param adminID
	 * @param adminUsername
	 * @param adminPW
	 * @param notes
	 * @param dateEntered
	 */
	public BankAdmin(int adminID, String adminUsername, String adminPW) {
		this.adminID = adminID;
		this.adminUsername = adminUsername;
		this.adminPW = adminPW;

	}
	/**
	 * @return the adminID
	 */
	public int getAdminID() {
		return adminID;
	}
	/**
	 * @param adminID the adminID to set
	 */
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	/**
	 * @return the adminUsername
	 */
	public String getAdminUsername() {
		return adminUsername;
	}
	/**
	 * @param adminUsername the adminUsername to set
	 */
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	/**
	 * @return the adminPW
	 */
	public String getAdminPW() {
		return adminPW;
	}
	/**
	 * @param adminPW the adminPW to set
	 */
	public void setAdminPW(String adminPW) {
		this.adminPW = adminPW;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BankAdmin [adminID=" + adminID + ", adminUsername=" + adminUsername + ", adminPW=" + adminPW + "]";
	}
	
	
}
