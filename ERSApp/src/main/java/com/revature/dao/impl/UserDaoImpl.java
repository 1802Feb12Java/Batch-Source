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
import com.revature.database.ConnectionFactory;

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

	public void addUser(User user) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "INSERT INTO ERS_USERS" + " (U_ID, U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL)"
				+ " VALUES(?,?,?,?,?,?)";

		// CommonStatements.InsertIntoFill.apply(TABLE_NAME, TABLE_COLS.length);
		logger.debug("Created statement = " + statement + "\nwith user " + user);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, user.getUserId());
			s.setString(2, user.getUsername());
			s.setString(3, user.getPassword());
			s.setString(4, user.getFirstName());
			s.setString(5, user.getLastName());
			s.setString(6, user.getEmail());

			s.executeQuery();
			logger.debug("Executed SQL Query");
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	public User getUser(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM ERS_USERS U " + " WHERE USER_ID=? "
				+ " INNER JOIN ERS_USER_ROLES R ON U.UR_ID = R.UR_ID";
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

	public User getUser(String username, String password) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM ERS_USERS U" + " INNER JOIN ERS_USER_ROLES R ON U.UR_ID = R.UR_ID "
				+ " WHERE U_USERNAME=? AND U_PASSWORD = ?";
		logger.debug(
				"Created SQL Statement: " + statement + " With U_USERNAME " + username + ", U_PASSWORD" + password);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setString(1, username);
			s.setString(2, password);

			rs = s.executeQuery();
			logger.debug("Fetched User with username: " + username + ", password: " + password + " from Database");

			User u = null;
			if (rs.next())
				u = processRow(rs);
			else
				logger.debug("no result set found");
			return u;
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		}
	}

	public void updateUser(User user) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "UPDATE ERS_USERS"
				+ " SET U_USERNAME=?, U_PASSWORD=?, U_FIRSTNAME=?, U_LASTNAME=?, U_EMAIL=?" + " WHERE U_ID = ?";

		logger.debug("Created SQL Statement = " + statement + "\nwith user " + user);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setString(1, user.getUsername());
			s.setString(2, user.getPassword());
			s.setString(3, user.getFirstName());
			s.setString(4, user.getLastName());
			s.setString(5, user.getEmail());

			s.setInt(6, user.getUserId());

			s.executeQuery();
			logger.debug("Executed SQL Query");
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
		String statement = "DELETE FROM ERS_USERS" + " WHERE U_ID =?";

		logger.debug("Created SQL statement = " + statement + "\nwith userId " + userId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, userId);

			s.executeQuery();
			logger.debug("Executed SQL Query");
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	public List<User> getAllUsers() throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();

		String statement = "SELECT * FROM ERS_USERS " + " INNER JOIN ERS_USER_ROLES R ON U.UR_ID = R.UR_ID";

		logger.debug("Created SQL Statement = " + statement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);

			rs = s.executeQuery();
			logger.debug("Fetched All Users from Database");

			while (rs.next()) {
				list.add(processRow(rs));
			}
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
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
		user.setUserId(rs.getInt("U_ID"));
		user.setUsername(rs.getString("U_USERNAME"));
		user.setPassword(rs.getString("U_PASSWORD"));
		user.setFirstName(rs.getString("U_FIRSTNAME"));
		user.setLastName(rs.getString("U_PASSWORD"));
		user.setEmail(rs.getString("U_EMAIL"));
		// inner joining on ERS_USER_ROLES Table to get the string value of the role
		user.setUserRole(rs.getString("UR_ROLE"));
		return user;

	}

}
