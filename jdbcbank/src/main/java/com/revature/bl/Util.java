package com.revature.bl;

import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.dao.impl.UtilDaoImpl;

/**
 * Exception handling layer for Util DAO
 */
public class Util {
	private static final Logger logger = LogManager.getLogger(Users.class);

	private static UtilDaoImpl utilDao = new UtilDaoImpl();

	public static int getNextSequence(String sequenceName) {
		try {
			return utilDao.getNextSequence(sequenceName);

		} catch (SQLException e) {
			// log for now
			logger.error("SQL Error executing getNextSeq on " + sequenceName);
			logger.error(e.getMessage());
		} finally {

		}
		return -1;
	}

}
