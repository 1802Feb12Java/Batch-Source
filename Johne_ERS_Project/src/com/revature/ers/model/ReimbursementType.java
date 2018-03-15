package com.revature.ers.model;

public class ReimbursementType {
	private int rt_id;
	private String rt_type;
	
	public ReimbursementType() {}

	/**
	 * @param rt_id
	 * @param rt_type
	 */
	public ReimbursementType(int rt_id, String rt_type) {
		this.rt_id = rt_id;
		this.rt_type = rt_type;
	}

	/**
	 * @return the rt_id
	 */
	public int getRt_id() {
		return rt_id;
	}

	/**
	 * @param rt_id the rt_id to set
	 */
	public void setRt_id(int rt_id) {
		this.rt_id = rt_id;
	}

	/**
	 * @return the rt_type
	 */
	public String getRt_type() {
		return rt_type;
	}

	/**
	 * @param rt_type the rt_type to set
	 */
	public void setRt_type(String rt_type) {
		this.rt_type = rt_type;
	}

	@Override
	public String toString() {
		return "ERSReimbursementType [rt_id=" + rt_id + ", rt_type=" + rt_type + "]";
	}
	
}
