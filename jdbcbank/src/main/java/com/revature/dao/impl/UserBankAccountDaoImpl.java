package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.dao.UserBankAccountDao;
import com.revature.util.ConnectionFactory;

public class UserBankAccountDaoImpl implements UserBankAccountDao {

	private static final Logger logger = LogManager.getLogger(UserBankAccountDaoImpl.class);
	public final static String TABLE_NAME = "USER_BANK_ACCOUNT";

	@Override
	public void addUserBankAccount(int userId, int accountId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;

		String statement = "INSERT INTO " + TABLE_NAME + "(USER_ID, ACCOUNT_ID) VALUES(?,?)";
		logger.debug(
				"Creating SQL Statement: " + statement + " with USER_ID= " + userId + ", ACCOUNT_ID= " + accountId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			// fill in values
			s.setInt(1, userId);
			s.setInt(2, accountId);

			s.executeQuery();
			logger.debug("Executed Insert User Bank Account with USER_ID " + userId + ", and ACCOUNT_ID " + accountId
					+ " Successfully");
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}
	}

	@Override
	public void getUserBankAccount(int userId, int accountId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;

		String statement = "SELECT FROM " + TABLE_NAME + " WHERE USER_ID=? AND ACCOUNT_ID)=?";
		logger.debug(
				"Creating SQL Statement: " + statement + " with USER_ID= " + userId + ", ACCOUNT_ID= " + accountId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			// fill in values
			s.setInt(1, userId);
			s.setInt(2, accountId);

			s.executeQuery();
			logger.debug("Executed Insert User Bank Account with USER_ID " + userId + ", and ACCOUNT_ID " + accountId
					+ " Successfully");

		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}
	}

	@Override
	public void updateUserBankAccountUserId(int userId, int accountId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;

		String statement = "UPDATE " + TABLE_NAME + " SET USER_ID = ? WHERE ACCOUNT_ID =?";
		logger.debug("Creating SQL Statement: " + statement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			// fill in values
			s.setInt(1, userId);
			s.setInt(2, accountId);

			s.executeQuery();
			logger.debug("Executed Update User Bank Account with USER_ID " + userId + ", and ACCOUNT_ID " + accountId
					+ " Successfully");

		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	@Override
	public void deleteUserBankAccount(int userId, int accountId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;

		String statement = "DELETE " + TABLE_NAME + " WHERE ACCOUNT_ID = ? AND USER_ID =?";
		logger.debug("Creating SQL Statement: " + statement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			// fill in values
			s.setInt(1, accountId);
			s.setInt(2, userId);

			s.executeQuery();
			logger.debug("Executed Delete User Bank Account with USER_ID " + userId + ", and ACCOUNT_ID " + accountId
					+ " Successfully");

		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}
	}

	@Override
	public ArrayList<Integer> getAllUserBankAccounts() throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		ArrayList<Integer> userIdList = new ArrayList<Integer>();
		String statement = "SELECT ACCOUNT_ID FROM " + TABLE_NAME;
		logger.debug("Created SQL Statement: " + statement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			rs = s.executeQuery();
			logger.debug("Executed SQL Statement");
			if (rs.next())
				userIdList.add(rs.getInt("ACCOUNT_ID"));
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		}

		return userIdList;
	}

	@Override
	public List<Integer> getAllUserBankAccountsWithUserId(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		List<Integer> userIdList = new ArrayList<Integer>();
		String statement = "SELECT ACCOUNT_ID FROM " + TABLE_NAME + " WHERE USER_ID=?";
		logger.debug("Created SQL Statement: " + statement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, userId);
			rs = s.executeQuery();
			logger.debug("Executed SQL Statement");
			while (rs.next())
				userIdList.add(rs.getInt("ACCOUNT_ID"));
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		}

		return userIdList;
	}

}
