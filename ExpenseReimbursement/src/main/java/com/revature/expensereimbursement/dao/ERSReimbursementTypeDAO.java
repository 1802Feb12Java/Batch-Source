package com.revature.expensereimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.expensereimbursement.DAOUtilities;
import com.revature.expensereimbursement.model.ERSReimbursementType;

public class ERSReimbursementTypeDAO implements InterfaceERSReimbursementTypeDAO {
	private static Connection  connection = null;
	private static PreparedStatement stmt = null;
	//CREATE
	public boolean addERSReimbursementType(ERSReimbursementType type) throws SQLException {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "INSERT INTO ERS_REIMBURSEMENT_TYPE () "
					+ "VALUES( ?, ?)";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, 0);
			stmt.setString(2, type.getType());
			return ( stmt.executeUpdate() != 0) ? true: false;
		} finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	//READ
	private List<ERSReimbursementType> getTypes() throws SQLException {
		List<ERSReimbursementType> types = new ArrayList<ERSReimbursementType>();
		ResultSet results = stmt.executeQuery();
		while(results.next()) {
			types.add(new ERSReimbursementType(results.getInt("RT_ID"), results.getString("RT_TYPE")));
		}
		return (types.size()>0) ? types: null;
	}
	public List<ERSReimbursementType> getAllTypes() throws SQLException {
		connection = DAOUtilities.getConnection();
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENT_TYPE";
		ERSReimbursementTypeDAO.stmt = connection.prepareStatement(sqlQuery);
		return getTypes();
	}

	public ERSReimbursementType getTypeById(int typeId) throws SQLException {
		List<ERSReimbursementType> types;
		connection = DAOUtilities.getConnection();
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENT_TYPE WHERE RT_ID=?";
		ERSReimbursementTypeDAO.stmt = connection.prepareStatement(sqlQuery);
		stmt.setInt(1, typeId);
		types = getTypes();
		return (types!= null) ? types.get(0): null;
	}

	public List<ERSReimbursementType> getTypeByType(String typeString) throws SQLException {
		connection = DAOUtilities.getConnection();
		String sqlQuery = "SELECT * FROM ERS_REIMBURSEMENT_TYPE WHERE ";
		ERSReimbursementTypeDAO.stmt = connection.prepareStatement(sqlQuery);
		stmt.setString(1, typeString);
		return getTypes();
	}
	//UPDATE
	public boolean updateType(ERSReimbursementType type) throws SQLException {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "UPDATE ERS_REIMBURSEMENT_TYPE SET RT_TYPE,"
					+ "WHERE R_ID=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, type.getType());
			stmt.setInt(2, type.getId());
			return ( stmt.executeUpdate() != 0) ? true: false;
		}  finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	//DELETE
	public boolean deleteType(ERSReimbursementType type) throws SQLException {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "DELETE FROM ERS_REIMBURSEMENTS WHERE R_ID=?;";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, type.getId());
			return ( stmt.executeUpdate() != 0) ? true: false;
		}  finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

}
