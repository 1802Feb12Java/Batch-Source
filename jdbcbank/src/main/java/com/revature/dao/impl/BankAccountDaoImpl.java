package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.BankAccount;
import com.revature.dao.BankAccountDao;
import com.revature.util.CommonStatements;
import com.revature.util.ConnectionFactory;

public class BankAccountDaoImpl implements BankAccountDao {

	private static final Logger logger = LogManager.getLogger(BankAccountDaoImpl.class);
	public final static String TABLE_NAME = "BANK_ACCOUNT";
	private final static String TABLE_COLS[] = { "ACCOUNT_ID", "BALANCE", "DATE_CREATED" };

	private Connection con = ConnectionFactory.getInstance().getConnection();

	@Override
	public void addBankAccount(BankAccount account) throws SQLException {
		PreparedStatement s = null;

		String statement = "INSERT INTO " + TABLE_NAME + "(ACCOUNT_ID, BALANCE) VALUES(?,?)";
		logger.info("Created SQL Statement: " + statement);
		try {
			s = con.prepareStatement(statement);

			// fill in values
			s.setInt(1, account.getAccountId());
			s.setBigDecimal(2, account.getBalance());

			s.executeQuery();
			logger.info("Execute Insert Bank Account with ID " + account.getAccountId() + " Successfully");
		} finally {
			if (s != null)
				s.close();
		}

	}

	@Override
	public BankAccount getBankAccount(int accountId) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM " + TABLE_NAME + " WHERE ACCOUNT_ID = " + accountId;
		try {
			s = con.prepareStatement(statement);
			rs = s.executeQuery();
			if (rs.next())
				return processRow(rs);
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
		}
		return null;
	}

	@Override
	public void updateBankAccount(BankAccount account) throws SQLException {
		PreparedStatement s = null;

		try {
			s = con.prepareStatement(CommonStatements.Update.apply(TABLE_NAME, TABLE_COLS));
			// fill in the ???s
			s.setString(1, TABLE_NAME);
			for (int i = 1; i < TABLE_COLS.length; i++) {
				s.setString(i + 1, TABLE_COLS[i]);
			}
			// set id ?
			s.setString(TABLE_COLS.length, TABLE_COLS[0]);
			logger.info("Created statement = " + s);

			s.executeQuery();
			logger.info("Executed Statement");
		} finally {
			if (s != null)
				s.close();
		}

	}

	@Override
	public void deleteBankAccount(int accountId) throws SQLException {
		PreparedStatement s = null;
		try {
			s = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE ACCOUNT_ID = " + accountId);
			logger.info("Created statement = " + s);
			s.executeQuery();
			logger.info("Executed Statement");
		} finally {
			if (s != null)
				s.close();
		}

	}

	@Override
	public ArrayList<BankAccount> getAllBankAccounts() throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<BankAccount> list = new ArrayList<>();

		try {
			s = con.prepareStatement(CommonStatements.SelectAll.apply(TABLE_NAME));
			rs = s.executeQuery();
			logger.info("Fetched All Bank Accounts from Database");
			while (rs.next()) {
				list.add(processRow(rs));
			}
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
		}
		return list;
	}

	/**
	 * Converts ResultSet row to BankAccount POJO
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private BankAccount processRow(ResultSet rs) throws SQLException {
		BankAccount account = new BankAccount();
		account.setAccountId(rs.getInt("ACCOUNT_ID"));
		account.setBalance(rs.getBigDecimal("BALANCE"));
		account.setDateCreated(rs.getDate("DATE_CREATED"));
		logger.debug("Processed row from bank account " + account);
		return account;

	}

}
