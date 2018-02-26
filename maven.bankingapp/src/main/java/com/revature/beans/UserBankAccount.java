package com.revature.beans;

public class UserBankAccount {
	
	private int userId;
	private int accountId;
	
	public UserBankAccount(int userId, int accountId) {
		this.userId=userId;
		this.accountId=accountId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "UserBankAccount [userId=" + userId + ", accountId=" + accountId + "]";
	}

}
