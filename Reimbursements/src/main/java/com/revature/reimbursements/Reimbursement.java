package com.revature.reimbursements;

import java.sql.Timestamp;

public class Reimbursement {
	private int r_id;
	private double r_amount;
	private String r_description;
	//private blob blob.--check what size sql defaults to. 
	private Timestamp r_submitted;
	private Timestamp r_resolved;
	private int u_id_author;
	private int u_id_resolver;
	private int rt_type;
	private int rt_status;
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public double getR_amount() {
		return r_amount;
	}
	public void setR_amount(double r_amount) {
		this.r_amount = r_amount;
	}
	public String getR_description() {
		return r_description;
	}
	public void setR_description(String r_description) {
		this.r_description = r_description;
	}
	public Timestamp getR_submitted() {
		return r_submitted;
	}
	public void setR_submitted(Timestamp r_submitted) {
		this.r_submitted = r_submitted;
	}
	public Timestamp getR_resolved() {
		return r_resolved;
	}
	public void setR_resolved(Timestamp r_resolved) {
		this.r_resolved = r_resolved;
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
	
	
	
	
	
}
