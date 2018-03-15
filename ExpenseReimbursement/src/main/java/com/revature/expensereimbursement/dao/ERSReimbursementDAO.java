package com.revature.expensereimbursement.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.revature.expensereimbursement.DAOUtilities;
import com.revature.expensereimbursement.model.ERSReimbursement;

public class ERSReimbursementDAO implements InterfaceERSReimubursementDAO {
	private static Connection  connection = null;
	private static PreparedStatement stmt = null;
	
	//Read
	private List<ERSReimbursement> getReimbursements() throws SQLException {
		try {
			List<ERSReimbursement> reimbursements = new ArrayList<ERSReimbursement>();
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				byte [] receipt = results.getBytes("R_RECEIPT");
				System.out.println(receipt);
				ERSReimbursement rim = new ERSReimbursement(results.getInt("R_ID"),results.getInt("E_ID"),results.getInt("M_ID"), results.getInt("RT_ID"), 
						 results.getInt("RT_STATUS"), results.getDouble("R_AMOUNT"), results.getString("R_DESCRIPTION"), 
						 results.getString("E_FIRSTNAME"),results.getString("E_LASTNAME"), results.getString("M_FIRSTNAME"),results.getString("M_LASTNAME"), 
						 results.getString("RS_STATUS"), receipt, results.getTimestamp("R_SUBMITTED"),
						 results.getTimestamp("R_RESOLVED"), results.getString("RT_TYPE"), results.getString("E_USERNAME")	);
				reimbursements.add(rim);
				if(receipt!=null) {
					rim.setReceiptBase64(Base64.getEncoder().encodeToString(receipt));
					rim.setReceipt(null);
				}else {
					rim.setReceiptBase64("");
				}
				System.out.println(rim.getReceiptBase64());
			}
			return (reimbursements.size()>0) ? reimbursements : null;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	public List<ERSReimbursement> getAllReimbursements() throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENTS_VIEW";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		return getReimbursements();
	}
	public ERSReimbursement getReimbursementsById(int reimbursementId) throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENTS_VIEW WHERE R_ID=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setInt(1, reimbursementId);
		List<ERSReimbursement> reimbursements = getReimbursements();
		if(reimbursements!=null)
			return reimbursements.get(0);
		return null;
	}
	public List<ERSReimbursement> getReimbursementsByAmount(double min, double max) throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENTS_VIEW WHERE R_AMOUNT>=? OR R_AMOUNT<=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setDouble(1, min);
		stmt.setDouble(2, max);
		return getReimbursements();
	}
	public List<ERSReimbursement> getReimbursementsBySubmittedDate(Timestamp submitted) throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENTS_VIEW WHERE R_SUBMITTED=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setTimestamp(1, submitted);
		return getReimbursements();
	}
	public List<ERSReimbursement> getReimbursementsByResolvedDate(Timestamp resolved) throws SQLException {
		connection = DAOUtilities.getConnection();
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENTS_VIEW WHERE R_RESOLVED=?";
		stmt = connection.prepareStatement(sqlQuery);
		stmt.setTimestamp(1, resolved);
		return getReimbursements();
	}
	public List<ERSReimbursement> getReimbursementsByAuthor(int authorId) throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENTS_VIEW WHERE E_ID=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setInt(1, authorId);
		return getReimbursements();
	}
	public List<ERSReimbursement> getReimbursementsByResolver(int resolverId) throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENTS_VIEW WHERE M_ID=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setInt(1, resolverId);
		return getReimbursements();
	}
	public List<ERSReimbursement> getReimbursementsByType(int typeId) throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENTS_VIEW WHERE RT_TYPE=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setInt(1, typeId);
		return getReimbursements();
	}
	public List<ERSReimbursement> getReimbursementsByStatus(int statusId) throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENTS_VIEW WHERE RT_STATUS=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setInt(1, statusId);
		return getReimbursements();
	}	//Create
	public boolean addReimbursement(ERSReimbursement reimbursement) throws SQLException {
		try {
			String sqlQuery = "INSERT INTO ERS_REIMBURSEMENTS (R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, "
					+ "U_ID_RESOLVER, RT_TYPE, RT_STATUS) "
					+ "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
			stmt.setInt(1, 0);
			stmt.setDouble(2, reimbursement.getAmount()); 
			stmt.setString(3, reimbursement.getDescription());
			stmt.setBytes(4, reimbursement.getReceipt());  
			stmt.setTimestamp(5, reimbursement.getSubmitted()); 
			stmt.setTimestamp(6, null);
			stmt.setInt(7, reimbursement.getAuthorId()); 
			stmt.setNull(8, java.sql.Types.INTEGER); 
			stmt.setInt(9, reimbursement.getTypeId());
			stmt.setInt(10, 1);
			return (stmt.executeUpdate() != 0) ? true: false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("ERROR: Adding Reimbursement: " + reimbursement.toString());
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	//Update ALL
	public boolean updateReimbursement(ERSReimbursement reimbursement) throws SQLException {
		String sqlQuery = "UPDATE ERS_REIMBURSEMENTS SET R_RESOLVED=?,  U_ID_RESOLVER=?,"
				+ " RT_STATUS=? WHERE R_ID=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setTimestamp(1, reimbursement.getResolved());
		stmt.setInt(2, reimbursement.getResolverId());
		stmt.setInt(3, reimbursement.getStatusId());
		stmt.setInt(4, reimbursement.getReimbursementId());
		try {
			 return (stmt.executeUpdate() != 0) ? true: false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	//Delete
	public boolean deleteReimbursement(int reimbursementId) throws SQLException {
		String sqlQuery = "DELETE FROM ERS_REIMBURSEMENTS WHERE R_ID=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setInt(1, reimbursementId);
		try {
			return (stmt.executeUpdate() != 0) ? true: false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
}
