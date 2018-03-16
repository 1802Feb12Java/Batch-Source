package com.revature.dao.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.Reimbursement;
import com.revature.database.ConnectionFactory;

public class SpecialReimbursementDao {
	private static final Logger logger = LogManager.getLogger(SpecialReimbursementDao.class);

	public List<Reimbursement> getAllReimbursementsWithUserId(Connection con, int userId) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		// joins status, type, and resolver table
		String statement = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_RECEIPT, R.R_SUBMITTED, R.R_RESOLVED, R.U_ID_AUTHOR, R.U_ID_RESOLVER,"
				+ " T.RT_TYPE, S.RS_STATUS, U.U_FIRSTNAME, U.U_LASTNAME, U2.U_FIRSTNAME AS A_FIRST, U2.U_LASTNAME AS A_LAST FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID"
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID"
				+ " LEFT JOIN ERS_USERS U2 ON U2.U_ID = R.U_ID_AUTHOR"
				+ " LEFT JOIN ERS_USERS U ON U.U_ID = R.U_ID_RESOLVER" + " WHERE R.U_ID_AUTHOR=?";

		logger.debug("Created SQL Statement = " + statement + "With author id =" + userId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, userId);

			rs = s.executeQuery();
			logger.debug("Fetched All Reimbursements from Database");

			while (rs.next()) {
				list.add(ReimbursementDaoImpl.processRow(rs));
			}
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
		}

		return list;
	}

	public List<Reimbursement> getAllReimbursementsWithStatus(Connection con, int userId, String status)
			throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		String statement = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_RECEIPT, R.R_SUBMITTED, R.R_RESOLVED, R.U_ID_AUTHOR, R.U_ID_RESOLVER, "
				+ "T.RT_TYPE, S.RS_STATUS, U.U_FIRSTNAME, U.U_LASTNAME, U2.U_FIRSTNAME AS A_FIRST, U2.U_LASTNAME AS A_LAST  FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID"
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID"
				+ " LEFT JOIN ERS_USERS U2 ON U2.U_ID = R.U_ID_AUTHOR"
				+ " LEFT JOIN ERS_USERS U ON U.U_ID = R.U_ID_RESOLVER" + " WHERE R.U_ID_AUTHOR=?"
				+ " AND S.RS_STATUS = ?";

		logger.debug("Created SQL Statement = " + statement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, userId);
			s.setString(2, status);

			rs = s.executeQuery();
			logger.debug("Fetched All Reimbursements from Database");

			while (rs.next()) {
				list.add(ReimbursementDaoImpl.processRow(rs));
			}
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
		}

		return list;
	}

	public List<Reimbursement> getAllReimbursementsWithType(Connection con, int userId, String type)
			throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		String statement = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_RECEIPT, R.R_SUBMITTED, R.R_RESOLVED, R.U_ID_AUTHOR, R.U_ID_RESOLVER, "
				+ " T.RT_TYPE, S.RS_STATUS, U.U_FIRSTNAME, U.U_LASTNAME, U2.U_FIRSTNAME AS A_FIRST, U2.U_LASTNAME AS A_LAST  FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID"
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID"
				+ " LEFT JOIN ERS_USERS U2 ON U2.U_ID = R.U_ID_AUTHOR"
				+ " LEFT JOIN ERS_USERS U ON U.U_ID = R.U_ID_RESOLVER" + " WHERE R.U_ID_AUTHOR=?" + " AND T.RT_TYPE=?";

		logger.debug("Created SQL Statement = " + statement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, userId);
			s.setString(1, type);

			rs = s.executeQuery();
			logger.debug("Fetched All Reimbursements from Database");

			while (rs.next()) {
				list.add(ReimbursementDaoImpl.processRow(rs));
			}
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
		}

		return list;
	}

	public Blob getReceipt(Connection con, int userId) throws SQLException, IOException {
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT R_RECEIPT FROM ERS_REIMBURSEMENTS " + " WHERE R_ID=?";

		logger.debug("Created SQL statement " + statement + " with userId");
		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, userId);

			rs = s.executeQuery();

			// // write binary stream into file
			// File file = new File(filename);
			// file.createNewFile();
			// logger.debug("Created a new file " + filename);
			//
			// logger.debug("Abs path:" + file.getAbsolutePath());
			//
			// fos = new FileOutputStream(file);

			while (rs.next()) {
				logger.debug(rs);
				return rs.getBlob("R_RECEIPT");
				// byte[] buffer = new byte[1024];
				// while (input.read(buffer) > 0) {
				// fos.write(buffer);
			}

		} finally

		{
			if (s != null)
				s.close();
		}
		return null;
	}

}
