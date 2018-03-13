package com.revature;

import java.sql.Blob;

public class Reimbursement {

	public int id;
	public double amount;
	public String description;
	public String timeSubmitted;
	public String timeResolved;
	public int author;
	public int resolver;
	public int type;
	public int status;
	public Blob receipt;
	
	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public Reimbursement(double amount, String description, int author, int type) {
		super();
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.type = type;
	}
	
	public Reimbursement(int id, double amount, String timeSubmitted, int author, int type, int status) {
		super();
		this.id = id;
		this.amount = amount;
		this.timeSubmitted = timeSubmitted;
		this.author = author;
		this.type = type;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeResolved() {
		return timeResolved;
	}

	public void setTimeResolved(String timeResolved) {
		this.timeResolved = timeResolved;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public String getTimeSubmitted() {
		return timeSubmitted;
	}

	public int getAuthor() {
		return author;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		if(status == 1) {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", timeSubmitted="
				+ timeSubmitted + ", timeResolved=" + timeResolved + ", author=" + author + ", resolver=" + resolver
				+ ", type=" + type + ", status=PENDING]";
		}else if(status == 2) {
			return "Reimbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", timeSubmitted="
					+ timeSubmitted + ", timeResolved=" + timeResolved + ", author=" + author + ", resolver=" + resolver
					+ ", type=" + type + ", status=APPROVED]";
		}else {
			return "Reimbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", timeSubmitted="
					+ timeSubmitted + ", timeResolved=" + timeResolved + ", author=" + author + ", resolver=" + resolver
					+ ", type=" + type + ", status=DENIED]";
		}
	}
	
	
	
}
