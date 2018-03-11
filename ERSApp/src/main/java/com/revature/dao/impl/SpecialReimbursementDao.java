package com.revature.dao.impl;

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

	public List<Reimbursement> getAllReimbursementsWithUserId(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		String statement = "SELECT * FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID"
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID" + " WHERE R.U_ID_AUTHOR=?";

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
			if (con != null)
				con.close();
			if (con != null)
				con.close();
		}

		return list;
	}

	public List<Reimbursement> getAllReimbursementsWithStatus(int userId, String status) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		String statement = "SELECT * FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID"
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID" + " WHERE R.U_ID_AUTHOR=?"
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
			if (con != null)
				con.close();
			if (con != null)
				con.close();
		}

		return list;
	}

	public List<Reimbursement> getAllReimbursementsWithType(int userId, String type) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		String statement = "SELECT * FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID"
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID" + " WHERE R.U_ID_AUTHOR=?"
				+ " AND T.RT_TYPE=?";

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
			if (con != null)
				con.close();
			if (con != null)
				con.close();
		}

		return list;
	}

}
