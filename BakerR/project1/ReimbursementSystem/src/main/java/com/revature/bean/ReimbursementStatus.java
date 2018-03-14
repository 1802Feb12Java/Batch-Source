package com.revature.bean;

import java.io.Serializable;

public class ReimbursementStatus implements Serializable {
	private static final long serialVersionUID = -4408380430117239578L;
	private Integer id;
	private String status;
	
	public ReimbursementStatus(Integer id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	public ReimbursementStatus() {
		this(null, null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [id=" + id + ", status=" + status + "]";
	}
	
	
}
