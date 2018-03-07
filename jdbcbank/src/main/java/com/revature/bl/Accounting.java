package com.revature.bl;

import java.math.BigDecimal;

import com.revature.beans.BankAccount;
import com.revature.beans.Transaction;

public class Accounting {
	public static boolean createDeposit(BigDecimal amount, BankAccount ba, int userId) {

		// first validate amount
		if (!depositable(amount))
			return false;

		// make a transaction for deposit
		Transaction t = new Transaction(0, 0, ba.getAccountId(), amount, null);
		// put transaction into database
		Transactions.addTransaction(t);

		// update the bank account
		ba.setBalance(ba.getBalance().add(amount));

		if (!Accounts.updateBankAccount(ba)) {
			return false;
			// TODO not updated, remove transaction
		}
		return true;
	}

	public static boolean createWithdraw(BigDecimal amount, BankAccount ba, int userId) {

		// first validate amount
		if (!withdrawable(amount, ba))
			return false;

		// make a transaction for deposit
		Transaction t = new Transaction(0, ba.getAccountId(), 0, amount, null);
		// put transaction into database
		Transactions.addTransaction(t);

		// update the bank account
		ba.setBalance(ba.getBalance().subtract(amount));

		if (!Accounts.updateBankAccount(ba)) {
			return false;
			// TODO not updated, remove transaction
		}
		return true;
	}

	private static boolean withdrawable(BigDecimal amount, BankAccount ba) {
		// check if amount is negative
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			// logger.error("Unable to withdraw a negative amount");
			return false;
		}
		// check if account has enough balance to withdraw the amount
		if (ba.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
			// logger.info("Withdrew " + amount + " from account");
			return true;
		}
		return false;
	}

	private static boolean depositable(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			// logger.error("Unable to deposit a negative amount");
			return false;
		}
		// Set deposit limit to 500,000,000
		if ((amount.subtract(new BigDecimal(500000000.0)).compareTo(BigDecimal.ZERO) > 0)) {
			// logger.error("Unable to deposit over 500,000,000.00 Dollars");
			return false;
		}
		// logger.info("Deposited " + amount);
		return true;
	}

}
