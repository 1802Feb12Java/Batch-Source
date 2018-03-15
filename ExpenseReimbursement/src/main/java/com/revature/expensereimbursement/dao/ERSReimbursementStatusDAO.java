package com.revature.expensereimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.expensereimbursement.DAOUtilities;
import com.revature.expensereimbursement.model.ERSReimbursementStatus;

public class ERSReimbursementStatusDAO implements InterfaceERSReimbursementStatusDAO {
	
	private static Connection  connection = null;
	private static PreparedStatement stmt = null;
	public boolean createNewStatus(ERSReimbursementStatus status) throws SQLException {
		try {
			String sqlQuery = "INSERT INTO ERS_REIMBURSEMENT_STATUS (RS_ID, RT_STATUS) "
					+ "VALUES( ?, ?)";
			stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
			stmt.setInt(1, status.getId());
			stmt.setString(2, status.getStatus()); 
			return (stmt.executeUpdate() != 0) ? true: false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	private List<ERSReimbursementStatus> getStatuses() throws SQLException  {
		try {
			List<ERSReimbursementStatus> statuses = new ArrayList<ERSReimbursementStatus>();
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				statuses.add(new ERSReimbursementStatus(results.getInt("RS_ID"), results.getString("RS_STATUS")));
			}
			return (statuses.size()>0) ? statuses: null;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public List<ERSReimbursementStatus> getAllStatuses() throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENT_STATUS";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		return getStatuses();
	}

	public ERSReimbursementStatus getStatusById(int id) throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENT_STATUS where RS_ID = ?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setInt(1, id);		
		List<ERSReimbursementStatus> statuses = getStatuses();
		return (statuses!=null) ?  statuses.get(0): null;
	}

	public List<ERSReimbursementStatus> getAllStatusByStatus(String status) throws SQLException {
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENT_STATUS where RS_STATUS = ?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setString(1, status);
		return getStatuses();
	}

	public boolean updateStatus(ERSReimbursementStatus status) throws SQLException {
		String sqlQuery = "UPDATE ERS_REIMBURSEMENT_STATUS SET RS_ID=?, RS_STATUS=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setDouble(1, status.getId());
		stmt.setString(2, status.getStatus());
		try {
			return (stmt.executeUpdate() != 0) ? true: false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public boolean deleteStatus(int id) throws SQLException {
		String sqlQuery = "DELETE FROM ERS_REIMBURSEMENTS WHERE R_ID=?";
		stmt = DAOUtilities.getConnection().prepareStatement(sqlQuery);
		stmt.setInt(1, id);
		try {
			return (stmt.executeUpdate() != 0) ? true: false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

		
}
