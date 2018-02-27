package com.revature.bl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.Transaction;
import com.revature.dao.impl.TransactionDaoImpl;
import com.revature.dao.impl.UtilDaoImpl;

public class Transactions {
	private static final Logger logger = LogManager.getLogger(Transactions.class);

	private static TransactionDaoImpl transDao = new TransactionDaoImpl();

	public static boolean addTransaction(Transaction transaction) {
		try {
			// put in user PK
			// get next sequence id
			int seqNum = Util.getNextSequence(UtilDaoImpl.TRANSACTION_SEQ);
			logger.debug("Got seq num: " + seqNum + " from sequence: " + UtilDaoImpl.TRANSACTION_SEQ);
			// update user obj's PK if default
			if (transaction.getTransactionId() == 0)
				transaction.setTransactionId(seqNum);

			transDao.addTransaction(transaction);

		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while adding transaction " + transaction);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static Transaction getTransaction(int transactionId) {
		try {
			return transDao.getTransaction(transactionId);

		} catch (SQLException e) {
			// log for now
			logger.error("No Transaction Exists with the ID " + transactionId);
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

	public static List<Transaction> getTransactions(int accountId) {
		try {
			return transDao.getAllTransactions(accountId);

		} catch (SQLException e) {
			// log for now
			logger.error("No Transactions Found with the USER ID " + accountId);
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

	public static boolean deleteTransaction(int transactionId) {
		try {
			transDao.deleteTransaction(transactionId);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while deleting transaction with ID " + transactionId);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static List<Transaction> getAllTransactions() {
		try {
			return transDao.getAllTransactions();
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while fetching all transactions");
			logger.error(e.getMessage());
		} finally {

		}
		return null;
	}
}
