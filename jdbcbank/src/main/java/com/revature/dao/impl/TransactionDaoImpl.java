package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.Transaction;
import com.revature.dao.TransactionDao;
import com.revature.util.ConnectionFactory;

public class TransactionDaoImpl implements TransactionDao {
	private static final Logger logger = LogManager.getLogger(TransactionDaoImpl.class);

	public final static String TABLE_NAME = "TRANSACTION";

	@Override
	public void addTransaction(Transaction transaction) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "INSERT INTO TRANSACTION " + " (TRANSACTION_ID, ACCOUNT_ID_FROM, ACCOUNT_ID_TO, AMOUNT)"
				+ " VALUES(?,?,?,?)";

		logger.debug("Created statement = " + statement + " with transaction " + transaction);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, transaction.getTransactionId());
			s.setInt(2, transaction.getAccountIdFrom());
			s.setInt(3, transaction.getAccountIdTo());
			s.setBigDecimal(4, transaction.getAmount());

			s.executeQuery();
			logger.debug("Executed SQL Query");

		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	@Override
	public Transaction getTransaction(int TransactionId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM " + TABLE_NAME + " WHERE TRANSACTION_ID=?";
		logger.debug("Created SQL Statement: " + statement + " With TRANSACTION_ID " + TransactionId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, TransactionId);

			rs = s.executeQuery();
			logger.debug("Fetched Transaction with TransactionId: " + TransactionId);

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
	public void updateTransaction(Transaction transaction) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "UPDATE " + TABLE_NAME + " SET ACCOUNT_ID_FROM =?, "
				+ " ACCOUNT_ID_TO=?, DATE_CREATED=?, AMOUNT=? " + " WHERE TRANSACTION_ID=?";
		logger.debug("Created SQL Statement: " + statement + " with transaction " + transaction);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			// fill in the ???s
			s.setInt(1, transaction.getAccountIdFrom());
			s.setInt(2, transaction.getAccountIdTo());
			s.setDate(3, transaction.getDateCreated());
			s.setBigDecimal(4, transaction.getAmount());
			s.setInt(5, transaction.getTransactionId());

			s.executeQuery();
			logger.debug("Updated Transaction to " + transaction);
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	@Override
	public void deleteTransaction(int transactionId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE TRANSACTION_ID = ?";
		logger.debug("Created statement = " + statement + " with TRANSACTION_ID " + transactionId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, transactionId);

			s.executeQuery();
			logger.debug("Deleted transaction from database with TRANSACTION_ID " + transactionId);
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	@Override
	public List<Transaction> getAllTransactions() throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Transaction> list = new ArrayList<>();

		String sqlStatement = ("SELECT * FROM " + TABLE_NAME);
		logger.debug("Created SQL Statement: " + sqlStatement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(sqlStatement);
			rs = s.executeQuery();
			logger.debug("Fetched All Transactions from Database");

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

	@Override
	public List<Transaction> getAllTransactions(int accountId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Transaction> list = new ArrayList<>();

		String sqlStatement = ("SELECT * FROM " + TABLE_NAME + " WHERE ACCOUNT_ID_TO =? OR ACCOUNT_ID_FROM=?");
		logger.debug("Created SQL Statement: " + sqlStatement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(sqlStatement);
			s.setInt(1, accountId);
			s.setInt(2, accountId);

			rs = s.executeQuery();
			logger.debug("Fetched All Transactions from Database to/from accountId " + accountId);

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

	private Transaction processRow(ResultSet rs) throws SQLException {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(rs.getInt("TRANSACTION_ID"));
		transaction.setAccountIdFrom(rs.getInt("ACCOUNT_ID_FROM"));
		transaction.setAccountIdTo(rs.getInt("ACCOUNT_ID_TO"));
		transaction.setDateCreated(rs.getDate("DATE_CREATED"));
		transaction.setAmount(rs.getBigDecimal("AMOUNT"));
		return transaction;
	}

}
