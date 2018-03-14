package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.dao.impl.UserDaoImpl;

/**
 * Exception handling layer for User DAO
 */
public class Users {
	private static final Logger logger = LogManager.getLogger(Users.class);

	private static UserDaoImpl userDao = new UserDaoImpl();

	public static boolean addUser(Connection con, User user) {
		try {
			// put in user PK
			// get next sequence id
			// int seqNum = Util.getNextSequence(UtilDaoImpl.USER_SEQ);
			// logger.debug("Got seq num: " + seqNum + " from sequence: " +
			// UtilDaoImpl.USER_SEQ);
			// update user obj's PK if default
			// if (user.getUserId() == 0)
			// user.setUserId(seqNum);
			// generate user id's database side
			user.setUserId(0);

			userDao.addUser(con, user);

		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while adding user " + user);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static User getUser(Connection con, int userId) {
		try {
			return userDao.getUser(con, userId);

		} catch (SQLException e) {
			// log for now
			logger.error("No User Exists with the ID " + userId);
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

	public static User getUser(Connection con, String username) {
		try {
			return userDao.getUser(con, username);
		} catch (SQLException e) {
			// log for now
			logger.error("No User Exists with the USERNAME " + username);
			logger.error(e.getMessage());
		} finally {

		}
		return null;
	}

	public static User getUser(Connection con, String username, String password) {
		try {
			return userDao.getUser(con, username, password);
		} catch (SQLException e) {
			// log for now
			logger.error("No User Exists with the USERNAME " + username + " and PASSWORD " + password);
			logger.error(e.getMessage());
		} finally {

		}
		return null;
	}

	public static boolean updateUser(Connection con, User user) {

		try {
			userDao.updateUser(con, user);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while updating user to " + user);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static boolean deleteUser(Connection con, int userId) {
		try {
			userDao.deleteUser(con, userId);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while deleting user with ID " + userId);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static boolean deleteUser(Connection con, String username) {
		try {
			userDao.deleteUser(con, username);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while deleting user with username " + username);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static List<User> getAllUsers(Connection con) {
		try {
			return userDao.getAllUsers(con);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while fetching all user");
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

	public static List<User> getAllUsers(Connection con, String role) {
		try {
			return userDao.getAllUsers(con, role);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while fetching all users with role " + role);
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

}
