package com.revature.bl;

import java.math.BigDecimal;

import com.revature.BankAccountOps;
import com.revature.beans.BankAccount;
import com.revature.beans.Transaction;

public class Accounting {
	public static boolean createDeposit(BigDecimal amount, BankAccount ba, int userId) {

		// first validate amount
		if (!BankAccountOps.depositable(amount))
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
		if (!BankAccountOps.withdrawable(amount, ba))
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

}
