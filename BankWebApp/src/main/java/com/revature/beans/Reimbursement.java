package com.revature.beans;

public class Reimbursement {
	
	private int id;
	private double amount;
	private String description;
	private String receipt;
	private String submittedTime;
	private String resolvedTime;
	private int authorId;
	private int resolverId;
	private int typeId;
	private int statusId;
	
	public Reimbursement(int id, double amount, String description, String receipt, String submittedTime,
			String resolvedTime, int authorId, int resolverId, int typeId, int statusId) {
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submittedTime = submittedTime;
		this.resolvedTime = resolvedTime;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.typeId = typeId;
		this.statusId = statusId;
	}
	
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
	
	public String getReceipt() {
		return receipt;
	}
	
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	
	public String getSubmittedTime() {
		return submittedTime;
	}
	
	public void setSubmittedTime(String submittedTime) {
		this.submittedTime = submittedTime;
	}
	
	public String getResolvedTime() {
		return resolvedTime;
	}
	
	public void setResolvedTime(String resolvedTime) {
		this.resolvedTime = resolvedTime;
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

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", receipt="
				+ receipt + ", submittedTime=" + submittedTime + ", resolvedTime=" + resolvedTime + ", authorId="
				+ authorId + ", resolverId=" + resolverId + ", typeId=" + typeId + ", statusId=" + statusId + "]";
	}

}
