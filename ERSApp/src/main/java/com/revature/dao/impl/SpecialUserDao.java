package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.User;

public class SpecialUserDao {
	private static final Logger logger = LogManager.getLogger(SpecialUserDao.class);

	public User getUserFullName(Connection con, int userId) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT U_FIRSTNAME, U_LASTNAME FROM ERS_USERS U " + " WHERE U_ID=? ";
		logger.debug("Created SQL Statement: " + statement + " With U_ID " + userId);

		try {
			s = con.prepareStatement(statement);
			s.setInt(1, userId);

			rs = s.executeQuery();
			logger.debug("Fetched User with userId: " + userId + " from Database");

			if (rs.next())
				return UserDaoImpl.processRow(rs);
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
		}

		return null;
	}

}
