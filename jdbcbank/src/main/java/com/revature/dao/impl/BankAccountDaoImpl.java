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

	@Override
	public void addBankAccount(BankAccount account) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;

		String statement = "INSERT INTO " + TABLE_NAME + "(ACCOUNT_ID, BALANCE) VALUES(?,?)";
		logger.debug("Created SQL Statement: " + statement);
		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);

			// fill in values
			s.setInt(1, account.getAccountId());
			s.setBigDecimal(2, account.getBalance());

			s.executeQuery();
			logger.debug("Execute Insert Bank Account with ID " + account.getAccountId() + " Successfully");
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	@Override
	public BankAccount getBankAccount(int accountId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM " + TABLE_NAME + " WHERE ACCOUNT_ID = " + accountId;
		logger.debug("Created SQL Statement: " + statement + " with ACCOUNT_ID " + accountId);
		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			rs = s.executeQuery();
			if (rs.next())
				return processRow(rs);
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		}
		return null;
	}

	@Override
	public void updateBankAccount(BankAccount account) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "UPDATE " + TABLE_NAME + " SET BALANCE=?, DATE_CREATED=? " + " WHERE ACCOUNT_ID =?";
		logger.debug("Created statement = " + statement + " with account " + account);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			// fill in the ???s
			s.setBigDecimal(1, account.getBalance());
			s.setDate(2, account.getDateCreated());
			s.setInt(3, account.getAccountId());

			s.executeQuery();
			logger.debug("Executed update bank account on " + account);
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	@Override
	public void deleteBankAccount(int accountId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE ACCOUNT_ID = " + accountId;
		logger.debug("Create SQL Statement: " + statement + " WITH ACCOUNT_ID " + accountId);
		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.executeQuery();
			logger.debug("Executed Statement");
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	@Override
	public ArrayList<BankAccount> getAllBankAccounts() throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		ArrayList<BankAccount> list = new ArrayList<>();
		String statement = CommonStatements.SelectAll.apply(TABLE_NAME);
		logger.debug("Create SQL Statement: " + statement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			rs = s.executeQuery();
			logger.debug("Fetched All Bank Accounts from Database");
			while (rs.next()) {
				list.add(processRow(rs));
			}
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
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
