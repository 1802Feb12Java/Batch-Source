package com.revature.banking;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.revature.database.DataStore;

/**
 * Class to withdraw, deposit, transfer amounts from a bank account
 * 
 * and find bank accounts
 *
 */
public class BankAccount implements Transactional<Double> {

	// private static final Logger logger = LogManager.getLogger(BankAccount.class);
	private static final long serialVersionUID = 3L;
	private BigDecimal balance;
	private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("##.00");

	// PK
	private final String accountId = DataStore.generateUID();

	public BankAccount(Double balance) {
		this(new BigDecimal(balance));
	}

	private BankAccount(BigDecimal balance) {
		super();
		this.balance = balance;
		// logger.info("Created a bank account with balance " + balance);
	}

	public boolean withdraw(Double amount) {
		return withdraw(new BigDecimal(amount));
	}

	private boolean withdraw(BigDecimal amount) {
		// check if amount is negative
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			// logger.error("Unable to withdraw a negative amount");
			return false;
		}
		// check if account has enough balance to withdraw the amount
		if (balance.subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
			balance = balance.subtract(amount);
			// logger.info("Withdrew " + amount + " from account");
			return true;
		}
		return false;
	}

	public boolean deposit(Double amount) {
		return deposit(new BigDecimal(amount));
	}

	private boolean deposit(BigDecimal amount) {
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
		balance = balance.add(amount);
		return true;
	}

	public boolean transfer(Double amount, String accountName) {
		// find matching account and withdraw/deposit respectivley
		return transfer(new BigDecimal(amount), BankAccounts.findAccount(accountName));
	}

	private boolean transfer(BigDecimal amount, BankAccount recipientAccount) {
		if (withdraw(amount))
			return recipientAccount.deposit(amount);
		return false;
	}

	public Double getBalance() {
		// format back to two decimal places
		return Double.parseDouble(MONEY_FORMAT.format(balance.doubleValue()));
	}

	public void setBalance(Double balance) {
		setBalance(new BigDecimal(balance));
	}

	private void setBalance(BigDecimal balance) {
		// logger.info("Set account balance from " + getBalance() + " to " + balance);
		this.balance = balance;
	}

	public String getAccountId() {
		return accountId;
	}

	public void closeAccount() {
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() {
		// logger.info("Closed account " + toString());
		balance = null;
		System.gc();
	}

	@Override
	public String toString() {
		return "BankAccount [balance=" + balance + ", accountId=" + accountId + "]";
	}

}
