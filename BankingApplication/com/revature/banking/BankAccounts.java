package com.revature.banking;

import java.util.ArrayList;

import com.revature.database.DataStore;

/**
 * Contains methods for modifying the BankAccounts "Table" in the text file
 */
public class BankAccounts {
	// private static final Logger logger =
	// LogManager.getLogger(BankAccounts.class);

	public static BankAccount findAccount(String accountNumber) {
		ArrayList<BankAccount> bankAccounts = DataStore.readBankAccountsFromFile();
		for (BankAccount b : bankAccounts)
			if (b.getAccountId().equals(accountNumber)) {
				// logger.info("Found account matching number " + accountNumber);
				return b;
			}
		// logger.error("Was unable to find account with the number " + accountNumber);
		return null;
	}

	public static void addAccountToFile(BankAccount b) {
		ArrayList<BankAccount> accounts = DataStore.readBankAccountsFromFile();
		if (accounts == null)
			accounts = new ArrayList<BankAccount>();
		accounts.add(b);
		DataStore.writeBankAccountsToFile(accounts);
	}

	public static boolean removeAccountFromFile(String accountId) {

		ArrayList<BankAccount> accounts = DataStore.readBankAccountsFromFile();
		int reqSize = accounts.size();
		for (int i = 0; i < reqSize; i++) {
			if (accounts.get(i).getAccountId().equals(accountId)) {
				accounts.remove(i);
				// logger.info("Removed bank account with id " + accountId + " from file");
				DataStore.writeBankAccountsToFile(accounts);
			}
		}
		if (accounts.size() == reqSize) {
			// logger.info("No Request matched " + accountId + " and, so none were removed
			// from file");
			return false;
		}
		return true;

	}

	public static boolean updateAccountFromFile(BankAccount b) {
		ArrayList<BankAccount> accounts = DataStore.readBankAccountsFromFile();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountId().equals(b.getAccountId())) {
				accounts.remove(i);
				accounts.add(b);
				DataStore.writeBankAccountsToFile(accounts);
				return true;
			}
		}
		return false;
	}

}
