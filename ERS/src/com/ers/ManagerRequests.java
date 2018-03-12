package com.ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ManagerRequests {
	
	private Connection connection;
	
	public ManagerRequests(Connection connection) {
		this.connection = connection;
	}
	

	public int getNumberOfPendingRequests() {
		int numberOfRequests = 0;
		String queryString = "SELECT COUNT(RS_STATUS) FROM (SELECT * FROM ERS_REIMBURSMENTS REIMBURSMENT INNER JOIN ERS_REIMBURSMENT_STATUS RSTATUS ON REIMBURSMENT.RT_STATUS = RSTATUS.RS_ID) WHERE RS_STATUS='PENDING'";
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
		String queryString = "SELECT COUNT(RS_STATUS) FROM (SELECT * FROM ERS_REIMBURSMENTS REIMBURSMENT INNER JOIN ERS_REIMBURSMENT_STATUS RSTATUS ON REIMBURSMENT.RT_STATUS = RSTATUS.RS_ID) WHERE RS_STATUS='APPROVED'";
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
		String queryString = "SELECT COUNT(R_ID) FROM ERS_REIMBURSMENTS";
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
	
	public String[] getRequests(String statusParam) {
		int requestId = 0;
		int amount = 0;
		String type = "";
		String description = "";
		byte[] receipt;
		String status = "";
		String requester = "";
		Timestamp timeStampRequest = null;
		String resolver = "";
		Timestamp timeStampApprove = null;
		
		List<String> results = new ArrayList<String>();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		StringBuilder sb = new StringBuilder("");
		sb.append("WITH requests AS (SELECT rei.R_ID as requestId, rei.R_AMOUNT as amount,");
		sb.append(" rei.R_DESCRIPTION as description, rei.R_RECEIPT as receipt, rei.U_ID_AUTHOR as submitter,");
		sb.append(" rei.R_SUBMITTED as dateSubmitted, usr.U_USERNAME as resolver, rei.R_RESOLVED dateResolved,");
		sb.append(" rt.RT_TYPE as requestType, status.RS_STATUS as requestStatus");
		sb.append(" FROM ERS_REIMBURSMENTS rei INNER JOIN ERS_REIMBURSMENT_TYPE rt ON rei.RT_TYPE =  rt.RT_ID");
		sb.append(" LEFT JOIN ERS_USERS usr ON rei.U_ID_RESOLVER = usr.U_ID INNER JOIN ERS_REIMBURSMENT_STATUS status");
		sb.append(" ON status.RS_ID = rei.RT_STATUS) SELECT requestId, amount, description, ");
		sb.append(" receipt, U_USERNAME as submitter, dateSubmitted, resolver, dateResolved, requestType, requestStatus");
		sb.append(" FROM requests INNER JOIN ERS_USERS ON requests.submitter = ERS_USERS.U_ID");

		if(statusParam.equals("PENDING")) {
			sb.append(" WHERE requestStatus = 'PENDING'");
		}
		else if (statusParam.equals("APPROVED")) {
			sb.append(" WHERE requestStatus = 'APPROVED'");
		}
		
		
		String requestQueryString = sb.toString();
		try {
			PreparedStatement requestsQuery = connection.prepareStatement(requestQueryString);
			ResultSet resultSet = requestsQuery.executeQuery();
			
			//create array of JSON objects
			while (resultSet.next()) {
				requestId = resultSet.getInt(1);
				amount = resultSet.getInt(2);
				description = resultSet.getString(3);
				receipt = resultSet.getBytes(4);
				requester = resultSet.getString(5);
				timeStampRequest = resultSet.getTimestamp(6);
				resolver = resultSet.getString(7);
				timeStampApprove = resultSet.getTimestamp(8);
				type = resultSet.getString(9);
				status = resultSet.getString(10);

				String encodedReceipt = Base64.encodeBase64String(receipt);
				Request request = new Request(requestId, amount, type, description, status, requester, timeStampRequest, resolver, timeStampApprove, encodedReceipt);
				
				String json = gson.toJson(request);
		        results.add(json);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results.toArray(new String[results.size()]);
	}
	
	public void approveOrDenyRequest(int userId, int requestId, String resolutionType) {
		String updateQueryString = "UPDATE ERS_REIMBURSMENTS RT_STATUS = ?, U_ID_AUTHOR = ?, R_RESOLVED = ? WHERE R_ID = ?";
		
	}
	
	
	//inner class to JSONify
		@SuppressWarnings("unused")
		private class Request {
			int requestId;
			int amount; 
			String type; 
			String description; 
			String status;
			String requester;
			Timestamp timeStampRequest; 
			String resolver; 
			Timestamp timeStampApprove;
			String receipt;
			
			Request (int requestId, int amount, String type, String description, 
					String status, String requester, Timestamp timeStampRequest, String resolver, 
					Timestamp timeStampApprove, String receipt) {
				this.requestId = requestId;
				this.amount = amount;
				this.description = description;
				this.status = status;
				this.requester = requester;
				this.timeStampRequest = timeStampRequest;
				this.resolver = resolver;
				this.timeStampApprove = timeStampApprove;
				this.receipt = receipt;
				this.type = type;
				
			}
		}
	
}
