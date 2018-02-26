package com.revature.BankingAppPt2;

public class BankAccount {
	private int accountId;
	private String accountType;
	private float accountBalance;
	private String approvalStatus;
	
	public BankAccount(int accountId, String accountType, float accountBalance, String approvalStatus) {
		this.accountId = accountId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.approvalStatus = approvalStatus;
	}

	@Override
	public String toString() {
		String approval = "";
		switch (this.approvalStatus) {
		case "P":
			approval = "Pending";
			break;
		case "A":
			approval = "Approved";
			break;
		case "D":
			approval = "Denied";
		default:
			break;
		}
		return "Bank Account Number: " + accountId + "\nAccountType: " + accountType + "\nAccount Balance: $"
				+ accountBalance + "\nApproval Status: " + approval;
	}
	
	public int getAccountId() {
		return this.accountId;
	}
	
	public String getApprovalStatus() {
		return this.approvalStatus;
	}
	
	public float getBalance() {
		return this.accountBalance;
	}
}
