package com.revature.bankingapp;

import java.io.File;

public class AccountChecker {
	public boolean canDoTransactions(String account) {
		String[] accountInfo = account.split(" ");
		if (accountInfo[4].equals("APPROVED"))
			return true;
		else
			return false;
	}
	
	public boolean isJointAccount(String account) {
		String[] accountInfo = account.split(" ");
		if (accountInfo[1].contains("joint"))
			return true;
		else
			return false;
	}
	
	public File getJointOwner(String account) {
		File file = null;
		if (isJointAccount(account)) {
			String[] accountInfo = account.split(" ");
			String jointOwner = (accountInfo[2].split("-"))[1];
			file = new File("./customerAccounts/" + jointOwner);
		}
		return file;

	}
}
