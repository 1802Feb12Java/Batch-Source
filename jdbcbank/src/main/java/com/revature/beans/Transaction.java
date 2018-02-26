package com.revature.beans;

import java.math.BigDecimal;

import oracle.sql.DATE;

public class Transaction {
	private int transactionId;
	private BigDecimal amount;
	private DATE dateCreated;

	public Transaction() {
	}

	public Transaction(BigDecimal amount, DATE dateMade) {
		super();
		this.amount = amount;
		this.dateCreated = dateMade;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public DATE getDateMade() {
		return dateCreated;
	}

	public void setDateMade(DATE dateMade) {
		this.dateCreated = dateMade;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", dateMade=" + dateCreated + "]";
	}

}
