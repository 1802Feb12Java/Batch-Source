package com.revature.BankingAppPt2;

public class BankAccount {
	private int accountId;
	private String accountType;
	private int accountBalance;
	private String approvalStatus;
	
	public BankAccount(int accountId, String accountType, int accountBalance, String approvalStatus) {
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
		case "D":
			approval = "Denied";
		default:
			break;
		}
		return "Bank Account Number: " + accountId + "\nAccountType: " + accountType + "\nAccount Balance: "
				+ accountBalance + "\nApproval Status: " + approval;
	}
}
