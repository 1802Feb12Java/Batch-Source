package com.revature.bean;

import java.io.Serializable;

public class ReimbursementType implements Serializable {
	private static final long serialVersionUID = -7476468634913717337L;
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

	@Override
	public String toString() {
		return "ReimbursementType [id=" + id + ", type=" + type + "]";
	}
	
	
}
