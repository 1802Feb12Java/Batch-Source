package com.ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerRequests implements RequestsDAO {
	
	private static Connection connection;
	
	public ManagerRequests(Connection connection) {
		this.connection = connection;
	}
	
	public int getNumberOfPendingRequests() {
		int numberOfRequests = 0;
		String queryString = "SELECT COUNT(RS_STATUS) FROM (SELECT * FROM ERS_REIMBURSMENTS REIMBURSMENT INNER JOIN ERS_REIMBURSMENT_STATUS RSTATUS ON REIMBURSMENT.RT_STATUS = RSTATUS.RS_STATUS) WHERE RS_STATUS='PENDING'";
		try {
			PreparedStatement pendingRequestsQuery = connection.prepareStatement(queryString);
			ResultSet resultSet = pendingRequestsQuery.executeQuery();
			if (resultSet.next()) {
				numberOfRequests = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error in getPendingReq.");
			e.printStackTrace();
		}
		
		
		return numberOfRequests;
	}
	
	public int getNumberOfApprovedRequests() {
		
		int numberOfRequests = 0;
		String queryString = "SELECT COUNT(RS_STATUS) FROM (SELECT * FROM ERS_REIMBURSMENTS REIMBURSMENT INNER JOIN ERS_REIMBURSMENT_STATUS RSTATUS ON REIMBURSMENT.RT_STATUS = RSTATUS.RS_STATUS) WHERE RS_STATUS='APPROVED'";
		try {
			PreparedStatement pendingRequestsQuery = connection.prepareStatement(queryString);
			ResultSet resultSet = pendingRequestsQuery.executeQuery();
			if (resultSet.next()) {
				numberOfRequests = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error in getApprovedReq.");
			e.printStackTrace();
		}
		
		
		return numberOfRequests;
	}
	
	public int getTotalNumberOfRequests() {
		
		int numberOfRequests = 0;
		String queryString = "SELECT COUNT(RS_STATUS) FROM (SELECT * FROM ERS_REIMBURSMENTS REIMBURSMENT INNER JOIN ERS_REIMBURSMENT_STATUS RSTATUS ON REIMBURSMENT.RT_STATUS = RSTATUS.RS_STATUS)";
		try {
			PreparedStatement pendingRequestsQuery = connection.prepareStatement(queryString);
			ResultSet resultSet = pendingRequestsQuery.executeQuery();
			if (resultSet.next()) {
				numberOfRequests = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error in getTotalReq.");
			e.printStackTrace();
		}
		
		
		return numberOfRequests;
	}
	
}
