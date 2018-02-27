package com.revature;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.BankAccount;

/**
 * Class to withdraw, deposit, transfer amounts from a bank account
 * 
 * and find bank accounts
 *
 */
public class BankAccountOps {

	private static final Logger logger = LogManager.getLogger(BankAccount.class);
	private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("##.00");

	public static boolean withdrawable(BigDecimal amount, BankAccount ba) {

		// check if amount is negative
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			// logger.error("Unable to withdraw a negative amount");
			return false;
		}
		// check if account has enough balance to withdraw the amount
		if (ba.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
			return true;
		}
		return false;
	}

	public static boolean depositable(String amount) {
		return depositable(new BigDecimal(amount));
	}

	public static boolean depositable(BigDecimal amount) {
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

	public boolean transfer(Double amount, String accountName) {
		// // find matching account and withdraw/deposit respectivley
		// return transfer(new BigDecimal(amount),
		// BankAccounts.findAccount(accountName));
		return false;
	}

	private boolean transfer(BigDecimal amount, BankAccountOps recipientAccount) {
		// if (withdraw(amount))
		// return recipientAccount.deposit(amount);
		// return false;
		return false;
	}

}
