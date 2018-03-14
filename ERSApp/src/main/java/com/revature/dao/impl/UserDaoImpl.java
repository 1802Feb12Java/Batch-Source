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

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

	public void addUser(Connection con, User user) throws SQLException {
		PreparedStatement s = null;
		String statement = "INSERT INTO ERS_USERS"
				+ " (U_ID, U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ID)" + " VALUES(?,?,?,?,?,?,?)";

		// CommonStatements.InsertIntoFill.apply(TABLE_NAME, TABLE_COLS.length);
		logger.debug("Created statement = " + statement + "\nwith user " + user);

		try {
			s = con.prepareStatement(statement);
			s.setInt(1, user.getUserId());
			s.setString(2, user.getUsername());
			s.setString(3, user.getPassword());
			s.setString(4, user.getFirstName());
			s.setString(5, user.getLastName());
			s.setString(6, user.getEmail());
			s.setInt(7, roleValue(user.getUserRole()));

			s.executeQuery();
			logger.debug("Executed SQL Query");
		} finally {
			if (s != null)
				s.close();
		}

	}

	public User getUser(Connection con, int userId) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM ERS_USERS U " + " WHERE U_ID=? "
				+ " INNER JOIN ERS_USER_ROLES R ON U.UR_ID = R.UR_ID";
		logger.debug("Created SQL Statement: " + statement + " With U_ID " + userId);

		try {
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
		}

		return null;
	}

	public User getUser(Connection con, String username) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM ERS_USERS U " + " WHERE U_USERNAME=? "
				+ " INNER JOIN ERS_USER_ROLES R ON U.UR_ID = R.UR_ID";
		logger.debug("Created SQL Statement: " + statement);

		try {
			s = con.prepareStatement(statement);
			s.setString(1, username);

			rs = s.executeQuery();
			logger.debug("Fetched User with username: " + username + " from Database");

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

	public User getUser(Connection con, String username, String password) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM ERS_USERS U" + " INNER JOIN ERS_USER_ROLES R ON U.UR_ID = R.UR_ID "
				+ " WHERE U_USERNAME=? AND U_PASSWORD = ?";
		logger.debug(
				"Created SQL Statement: " + statement + " With U_USERNAME " + username + ", U_PASSWORD" + password);

		try {
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
		}
	}

	public void updateUser(Connection con, User user) throws SQLException {
		PreparedStatement s = null;
		String statement = "UPDATE ERS_USERS"
				+ " SET U_USERNAME=?, U_PASSWORD=?, U_FIRSTNAME=?, U_LASTNAME=?, U_EMAIL=?" + " WHERE U_ID = ?";

		logger.debug("Created SQL Statement = " + statement + "\nwith user " + user);

		try {
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
		}

	}

	public void deleteUser(Connection con, int userId) throws SQLException {
		PreparedStatement s = null;
		String statement = "DELETE FROM ERS_USERS" + " WHERE U_ID =?";

		logger.debug("Created SQL statement = " + statement + "\nwith userId " + userId);

		try {
			s = con.prepareStatement(statement);
			s.setInt(1, userId);

			s.executeQuery();
			logger.debug("Executed SQL Query");
		} finally {
			if (s != null)
				s.close();
		}

	}

	public void deleteUser(Connection con, String username) throws SQLException {
		PreparedStatement s = null;
		String statement = "DELETE FROM ERS_USERS" + " WHERE U_USERNAME =?";

		logger.debug("Created SQL statement = " + statement + "\nwith username " + username);

		try {
			s = con.prepareStatement(statement);
			s.setString(1, username);

			s.executeQuery();
			logger.debug("Executed SQL Query");
		} finally {
			if (s != null)
				s.close();
		}

	}

	public List<User> getAllUsers(Connection con) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();

		String statement = "SELECT * FROM ERS_USERS U " + " INNER JOIN ERS_USER_ROLES R ON U.UR_ID = R.UR_ID";

		logger.debug("Created SQL Statement = " + statement);

		try {
			s = con.prepareStatement(statement);

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
		}

		return list;
	}

	public List<User> getAllUsers(Connection con, String role) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();

		String statement = "SELECT * FROM ERS_USERS U " + " INNER JOIN ERS_USER_ROLES R ON U.UR_ID = R.UR_ID"
				+ " WHERE U.UR_ID=?";

		logger.debug("Created SQL Statement = " + statement + " with user role " + role);

		try {
			s = con.prepareStatement(statement);
			s.setInt(1, roleValue(role));

			rs = s.executeQuery();
			logger.debug("Fetched Users from Database");

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
	 * Converts a result set row into User POJO
	 * 
	 * @param rs
	 * @return
	 */
	static User processRow(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("U_ID"));
		user.setUsername(rs.getString("U_USERNAME"));
		user.setPassword(rs.getString("U_PASSWORD"));
		user.setFirstName(rs.getString("U_FIRSTNAME"));
		user.setLastName(rs.getString("U_LASTNAME"));
		user.setEmail(rs.getString("U_EMAIL"));
		// inner joining on ERS_USER_ROLES Table to get the string value of the role
		user.setUserRole(rs.getString("UR_ROLE"));
		return user;

	}

	private int roleValue(String role) {
		switch (role.toLowerCase()) {
		case "admin":
			return 0;
		case "test":
			return 1;
		case "employee":
			return 2;
		case "manager":
			return 3;
		default:
			return -1;
		}
	}

}
