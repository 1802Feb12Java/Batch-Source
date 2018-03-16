package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbursementDao;
import com.revature.database.ConnectionFactory;

public class ReimbursementDaoImpl implements ReimbursementDao {

	private static final Logger logger = LogManager.getLogger(ReimbursementDaoImpl.class);

	public void addReimbursement(Connection con, Reimbursement reimbursement) throws SQLException {
		PreparedStatement s = null;
		String statement = "INSERT INTO ERS_REIMBURSEMENTS"
				+ " (R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED,"
				+ "	U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS)" + " VALUES(?,?,?,?,?,?,?,?,?,?)";

		logger.debug("Created SQL Statement = " + statement + "\nwith reimbursement " + reimbursement);

		try {
			s = con.prepareStatement(statement);
			s.setInt(1, reimbursement.getReimbursementId());
			s.setBigDecimal(2, reimbursement.getAmount());
			s.setString(3, reimbursement.getDecription());
			s.setBlob(4, reimbursement.getRecipt());
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
		}

	}

	public Reimbursement getReimbursement(Connection con, int reimbursementId) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_RECEIPT, R.R_SUBMITTED, R.R_RESOLVED, R.U_ID_AUTHOR, R.U_ID_RESOLVER, "
				+ "T.RT_TYPE, S.RS_STATUS, U.U_FIRSTNAME, U.U_LASTNAME FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID "
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID "
				+ " LEFT JOIN ERS_USERS U ON U.U_ID = R.U_ID_RESOLVER" + "WHERE R_ID=?";

		logger.debug("Created SQL Statement: " + statement + " With R_ID " + reimbursementId);

		try {
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
		}

		return null;
	}

	public void updateReimbursement(Connection con, Reimbursement reimbursement) throws SQLException {
		PreparedStatement s = null;
		String statement = "UPDATE ERS_REIMBURSEMENTS "
				+ " SET R_AMOUNT=?, R_DESCRIPTION=?, R_RECEIPT=?, R_SUBMITTED=?, R_RESOLVED=?,"
				+ "	U_ID_AUTHOR=?, U_ID_RESOLVER=?, RT_TYPE=?, RT_STATUS=?" + " WHERE R_ID = ?";

		logger.debug("Created SQL Statement = " + statement + "\nwith reimbursement " + reimbursement);

		try {
			s = con.prepareStatement(statement);
			s.setBigDecimal(1, reimbursement.getAmount());
			s.setString(2, reimbursement.getDecription());
			s.setBlob(3, reimbursement.getRecipt());
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

	public void updateReimbursement(Connection con, int reimId, String status, int approverId, Timestamp resolved)
			throws SQLException {
		PreparedStatement s = null;
		String statement = "UPDATE ERS_REIMBURSEMENTS " + " SET RT_STATUS=?, U_ID_RESOLVER=?, R_RESOLVED=? "
				+ " WHERE R_ID = ?";

		logger.debug("Created SQL Statement = " + statement + "\nwith to status" + statusValue(status) + " reimId "
				+ reimId + " resolverid " + approverId + " timestamped " + resolved);

		try {
			s = con.prepareStatement(statement);
			s.setInt(1, statusValue(status));
			s.setInt(2, approverId);
			s.setTimestamp(3, resolved);
			s.setInt(4, reimId);

			s.executeQuery();
			logger.debug("Executed SQL Query");
		} finally {
			if (s != null)
				s.close();
		}

	}

	public void deleteReimbursement(Connection con, int reimbursementId) throws SQLException {
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
		}

	}

	public List<Reimbursement> getAllReimbursements(Connection con) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		String statement = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_RECEIPT, R.R_SUBMITTED, R.R_RESOLVED, R.U_ID_AUTHOR, R.U_ID_RESOLVER, "
				+ "	T.RT_TYPE, S.RS_STATUS, U.U_FIRSTNAME, U.U_LASTNAME, U2.U_FIRSTNAME AS A_FIRST, U2.U_LASTNAME AS A_LAST FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID "
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID "
				+ " LEFT JOIN ERS_USERS U ON U.U_ID = R.U_ID_RESOLVER"
				+ " LEFT JOIN ERS_USERS U2 ON U2.U_ID = R.U_ID_AUTHOR";

		logger.debug("Created SQL Statement = " + statement);

		try {
			s = con.prepareStatement(statement);

			rs = s.executeQuery();
			logger.debug("Fetched All Reimbursements from Database");

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

	public List<Reimbursement> getAllReimbursements(Connection con, String status) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		List<Reimbursement> list = new ArrayList<>();

		String statement = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_RECEIPT, R.R_SUBMITTED, R.R_RESOLVED, R.U_ID_AUTHOR, R.U_ID_RESOLVER, "
				+ "T.RT_TYPE, S.RS_STATUS, U.U_FIRSTNAME, U.U_LASTNAME, U2.U_FIRSTNAME AS A_FIRST, U2.U_LASTNAME AS A_LAST  FROM ERS_REIMBURSEMENTS R "
				+ " INNER JOIN ERS_REIMBURSEMENT_TYPE T ON R.RT_TYPE = T.RT_ID"
				+ " INNER JOIN ERS_REIMBURSEMENT_STATUS S ON R.RT_STATUS = S.RS_ID"
				+ " LEFT JOIN ERS_USERS U2 ON U2.U_ID = R.U_ID_AUTHOR"
				+ " LEFT JOIN ERS_USERS U ON U.U_ID = R.U_ID_RESOLVER" + " WHERE R.RT_STATUS =?";

		logger.debug("Created SQL Statement = " + statement + "with status=" + status);

		try {
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
			if (rs != null)
				rs.close();
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
		reimbursement.setSubmitted(rs.getTimestamp("R_SUBMITTED"));
		reimbursement.setResolved(rs.getTimestamp("R_RESOLVED"));
		reimbursement.setAuthorId(rs.getInt("U_ID_AUTHOR"));
		reimbursement.setResolverId(rs.getInt("U_ID_RESOLVER"));
		reimbursement.setResolverName(rs.getString("U_FIRSTNAME") + " " + rs.getString("U_LASTNAME"));
		logger.debug("Reim name" + reimbursement.getResolverName());
		// join on ERS_REIMBURSEMENTS_TYPE table to get type value
		reimbursement.setType(rs.getString("RT_TYPE"));
		logger.debug("Type fetched = " + rs.getString("RT_TYPE"));
		// join on ERS_REIMBURSEMENTS_STATUS to get status value
		reimbursement.setStatus(rs.getString("RS_STATUS"));
		// set author name
		reimbursement.setAuthorName(rs.getString("A_FIRST") + " " + rs.getString("A_LAST"));

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
			logger.error("No valid status passed " + status);
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
			logger.error("No valid type passed " + type);
			return -1;
		}
	}

}
