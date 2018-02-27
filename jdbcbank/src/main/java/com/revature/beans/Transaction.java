package com.revature.beans;

import java.math.BigDecimal;
import java.sql.Date;

public class Transaction {
	private int transactionId;
	private int accountIdFrom;
	private int accountIdTo;
	private BigDecimal amount;
	private Date dateCreated;

	public Transaction() {
	}

	public Transaction(int transactionId, int accountIdFrom, int accountIdTo, BigDecimal amount, Date dateCreated) {
		super();
		this.transactionId = transactionId;
		this.accountIdFrom = accountIdFrom;
		this.accountIdTo = accountIdTo;
		this.amount = amount;
		this.dateCreated = dateCreated;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountIdFrom() {
		return accountIdFrom;
	}

	public void setAccountIdFrom(int accountIdFrom) {
		this.accountIdFrom = accountIdFrom;
	}

	public int getAccountIdTo() {
		return accountIdTo;
	}

	public void setAccountIdTo(int accountIdTo) {
		this.accountIdTo = accountIdTo;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountIdFrom=" + accountIdFrom + ", accountIdTo="
				+ accountIdTo + ", amount=" + amount + ", dateCreated=" + dateCreated + "]";
	}

}
