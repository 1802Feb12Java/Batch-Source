package com.revature.expensereimbursement.model;

public class ERSReimbursementType {
	int id;
	String type;
	public ERSReimbursementType(String type) {
		this(0, type);
	}
	public ERSReimbursementType(int id, String type) {
		this.id = id;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
