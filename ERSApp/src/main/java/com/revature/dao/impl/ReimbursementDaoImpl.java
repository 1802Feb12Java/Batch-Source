package com.revature.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
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
				+ "	U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS)" + " VALUES(?,?,?,?,?,?,?,?,?,?)";

		logger.debug("Created SQL Statement = " + statement + "\nwith reimbursement " + reimbursement);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, reimbursement.getReimbursementId());
			s.setBigDecimal(2, reimbursement.getAmount());
			s.setString(3, reimbursement.getDecription());
			// InputStream in = new ByteArrayInputStream(reimbursement.getRecipt());
			// s.setBinaryStream(3, in, reimbursement.getRecipt().length);
			s.setNull(4, java.sql.Types.BLOB);
			s.setTimestamp(5, reimbursement.getSubmitted());
			s.setTimestamp(6, reimbursement.getResolved());
			s.setInt(7, reimbursement.getAuthorId());
			s.setNull(8, java.sql.Types.NUMERIC);
			s.setInt(9, typeValue(reimbursement.getType()));
			s.setInt(10, statusValue(reimbursement.getStatus()));

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

		String statement = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_RECEIPT, R.R_SUBMITTED, R.R_RESOLVED, R.U_ID_AUTHOR, R.U_ID_RESOLVER,\r\n"
				+ "T.RT_TYPE, S.RS_STATUS FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID "
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID " + "WHERE R_ID=?";

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
			InputStream in = new ByteArrayInputStream(reimbursement.getRecipt());
			s.setBinaryStream(3, in, reimbursement.getRecipt().length);
			s.setTimestamp(4, reimbursement.getSubmitted());
			s.setTimestamp(5, reimbursement.getResolved());
			s.setInt(6, reimbursement.getAuthorId());
			s.setInt(7, reimbursement.getResolverId());
			s.setInt(8, typeValue(reimbursement.getType()));
			s.setInt(9, statusValue(reimbursement.getStatus()));

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

	public List<Reimbursement> getAllReimbursements() throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		String statement = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_RECEIPT, R.R_SUBMITTED, R.R_RESOLVED, R.U_ID_AUTHOR, R.U_ID_RESOLVER,\r\n"
				+ "T.RT_TYPE, S.RS_STATUS FROM ERS_REIMBURSEMENTS R "
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

	public List<Reimbursement> getAllReimbursements(String status) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		String statement = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_RECEIPT, R.R_SUBMITTED, R.R_RESOLVED, R.U_ID_AUTHOR, R.U_ID_RESOLVER,\r\n"
				+ "T.RT_TYPE, S.RS_STATUS FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID"
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID" + " WHERE R.RT_STATUS =?";

		logger.debug("Created SQL Statement = " + statement + "with status=" + status);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, statusValue(status));

			rs = s.executeQuery();
			logger.debug("Fetched All Reimbursements from Database with status " + status);

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
	public static Reimbursement processRow(ResultSet rs) throws SQLException {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setReimbursementId(rs.getInt("R_ID"));
		reimbursement.setAmount(rs.getBigDecimal("R_AMOUNT"));
		reimbursement.setDecription(rs.getString("R_DESCRIPTION"));
		byte[] rec;
		try {
			InputStream bs = rs.getBinaryStream("R_RECEIPT");
			if (bs != null) {
				rec = IOUtils.toByteArray(bs);
				reimbursement.setRecipt(rec);
			}
		} catch (IOException e) {
			logger.error(e);
		}

		reimbursement.setSubmitted(rs.getTimestamp("R_SUBMITTED"));
		reimbursement.setResolved(rs.getTimestamp("R_RESOLVED"));
		reimbursement.setAuthorId(rs.getInt("U_ID_AUTHOR"));
		reimbursement.setResolverId(rs.getInt("U_ID_RESOLVER"));
		// join on ERS_REIMBURSEMENTS_TYPE table to get type value
		reimbursement.setType(rs.getString("RT_TYPE"));
		logger.debug("Type fetched = " + rs.getString("RT_TYPE"));
		// join on ERS_REIMBURSEMENTS_STATUS to get status value
		reimbursement.setStatus(rs.getString("RS_STATUS"));
		return reimbursement;

	}

	private int statusValue(String status) {
		switch (status.toLowerCase()) {
		case "pending":
			return 0;
		case "approved":
			return 1;
		case "denied":
			return 2;
		default:
			return -1;
		}
	}

	private int typeValue(String type) {
		switch (type.toLowerCase()) {
		case "business":
			return 0;
		case "travel":
			return 1;
		case "medical":
			return 2;
		case "other":
			return 3;
		default:
			return -1;
		}
	}

}
