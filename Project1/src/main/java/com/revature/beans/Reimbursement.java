package com.revature.beans;

import java.sql.Blob;
import java.sql.Timestamp;

public class Reimbursement {
	private int id;
	private double amount;
	private String description;
	private Blob receipt;
	private String receiptPath;
	private Timestamp submitted;
	private Timestamp resolved;
	private int authorId;
	private int resolverId;
	private int typeId;
	private int statusID;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Blob getReceipt() {
		return receipt;
	}
	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}
	public String getReceiptPath() {
		return receiptPath;
	}
	public void setReceiptPath(String receiptPath) {
		this.receiptPath = receiptPath;
	}
	public Timestamp getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}
	public Timestamp getResolved() {
		return resolved;
	}
	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	
	public Reimbursement(int id, double amount, String description, Blob receipt, Timestamp submitted,
			Timestamp resolved, int authorId, int resolverId, int typeId, int statusID) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.typeId = typeId;
		this.statusID = statusID;
	}
	
	//for calling to make a new reimbursement to put in database
	public Reimbursement(double amount, String description, Blob receipt, String receiptPath, int authorId, int typeId, int statusID) {
		super();
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.receiptPath = receiptPath;
		this.authorId = authorId;
		this.typeId = typeId;
		this.statusID = statusID;
	}
}
