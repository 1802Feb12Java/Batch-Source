package com.ers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

public class RequestType implements RequestTypeDAO{
	Connection connection;
	final static Logger logger = Logger.getLogger(RequestType.class);
	public RequestType(Connection connection) {
		this.connection = connection;
	}
	@Override
	public String getRequestTypes() {
		String requestTypeQueryString = "SELECT RT_TYPE FROM ERS_REIMBURSMENT_TYPE";
		String typesString = "";
		try {
			PreparedStatement requestTypeQuery = connection.prepareStatement(requestTypeQueryString);
			ResultSet resultSet = requestTypeQuery.executeQuery();
			while(resultSet.next()) {
				typesString = typesString + resultSet.getString(1) + ",";
			}
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}
		return typesString;
	}

	public void addReimbursment(float amount, String description,
									byte[] receipt, int requestTypeId, int authorId) {
		String insertString = "INSERT INTO ERS_REIMBURSMENTS (R_AMOUNT, R_DESCRIPTION, R_RECEIPT, RT_TYPE, U_ID_AUTHOR, R_SUBMITTED, RT_STATUS) VALUES (?,?,?,?,?,?,1)";
		
		try {
			//execute insert into table
			PreparedStatement insertQuery = connection.prepareStatement(insertString);
			insertQuery.setFloat(1, amount);
			insertQuery.setString(2, description);
			insertQuery.setBytes(3, receipt);
			insertQuery.setInt(4, requestTypeId);
			insertQuery.setInt(5, authorId);
			insertQuery.setTimestamp(6,  new Timestamp(System.currentTimeMillis()));
			insertQuery.execute();
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}
	}
	
	public int getRequestTypeId(String type) {
		int typeID = 0;
		String requestTypeString = "SELECT RT_ID FROM ERS_REIMBURSMENT_TYPE WHERE RT_TYPE = ?";
		PreparedStatement requestTypeQuery;
		try {
			requestTypeQuery = connection.prepareStatement(requestTypeString);
			requestTypeQuery.setString(1, type);
			ResultSet typeQueryResult = requestTypeQuery.executeQuery();
			if (typeQueryResult.next()) {
				typeID = typeQueryResult.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}
		return typeID;
	}
	public void addReimbursmentType(String type) {
		//add this later
	}
}
