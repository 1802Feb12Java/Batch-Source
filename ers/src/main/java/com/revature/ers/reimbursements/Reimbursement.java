package com.revature.ers.reimbursements;

import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Reimbursement {
	private int r_id;
	private double r_amount;
	private String r_description;
	private Blob r_receipt = null;
	private Timestamp r_submitted = Timestamp.valueOf(LocalDateTime.now());
	private Timestamp r_resolved = null;
	private int u_ID_Author;
	private int u_ID_Resolver;
	private int rt_Type;
	private int rt_Status = 1;
	
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
	public Blob getR_receipt() {
		return r_receipt;
	}
	public void setR_receipt(Blob r_receipt) {
		this.r_receipt = r_receipt;
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
	public int getU_ID_Author() {
		return u_ID_Author;
	}
	public void setU_ID_Author(int u_ID_Author) {
		this.u_ID_Author = u_ID_Author;
	}
	public int getU_ID_Resolver() {
		return u_ID_Resolver;
	}
	public void setU_ID_Resolver(int u_ID_Resolver) {
		this.u_ID_Resolver = u_ID_Resolver;
	}
	public int getRt_Type() {
		return rt_Type;
	}
	public void setRt_Type(int rt_Type) {
		this.rt_Type = rt_Type;
	}
	public int getRt_Status() {
		return rt_Status;
	}
	public void setRt_Status(int rt_Status) {
		this.rt_Status = rt_Status;
	}	
}
