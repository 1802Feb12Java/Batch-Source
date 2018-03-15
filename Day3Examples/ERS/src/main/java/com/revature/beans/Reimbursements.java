package com.revature.beans;

import java.sql.Date;

public class Reimbursements {

	private int r_id;
	private double amount;
	private String description;
	private String receipt;
	private Date submitted;
	private Date resolved;
	private int u_id_author;
	private int u_id_resolver;
	private int rt_type;
	private int rt_status;
	private String type;
	
	
	
	public Reimbursements() {
		super();
	}

	public Reimbursements(int r_id, double amount, String description, String receipt, Date submitted, Date resolved,
			int u_id_author, int u_id_resolver, int rt_type, int rt_status) {
		super();
		this.r_id = r_id;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.u_id_author = u_id_author;
		this.u_id_resolver = u_id_resolver;
		this.rt_type = rt_type;
		this.rt_status = rt_status;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	public int getU_id_author() {
		return u_id_author;
	}

	public void setU_id_author(int u_id_author) {
		this.u_id_author = u_id_author;
	}

	public int getU_id_resolver() {
		return u_id_resolver;
	}

	public void setU_id_resolver(int u_id_resolver) {
		this.u_id_resolver = u_id_resolver;
	}

	public int getRt_type() {
		return rt_type;
	}

	public void setRt_type(int rt_type) {
		this.rt_type = rt_type;
	}

	public int getRt_status() {
		return rt_status;
	}

	public void setRt_status(int rt_status) {
		this.rt_status = rt_status;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [r_id= " + r_id + ", amount= " + amount + ", description= " + description + ", receipt= " + receipt 
				+ ", submitted= " + submitted + ", resolved= " + resolved + ", u_id_author= " + u_id_author + ", u_id_resolver=" +
				u_id_resolver + ", rt_type= " + rt_type + ", rt_status= " + rt_status + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
