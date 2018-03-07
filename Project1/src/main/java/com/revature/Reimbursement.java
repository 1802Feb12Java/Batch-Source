package com.revature;

public class Reimbursement {

	public int id;
	public int amount;
	public String description;
	public String timeSubmitted;
	public String timeResolved;
	public int author;
	public int resolver;
	public int type;
	public int status;
	
	public Reimbursement(int id, int amount, String timeSubmitted, int author, int type, int status) {
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

	public int getAmount() {
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
		return "Reimbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", timeSubmitted="
				+ timeSubmitted + ", timeResolved=" + timeResolved + ", author=" + author + ", resolver=" + resolver
				+ ", type=" + type + ", status=" + status + "]";
	}
	
	
	
}
