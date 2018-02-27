package com.revature.banking.jdbc.model;

import java.io.Serializable;
/**
 * 
 * @author gkigu
 *
 */
public class Account implements Serializable{
	private static final long serialVersionUID = 99L;
	private int accountId, primaryCustomerId, secondaryCustomerId, applicationId;
	private double balance;
	private String status;
	/**
	 * 
	 * @param accountId
	 * @param primaryCustomerId
	 * @param applicationId
	 * @param status
	 */
	public Account(int accountId, int primaryCustomerId,  int applicationId, String status) {
		this(accountId, primaryCustomerId, 0, applicationId, status);
	}	
	public Account(int accountId, int primaryCustomerId, int secondaryCustomerId,  int applicationId,String status) {
		this(accountId, primaryCustomerId, secondaryCustomerId, applicationId, status, 0);
	}
	public Account(int accountId, int primaryCustomerId, int secondaryCustomerId, int applicationId, String status, double balance) {
		super();
		this.accountId = accountId;
		this.primaryCustomerId = primaryCustomerId;
		this.secondaryCustomerId = secondaryCustomerId;
		this.status=status;
		this.balance = balance;
		this.applicationId=applicationId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId=applicationId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId=accountId;
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
		this.status = "canceled by " + admin.getUsername() + ":" + admin.getUserId();
	}
	public void close(int customerId) {
		this.status = "closed by " + customerId;
	}
	@Override
	public String toString() {
		if (secondaryCustomerId !=0)
			return "Account [AccountId=" + accountId +", Balance=" + balance + ", Status=" + status +
				", PrimaryCustomerId=" + primaryCustomerId
				+ ", SecondaryCustomerId=" + secondaryCustomerId +  "]";
		else
			return "Account [AccountId=" + accountId +", Balance=" + balance + ", Status=" + status +
					", Primary Customer=" + primaryCustomerId + "]";
	}
	
}
