package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.util.ConnectionFactory;

public class UtilDaoImpl {
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
	private static Connection con = ConnectionFactory.getInstance().getConnection();

	public static final String USER_SEQ = "BANK_USER_SEQ";
	public static final String BANK_ACCOUNT_SEQ = "BANK_ACCOUNT_SEQ";

	public int getNextSequence(String sequenceName) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		String statement = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
		logger.info("Created SQL Statement: " + statement);

		try {
			s = con.prepareCall(statement);
			rs = s.executeQuery();
			logger.info("Selected next sequence value for sequence name " + sequenceName);
			if (rs.next())
				return rs.getInt(1);
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
		}

		return -1;
	}

}
