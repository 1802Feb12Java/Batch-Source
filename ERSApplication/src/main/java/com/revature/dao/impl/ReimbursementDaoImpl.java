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
import com.revature.dao.ReimbursementDao;
import com.revature.database.ConnectionFactory;

public class ReimbursementDaoImpl implements ReimbursementDao {

	private static final Logger logger = LogManager.getLogger(ReimbursementDaoImpl.class);

	public void addReimbursement(Reimbursement reimbursement) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "INSERT INTO ERS_REIMBURSEMENTS"
				+ " (R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED,"
				+ "	U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS)" + " VALUES(?,?,?,?,?,?,?,?,?)";

		logger.debug("Created SQL Statement = " + statement + "\nwith reimbursement " + reimbursement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, reimbursement.getReimbursementId());
			s.setBigDecimal(2, reimbursement.getAmount());
			s.setBlob(3, reimbursement.getRecipt());
			s.setTimestamp(4, reimbursement.getSubmitted());
			s.setTimestamp(5, reimbursement.getResolved());
			s.setInt(6, reimbursement.getAuthorId());
			s.setInt(7, reimbursement.getResolverId());
			s.setString(8, reimbursement.getType());
			s.setString(9, reimbursement.getStatus());

			s.executeQuery();
			logger.debug("Executed SQL Query");
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	public Reimbursement getReimbursement(int reimbursementId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM ERS_REIMBURSEMENTS R WHERE R_ID=? "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID "
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID";

		logger.debug("Created SQL Statement: " + statement + " With R_ID " + reimbursementId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, reimbursementId);

			rs = s.executeQuery();
			logger.debug("Fetched Reimbursement with reimbursementId: " + reimbursementId + " from Database");

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

	public void updateReimbursement(Reimbursement reimbursement) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "UPDATE ERS_REIMBURSEMENTS "
				+ " SET R_AMOUNT=?, R_DESCRIPTION=?, R_RECEIPT=?, R_SUBMITTED=?, R_RESOLVED=?,"
				+ "	U_ID_AUTHOR=?, U_ID_RESOLVER=?, RT_TYPE=?, RT_STATUS=?" + " WHERE R_ID = ?";

		logger.debug("Created SQL Statement = " + statement + "\nwith reimbursement " + reimbursement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setBigDecimal(1, reimbursement.getAmount());
			s.setString(2, reimbursement.getDecription());
			s.setBlob(3, reimbursement.getRecipt());
			s.setTimestamp(4, reimbursement.getSubmitted());
			s.setTimestamp(5, reimbursement.getResolved());
			s.setInt(6, reimbursement.getAuthorId());
			s.setInt(7, reimbursement.getResolverId());
			s.setString(8, reimbursement.getType());
			s.setString(9, reimbursement.getStatus());

			s.setInt(10, reimbursement.getReimbursementId());

			s.executeQuery();
			logger.debug("Executed SQL Query");
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	public void deleteReimbursement(int reimbursementId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		String statement = "DELETE FROM ERS_REIMBURSEMENTS" + " WHERE R_ID =?";

		logger.debug("Created SQL statement = " + statement + "\nwith reimbursementId " + reimbursementId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, reimbursementId);

			s.executeQuery();
			logger.debug("Executed SQL Query");
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	public List<Reimbursement> getAllReimbursement() throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		String statement = "SELECT * FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID"
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID";

		logger.debug("Created SQL Statement = " + statement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);

			rs = s.executeQuery();
			logger.debug("Fetched All Reimbursements from Database");

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
	 * Converts a result set row into Reimbursement POJO
	 * 
	 * @param rs
	 * @return
	 */
	private Reimbursement processRow(ResultSet rs) throws SQLException {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setReimbursementId(rs.getInt("R_ID"));
		reimbursement.setAmount(rs.getBigDecimal("R_AMOUNT"));
		reimbursement.setDecription(rs.getString("R_DESCRIPTION"));
		reimbursement.setRecipt(rs.getBlob("R_RECEIPT"));
		reimbursement.setSubmitted(rs.getTimestamp("R_SUBMITTED"));
		reimbursement.setResolved(rs.getTimestamp("RS_RESOLVED"));
		reimbursement.setAuthorId(rs.getInt("U_ID_RESOLVER"));
		reimbursement.setResolverId(rs.getInt("U_ID_RESOLVER"));
		// join on ERS_REIMBURSEMENTS_TYPE table to get type value
		reimbursement.setType(rs.getString("RT_TYPE_1"));
		// join on ERS_REIMBURSEMENTS_STATUS to get status value
		reimbursement.setStatus(rs.getString("RS_STATUS"));
		return reimbursement;

	}

}
