package com.revature.bl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.dao.impl.UserDaoImpl;
import com.revature.dao.impl.UtilDaoImpl;

/**
 * Exception handling layer for User DAO
 */
public class Users {
	private static final Logger logger = LogManager.getLogger(Users.class);

	private static UserDaoImpl userDao = new UserDaoImpl();

	public static boolean addUser(User user) {
		try {
			// put in user PK
			// get next sequence id
			int seqNum = Util.getNextSequence(UtilDaoImpl.USER_SEQ);
			logger.debug("Got seq num: " + seqNum + " from sequence: " + UtilDaoImpl.USER_SEQ);
			// update user obj's PK if default
			if (user.getUserId() == 0)
				user.setUserId(seqNum);

			userDao.addUser(user);

		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while adding user " + user);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static User getUser(int userId) {
		try {
			return userDao.getUser(userId);

		} catch (SQLException e) {
			// log for now
			logger.error("No User Exists with the ID " + userId);
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

	public static User getUser(String username) {
		try {
			return userDao.getUser(username);

		} catch (SQLException e) {
			logger.debug("No User Exists with the Username " + username);
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

	public static User getUser(String username, String password) {
		try {
			return userDao.getUser(username, password);
		} catch (SQLException e) {
			// log for now
			logger.error("No User Exists with the USERNAME " + username + " and PASSWORD " + password);
			logger.error(e.getMessage());
		} finally {

		}
		return null;
	}

	public static boolean updateUser(User user) {

		try {
			userDao.updateUser(user);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while updating user to " + user);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static boolean deleteUser(int userId) {
		try {
			userDao.deleteUser(userId);
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while deleting user with ID " + userId);
			logger.error(e.getMessage());
			return false;
		} finally {

		}

		return true;
	}

	public static List<User> getAllUsers() {
		try {
			return userDao.getAllUsers();
		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error while fetching all user");
			logger.error(e.getMessage());
		} finally {

		}

		return null;
	}

}
