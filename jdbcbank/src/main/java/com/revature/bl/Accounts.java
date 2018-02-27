package com.revature.bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.BankAccount;
import com.revature.dao.impl.BankAccountDaoImpl;
import com.revature.dao.impl.UserBankAccountDaoImpl;
import com.revature.dao.impl.UtilDaoImpl;

/**
 * Contains Business Logic for Bank Account DAO Access
 */
public class Accounts {
	private static final Logger logger = LogManager.getLogger(Users.class);

	private static BankAccountDaoImpl bankAccDao = new BankAccountDaoImpl();
	private static UserBankAccountDaoImpl userBankAccDao = new UserBankAccountDaoImpl();

	public static boolean addBankAccount(BankAccount account, int userId) {

		try {
			// put in user PK
			// get next sequence id
			int seqNum = Util.getNextSequence(UtilDaoImpl.BANK_ACCOUNT_SEQ);
			logger.debug("Got seq num: " + seqNum + " from sequence: " + UtilDaoImpl.BANK_ACCOUNT_SEQ);
			// update user obj's PK if default
			if (account.getAccountId() == 0)
				account.setAccountId(seqNum);

			bankAccDao.addBankAccount(account);

			// add in User Bank Account row entry too
			userBankAccDao.addUserBankAccount(userId, account.getAccountId());

		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while adding bank account " + account);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static BankAccount getBankAccount(int accountId) {
		try {
			return bankAccDao.getBankAccount(accountId);

		} catch (SQLException e) {
			// log for now
			logger.error("No Bank Account Exists with the ID " + accountId);
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

	public static ArrayList<BankAccount> getAllBankAccounts(int userId) {
		ArrayList<BankAccount> bankAccounts = new ArrayList<>();
		try {
			// get all the account numbers associated with the user from the
			// USER_BANK_ACCOUNT Table
			List<Integer> accountNumbers = userBankAccDao.getAllUserBankAccountsWithUserId(userId);

			// using those ids fetch all the corresponding accounts
			for (Integer accountNum : accountNumbers) {
				bankAccounts.add(bankAccDao.getBankAccount(accountNum));
			}

		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while fetching all the user's bank accounts with the id " + userId);
			logger.error(e.getMessage());
		} finally {

		}
		return bankAccounts;
	}

	public static boolean updateBankAccount(BankAccount account) {
		try {
			bankAccDao.updateBankAccount(account);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while updating bank account to " + account);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static boolean deleteBankAccount(int accountId, int userId) {
		try {

			// delete user bank account entry
			userBankAccDao.deleteUserBankAccount(userId, accountId);

			// then delete bank account #PK constraint
			bankAccDao.deleteBankAccount(accountId);

		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while deleting bank account with ID " + accountId);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static List<BankAccount> getAllBankAccounts() {
		try {
			return bankAccDao.getAllBankAccounts();
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while fetching all bank accounts");
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

}
