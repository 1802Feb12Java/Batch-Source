package com.revature.banking.model;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = 99L;
	private final int accountId;
	private double balance;
	private String status;
	private int primaryCustomerId, secondaryCustomerId;
	public Account(int accountId, int primaryCustomerId, String status) {
		this(accountId, primaryCustomerId, 0, status);
	}	
	public Account(int accountId, int primaryCustomerId, int secondaryCustomerId, String status) {
		super();
		this.accountId = accountId;
		this.primaryCustomerId = primaryCustomerId;
		this.secondaryCustomerId = secondaryCustomerId;
		this.status=status;
	}
	public int getAccountId() {
		return accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void withdraw(double amount) {
		this.balance -= amount;
	}
	public void deposit(double amount) {
		this.balance += amount;
	}
	public int getPrimaryCustomerId() {
		return primaryCustomerId;
	}
	public int getSecondaryCustomerId() {
		return secondaryCustomerId;
	}
	public String getStatus() {
		return status;
	}
	public void cancel(Admin admin) {
		this.status = "canceled by " + admin.getUserId();
	}
	public void close(int customerId) {
		this.status = "closed by " + customerId;
	}
	@Override
	public String toString() {
		if (secondaryCustomerId !=0)
			return "Account \n[\tAccountNumber=" + accountId +", \n\tbalance=" + balance + ", \n\tstatus=" + status +
				", \n\tprimary Customer=" + primaryCustomerId
				+ ", \n\tsecondary Customer=" + secondaryCustomerId +  "\n]";
		else
			return "Account \n[\tAccountNumber=" + accountId +", \n\tbalance=" + balance + ", \n\tstatus=" + status +
					", \n\tprimary Customer=" + primaryCustomerId
					+   "\n]";
	}
	
}
