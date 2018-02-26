package com.revature.dao.bl;

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
			// TODO CHECK FOR PK VIOLATION
			logger.error("SQL Error while adding bank account " + account);
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
			return false;
		} finally {

		}

		return true;
	}

	public static boolean deleteBankAccount(int accountId) {
		try {
			bankAccDao.deleteBankAccount(accountId);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while deleting bank account with ID " + accountId);
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
		} finally {

		}

		return null;
	}

}
