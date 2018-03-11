package com.revature;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.Reimbursement;
import com.revature.dao.impl.ReimbursementDaoImpl;
import com.revature.dao.impl.SpecialReimbursementDao;

public class Reimbursements {
	private static final Logger logger = LogManager.getLogger(Reimbursements.class);

	private static ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
	private static SpecialReimbursementDao sReimDao = new SpecialReimbursementDao();

	public static boolean addReimbursement(Reimbursement reim) {

		try {
			// put in user PK
			// get next sequence id
			// int seqNum = Util.getNextSequence(UtilDaoImpl.BANK_ACCOUNT_SEQ);
			// logger.debug("Got seq num: " + seqNum + " from sequence: " +
			// UtilDaoImpl.BANK_ACCOUNT_SEQ);
			// // update user obj's PK if default
			// if (account.getAccountId() == 0)
			// account.setAccountId(seqNum);
			reim.setReimbursementId(0);
			// get sys time as current timestamp
			reim.setSubmitted(new Timestamp(System.currentTimeMillis()));
			// set status to pending - 0
			reim.setStatus("pending");
			reimDao.addReimbursement(reim);

		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while adding bank account " + reimDao);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static Reimbursement getReimbursement(int reimId) {
		try {
			return reimDao.getReimbursement(reimId);

		} catch (SQLException e) {
			// log for now
			logger.error("No Reimbursement Exists with the ID " + reimId);
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

	public static ArrayList<Reimbursement> getAllReimbursements(int userId) {
		ArrayList<Reimbursement> reimbursements = new ArrayList<>();
		try {
			// get all the account numbers associated with the user from the
			// USER_BANK_ACCOUNT Table
			reimbursements = (ArrayList<Reimbursement>) sReimDao.getAllReimbursementsWithUserId(userId);

		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while fetching all the user's reimbursements with the id " + userId);
			logger.error(e.getMessage());
		} finally {

		}
		return reimbursements;
	}

	public static boolean updateReimbursement(Reimbursement reim) {
		try {
			reimDao.updateReimbursement(reim);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while updating reimbursement to " + reim);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static boolean deleteReimbursement(int reimId) {
		try {

			// delete user bank account entry
			reimDao.deleteReimbursement(reimId);

			// // update transactions with this account
			// ArrayList<Transaction> transactionList = (ArrayList<Transaction>)
			// Transactions.getTransactions(accountId);
			// for (Transaction t : transactionList) {
			// // wipe user id
			// if (t.getAccountIdFrom() == accountId)
			// t.setAccountIdFrom(0);
			// if (t.getAccountIdTo() == accountId)
			// t.setAccountIdFrom(0);
			// if (t.getAccountIdFrom() == t.getAccountIdFrom() && t.getAccountIdFrom() ==
			// 0) {
			// Transactions.deleteTransaction(t.getTransactionId());
			// logger.debug("Deleted transaction " + t);
			// } else {
			// Transactions.updateTransaction(t);
			// logger.debug("Updated transaction " + t);
			// }
			// }
			// // then delete bank account #PK constraint
			// reimDao.deleteReimbursement(accountId);

		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while deleting reimbursement with ID " + reimId);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static List<Reimbursement> getAllReimbursements() {
		try {
			return reimDao.getAllReimbursements();
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while fetching all reimbursements");
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

	public static List<Reimbursement> getAllReimbursements(String status) {
		try {
			return reimDao.getAllReimbursements(status);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while fetching all reimbursements");
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

}
