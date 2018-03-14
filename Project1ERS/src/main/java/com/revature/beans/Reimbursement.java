package com.revature.beans;

import java.sql.SQLException;
import java.util.Date;

public class Reimbursement {
	
	private int r_id;
	private double amount;
	private String description;
	private byte[] receipt; //image url
	private Date submitted;
	private Date resolved;
	private int author_id;
	private int resolver_id;
	private int type; // 
	private int status; // 0 - Pending; 1 - Approved; 2 - Denied
	
	
	public Reimbursement(double amount, String description, int author_id, int type, byte[] receipt) throws SQLException {
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submitted = com.revature.dao.ReimbursementDAOImp.getRequestSubmitDate(r_id);
		this.author_id = author_id;
		this.type = type;
		this.r_id = com.revature.dao.ReimbursementDAOImp.getReimbursementID();
		this.resolved = null;
		this.resolver_id = 0; //0 implied not yet resolved.
		this.status = 0;//Pending
		com.revature.dao.ReimbursementDAOImp.createReimbursementRequest(author_id, amount, description, type, receipt);
	}
	
	public Reimbursement(int r_id, double amount, String description, byte[] receipt, Date submitted, int author_id, int type, Date resolved, int u_resolver, int status) throws SQLException {
		//this is for viewing purposes
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submitted = submitted;
		this.author_id = author_id;
		this.type = type;
		this.r_id = r_id;
		this.resolved = resolved;
		this.resolver_id = u_resolver;
		this.status = status;
		
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


	public byte[] getReceipt() {
		return receipt;
	}


	public void setReceipt(byte[] receipt) throws SQLException {
		com.revature.dao.ReimbursementDAOImp.updateReceiptImage(this.r_id, receipt);
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


	public int getResolver_id() {
		return resolver_id;
	}


	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getR_id() {
		return r_id;
	}


	public int getAuthor_id() {
		return author_id;
	}
	
	
	
	
	
	
}//end class
