package com.revature.ers.model;

public class ReimbursementStatus {
	private int rs_id;
	private String rs_status;
	
	
	public ReimbursementStatus() {}


	/**
	 * @param rs_id
	 * @param rs_status
	 */
	public ReimbursementStatus(int rs_id, String rs_status) {
		this.rs_id = rs_id;
		this.rs_status = rs_status;
	}


	/**
	 * @return the rs_id
	 */
	public int getRs_id() {
		return rs_id;
	}


	/**
	 * @param rs_id the rs_id to set
	 */
	public void setRs_id(int rs_id) {
		this.rs_id = rs_id;
	}


	/**
	 * @return the rs_status
	 */
	public String getRs_status() {
		return rs_status;
	}


	/**
	 * @param rs_status the rs_status to set
	 */
	public void setRs_status(String rs_status) {
		this.rs_status = rs_status;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ERSReimbursementStatus [rs_id=" + rs_id + ", rs_status=" + rs_status + "]";
	}
	
	
}
