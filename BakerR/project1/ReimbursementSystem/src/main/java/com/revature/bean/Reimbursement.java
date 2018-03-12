package com.revature.bean;

import java.time.LocalDateTime;

public class Reimbursement {
	private Integer id;
	private Double amount;
	private String description;
	private byte[] receipt;
	private LocalDateTime submitted;
	private LocalDateTime resolved;
	private User author;
	private User resolver;
	private ReimbursementType type;
	private ReimbursementStatus status;
	public Reimbursement(Integer id, Double amount, String description, byte[] receipt, LocalDateTime submitted,
			LocalDateTime resolved, User author, User resolver, ReimbursementType type, ReimbursementStatus status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.author = author;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
	}
	
	public Reimbursement() {
		this(null, null, null, null, null, null, null, null, null, null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
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

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public LocalDateTime getSubmitted() {
		return submitted;
	}

	public void setSubmitted(LocalDateTime submitted) {
		this.submitted = submitted;
	}

	public LocalDateTime getResolved() {
		return resolved;
	}

	public void setResolved(LocalDateTime resolved) {
		this.resolved = resolved;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}
}
