package com.revature.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Reimbursement {

	private int reimbursementId;
	private BigDecimal amount;
	private String decription;
	// private Blob recipt;
	private byte[] recipt;
	private Timestamp submitted;
	private Timestamp resolved;
	private int authorId;
	private int resolverId;
	private String type;
	private String status;

	public Reimbursement() {

	}

	public Reimbursement(int reimbursementId, BigDecimal amount, String decription, byte[] recipt, Timestamp submitted,
			Timestamp resolved, int authorId, int resolverId, String type, String status) {
		super();
		this.reimbursementId = reimbursementId;
		this.amount = amount;
		this.decription = decription;
		this.recipt = recipt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.type = type;
		this.status = status;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public byte[] getRecipt() {
		return recipt;
	}

	public void setRecipt(byte[] recipt) {
		this.recipt = recipt;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", amount=" + amount + ", decription=" + decription
				+ ", recipt=" + recipt + ", submitted=" + submitted + ", resolved=" + resolved + ", authorId="
				+ authorId + ", resolverId=" + resolverId + ", type=" + type + ", status=" + status + "]";
	}

}
