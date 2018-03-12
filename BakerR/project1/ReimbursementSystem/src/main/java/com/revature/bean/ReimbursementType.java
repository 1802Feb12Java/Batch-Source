package com.revature.bean;

public class ReimbursementType {
	private Integer id;
	private String type;
	
	public ReimbursementType(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	public ReimbursementType() {
		this(null, null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
