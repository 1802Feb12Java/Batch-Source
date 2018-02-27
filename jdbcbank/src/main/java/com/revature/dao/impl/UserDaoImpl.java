package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.dao.UserDao;
import com.revature.util.CommonStatements;
import com.revature.util.ConnectionFactory;

public class UserDaoImpl implements UserDao {

	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

	public final static String TABLE_NAME = "BANK_USER";
	// store table MetaData here for now
	private final static String USER_ID = "USER_ID";
	private final static String USERNAME = "USERNAME";
	private final static String PASSWORD = "PASSWORD";
	private final static String FIRST_NAME = "FNAME";
	private final static String LAST_NAME = "LNAME";
	private final static String BIRTHDATE = "BIRTHDATE";
	private final static String IS_SUPER = "SUPER";
	private final static String DATE_CREATED = "DATE_CREATED";

	private final static String TABLE_COLS[] = { USER_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, BIRTHDATE,
			IS_SUPER };

	public void addUser(User user) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "INSERT INTO BANK_USER" + " (USER_ID, USERNAME, PASSWORD, FNAME, LNAME, BIRTHDATE, SUPER)"
				+ " VALUES(?,?,?,?,?,?,?)";

		// CommonStatements.InsertIntoFill.apply(TABLE_NAME, TABLE_COLS.length);
		logger.debug("Created statement = " + statement + " with user " + user);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, user.getUserId());
			s.setString(2, user.getUsername());
			s.setString(3, user.getPassword());
			s.setString(4, user.getFirstName());
			s.setString(5, user.getLastName());
			s.setDate(6, user.getBirthdate());
			s.setInt(7, user.getSuper());

			s.executeQuery();
			logger.debug("Executed SQL Query");
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	/**
	 * Returns a user with matching USER_ID
	 */
	public User getUser(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM " + TABLE_NAME + " WHERE USER_ID=?";
		logger.debug("Created SQL Statement: " + statement + " With USER_ID " + userId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, userId);

			rs = s.executeQuery();
			logger.debug("Fetched User with userId: " + userId + " from Database");

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

	public User getUser(String username) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM " + TABLE_NAME + " WHERE USERNAME=? ";
		logger.debug("Created SQL Statement: " + statement + " With USERNAME " + username);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setString(1, username);

			rs = s.executeQuery();
			logger.debug("Fetched User with username: " + username);

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

	public User getUser(String username, String password) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM " + TABLE_NAME + " WHERE USERNAME=? AND PASSWORD = ?";
		logger.debug("Created SQL Statement: " + statement + " With USERNAME " + username + ", PASSWORD" + password);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setString(1, username);
			s.setString(2, password);

			rs = s.executeQuery();
			logger.debug("Fetched User with username: " + username + ", password: " + password + " from Database");

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

	public void updateUser(User user) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = CommonStatements.Update.apply(TABLE_NAME, TABLE_COLS);
		logger.debug("Created SQL Statement: " + statement + " with user " + user);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			// fill in the ???s
			s.setString(1, TABLE_NAME);
			for (int i = 1; i < TABLE_COLS.length; i++) {
				s.setString(i + 1, TABLE_COLS[i]);
			}
			// set id ?
			s.setString(TABLE_COLS.length, TABLE_COLS[0]);

			s.executeQuery();
			logger.debug("Updated User to " + user);
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	public void deleteUser(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "DELETE FROM " + TABLE_NAME + " WHERE USER_ID = ?";
		logger.debug("Created statement = " + statement + " with USER_ID " + userId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, userId);

			s.executeQuery();
			logger.debug("Deleted user from database with USER_ID " + userId);
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	/*
	 * Gets a list of all the User objects in the DB BANK_USER Table
	 */
	public List<User> getAllUsers() throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();

		String sqlStatement = ("SELECT * FROM " + TABLE_NAME);
		logger.debug("Created SQL Statement: " + sqlStatement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(sqlStatement);
			rs = s.executeQuery();
			logger.debug("Fetched All Users from Database");

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
	 * Converts a result set row into User POJO
	 * 
	 * @param rs
	 * @return
	 */
	private User processRow(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt(USER_ID));
		user.setUsername(rs.getString(USERNAME));
		user.setPassword(rs.getString(PASSWORD));
		user.setFirstName(rs.getString(FIRST_NAME));
		user.setLastName(rs.getString(LAST_NAME));
		user.setBirthdate(rs.getDate(BIRTHDATE));
		user.setSuper(rs.getInt(IS_SUPER));
		user.setDateCreated(rs.getDate(DATE_CREATED));
		return user;

	}

}
