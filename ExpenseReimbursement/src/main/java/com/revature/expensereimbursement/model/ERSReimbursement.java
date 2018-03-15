package com.revature.expensereimbursement.model;

import java.sql.Timestamp;

public class ERSReimbursement {
	int reimbursementId, authorId, resolverId,  typeId, statusId;
	double amount;
	String description, authorFirstName, authorLastName, resolverFirstName, resolverLastName, status, type, authorUsername;
	byte[] receipt;
	String receiptBase64;
	Timestamp submitted;
	Timestamp resolved;
	
	
	public ERSReimbursement(int reimbursementId, int authorId, int resolverId, int typeId, int statusId, double amount,
			String description, String authorFirstName, String authorLastName, String resolverFirstName,
			String resolverLastName, String status, byte [] receipt, Timestamp submitted, Timestamp resolved, 
			String type, String authorUsername) {
		super();
		this.reimbursementId = reimbursementId;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.typeId = typeId;
		this.statusId = statusId;
		this.amount = amount;
		this.description = description;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.resolverFirstName = resolverFirstName;
		this.resolverLastName = resolverLastName;
		this.status = status;
		this.receipt = receipt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.type=type;
		this.authorUsername = authorUsername;
	}
	public String getReceiptBase64() {
		return receiptBase64;
	}
	public void setReceiptBase64(String receiptBase64) {
		this.receiptBase64 = receiptBase64;
	}
	public ERSReimbursement(double amount, String description, byte [] receipt,
			Timestamp submitted, Timestamp resolved, int authorId, int resolverId, int typeId, int statusId) {
		this(0, amount, description, receipt, submitted, resolved, authorId, resolverId, typeId, statusId);
	}
	public ERSReimbursement(int reimbursementId, double amount, String description, byte [] receipt,
			Timestamp submitted, Timestamp resolved, int authorId, int resolverId, int typeId, int statusId) {
		super();
		this.reimbursementId = reimbursementId;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.typeId = typeId;
		this.statusId = statusId;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submitted = submitted;
		this.resolved = resolved;
	}
	//create reimbursment
	public ERSReimbursement(int authorId, double amount, String description, byte [] receipt, int typeId) {
		super();
		this.authorId = authorId;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submitted=new Timestamp(System.currentTimeMillis());
		this.typeId=typeId;
	}
	//update reimbursement
	public ERSReimbursement(int reimbursementId, int resolverId, int statusId) {
		super();
		this.reimbursementId = reimbursementId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.resolved=new Timestamp(System.currentTimeMillis());
	}
	
	public String getResolverFirstName() {
		return resolverFirstName;
	}
	public void setResolverFirstName(String resolverFirstName) {
		this.resolverFirstName = resolverFirstName;
	}
	public String getResolverLastName() {
		return resolverLastName;
	}
	public void setResolverLastName(String resolverLastName) {
		this.resolverLastName = resolverLastName;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public String getAuthorFirstName() {
		return authorFirstName;
	}
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	public String getAuthorLastName() {
		return authorLastName;
	}
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmployeeUsername() {
		return authorUsername;
	}
	public void setEmployeeUsername(String employeeUsername) {
		this.authorUsername = authorUsername;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
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
	public byte [] getReceipt() {
		return receipt;
	}
	public void setReceipt(byte [] receipt) {
		this.receipt = receipt;
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
	public int getReimbursementId() {
		return reimbursementId;
	}
	@Override
	public String toString() {
		return "ERSReimbursement [reimbursementId=" + reimbursementId + ", authorId=" + authorId + ", resolverId="
				+ resolverId + ", typeId=" + typeId + ", statusId=" + statusId + ", amount=" + amount + ", description="
				+ description + ", authorFirstName=" + authorFirstName + ", authorLastName=" + authorLastName
				+ ", resolverFirstName=" + resolverFirstName + ", resolverLastName=" + resolverLastName + ", status="
				+ status + ", type=" + type + ", authorUsername=" + authorUsername + ", receipt=" + receipt
				+ ", submitted=" + submitted + ", resolved=" + resolved + "]";
	}
}
