package com.revature.beans;

import java.math.BigDecimal;
import java.sql.Date;

public class BankAccount {

	private int accountId;
	private BigDecimal balance;
	private java.sql.Date dateCreated;

	public BankAccount() {
	}

	public BankAccount(int accountId, BigDecimal balance, Date dateCreated) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.dateCreated = dateCreated;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public java.sql.Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(java.sql.Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", balance=" + balance + ", dateCreated=" + dateCreated + "]";
	}

}
