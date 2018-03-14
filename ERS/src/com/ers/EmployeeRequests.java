package com.ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EmployeeRequests implements RequestsDAO {
	private LoginDAO loginDAO;
	private Connection connection;
	final static Logger logger = Logger.getLogger(EmployeeRequests.class);
	public EmployeeRequests(Connection connection) {
		this.connection = connection;
		this.loginDAO = new LoginDAO(connection);
	}
	@Override
	public int getNumberOfPendingRequests(int userId) {
		int numberOfRequests = 0;
		String queryString = "SELECT COUNT(RS_STATUS) FROM (SELECT RS_STATUS, U_ID_AUTHOR FROM (SELECT * FROM ERS_REIMBURSMENTS REIMBURSMENT INNER JOIN ERS_REIMBURSMENT_STATUS RSTATUS ON REIMBURSMENT.RT_STATUS = RSTATUS.RS_ID) WHERE U_ID_AUTHOR = ?) WHERE RS_STATUS='PENDING'";
		
		try {
			PreparedStatement pendingRequestsQuery = connection.prepareStatement(queryString);
			pendingRequestsQuery.setInt(1, userId);
			ResultSet resultSet = pendingRequestsQuery.executeQuery();
			if (resultSet.next()) {
				numberOfRequests = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}
		return numberOfRequests;
	}

	@Override
	public int getNumberOfApprovedRequests(int userId) {
		int numberOfRequests = 0;
		String queryString = "SELECT COUNT(RS_STATUS) FROM (SELECT * FROM ERS_REIMBURSMENTS REIMBURSMENT INNER JOIN ERS_REIMBURSMENT_STATUS RSTATUS ON REIMBURSMENT.RT_STATUS = RSTATUS.RS_ID) WHERE RS_STATUS='APPROVED' AND U_ID_AUTHOR=?";
		try {
			PreparedStatement pendingRequestsQuery = connection.prepareStatement(queryString);
			pendingRequestsQuery.setInt(1, userId);
			ResultSet resultSet = pendingRequestsQuery.executeQuery();
			if (resultSet.next()) {
				numberOfRequests = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}
		
		
		return numberOfRequests;
	}

	@Override
	public int getTotalNumberOfRequests(int userId) {
		int numberOfRequests = 0;
		String queryString = "SELECT COUNT(R_ID) FROM ERS_REIMBURSMENTS WHERE U_ID_AUTHOR=?";
		try {
			PreparedStatement pendingRequestsQuery = connection.prepareStatement(queryString);
			pendingRequestsQuery.setInt(1, userId);
			ResultSet resultSet = pendingRequestsQuery.executeQuery();
			if (resultSet.next()) {
				numberOfRequests = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}
				
		return numberOfRequests;
	}
	
	public int getEmployeeId(String userName) {
		int employeeID = 0;
		String queryString = "SELECT U_ID FROM ERS_USERS WHERE U_USERNAME = ?";
		try {
			PreparedStatement userIdQuery = connection.prepareStatement(queryString);
			userIdQuery.setString(1, userName);
			ResultSet resultSet = userIdQuery.executeQuery();
			if (resultSet.next()) {
				employeeID = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}
		return employeeID;
	}
	
	public String[] getEmployeeInfo(int userId) {
		String employeeQueryString = "SELECT U_FIRSTNAME, U_LASTNAME, U_USERNAME, U_EMAIL FROM ERS_USERS WHERE U_ID = ?";
		String firstName = "";
		String lastName = "";
		String userName = "";
		String email = "";
		try {
			PreparedStatement employeeInfoQuery = connection.prepareStatement(employeeQueryString);
			employeeInfoQuery.setInt(1, userId);
			ResultSet resultSet = employeeInfoQuery.executeQuery();
			if (resultSet.next()) {
				firstName = resultSet.getString(1);
				lastName = resultSet.getString(2);
				userName = resultSet.getString(3);
				email = resultSet.getString(4);
			}
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}
		String[] result = {firstName, lastName, userName, email};
		return result;
	}
	
	
	public String[] getAllEmployeeRequests(int userId, String statusParam) {
		
		float amount = 0;
		String type = "";
		String description = "";
		byte[] receipt;
		String status = "";
		Timestamp timeStampRequest = null;
		String resolver = "";
		Timestamp timeStampApprove = null;
		List<String> results = new ArrayList<String>();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		//create giant SQL query
		StringBuilder sb = new StringBuilder("SELECT rei.R_AMOUNT as amount,");
		sb.append(" rei.R_DESCRIPTION as description,");
		sb.append(" rei.R_RECEIPT as receipt,");
		sb.append(" rei.R_SUBMITTED as dateSubmitted,");
		sb.append(" usr.U_USERNAME as resolver,");
		sb.append(" rei.R_RESOLVED dateResolved,");
		sb.append(" rt.RT_TYPE as requestType,");
		sb.append(" status.RS_STATUS as requestStatus");
		sb.append(" FROM ERS_REIMBURSMENTS rei");
		sb.append(" INNER JOIN ERS_REIMBURSMENT_TYPE rt");
		sb.append(" ON rei.RT_TYPE =  rt.RT_ID");
		sb.append(" LEFT JOIN ERS_USERS usr");
		sb.append(" ON rei.U_ID_RESOLVER = usr.U_ID");
		sb.append(" INNER JOIN ERS_REIMBURSMENT_STATUS status");
		sb.append(" ON status.RS_ID = rei.RT_STATUS");
		sb.append(" WHERE rei.U_ID_AUTHOR = ?");
		
		if(statusParam.equals("PENDING")) {
			sb.append(" AND status.RS_STATUS = 'PENDING'");
		}
		else if (statusParam.equals("APPROVED")) {
			sb.append(" AND status.RS_STATUS = 'APPROVED'");
		}
		
		
		String requestQueryString = sb.toString();
		try {
			PreparedStatement requestsQuery = connection.prepareStatement(requestQueryString);
			requestsQuery.setInt(1, userId);
			ResultSet resultSet = requestsQuery.executeQuery();
			//create array of JSON objects
			while (resultSet.next()) {
				amount = resultSet.getFloat(1);
				description = resultSet.getString(2);
				receipt = resultSet.getBytes(3);
				timeStampRequest = resultSet.getTimestamp(4);
				resolver = resultSet.getString(5);
				timeStampApprove = resultSet.getTimestamp(6);
				type = resultSet.getString(7);
				status = resultSet.getString(8);;

				String encodedReceipt = Base64.encodeBase64String(receipt);
				Request request = new Request(amount, type, description, status, 
						timeStampRequest, resolver, timeStampApprove, encodedReceipt);
				
				String json = gson.toJson(request);
		        results.add(json);
			}
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}
		return results.toArray(new String[results.size()]);
	}
	
	public String updateEmployee(int userId, String column, String newValue) {
		
		//check if email or userName violate integrity constraints
		if(column.equals("email")) {
			if (loginDAO.emailExists(newValue)) {
				return "EMAILFAIL";
			}
		}
		
		if(column.equals("userName")) {
			if (loginDAO.userNameExists(newValue)) {
				return "USERNAMEFAIL";
			}
		}
		
		//do updates
		int result = -1;
		switch (column) {
		case "firstName":
			result = updateFirstName(userId, newValue);
			break;
			
		case "lastName":
			result = updateLastName(userId, newValue);
			break;

		case "userName":
			result = updateUserName(userId, newValue);
				break;
				
		case "email":
			result = updateEmail(userId, newValue);
			break;
		case "password":
			result = updatePassword(userId, newValue);
			break;
			
		default:
			break;
		}
		
		if (result < 0) {
			return "PUTFAIL";
		}
		
		return "SUCCESS";
		
//		String updateEmployeeString = "UPDATE ERS_USERS SET U_USERNAME = ?, U_FIRSTNAME = ?, U_LASTNAME = ?, U_EMAIL = ? WHERE U_ID = ?";
	}
	
	private int updateFirstName(int userId, String firstName) {
		String updateEmployeeString = "UPDATE ERS_USERS SET U_FIRSTNAME = ? WHERE U_ID = ?";
		int result = 0;
		try {
			PreparedStatement updateQuery = connection.prepareStatement(updateEmployeeString);
			updateQuery.setString(1, firstName);
			updateQuery.setInt(2, userId);
			result = updateQuery.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("Error in: " + this.getClass());
			logger.error(e.toString());
		}
		
		return result;
	}
	
	private int updateLastName(int userId, String lastName) {
		String updateEmployeeString = "UPDATE ERS_USERS SET U_LASTNAME = ? WHERE U_ID = ?";
		int result = 0;
		try {
			PreparedStatement updateQuery = connection.prepareStatement(updateEmployeeString);
			updateQuery.setString(1, lastName);
			updateQuery.setInt(2, userId);
			result = updateQuery.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("Error in: " + this.getClass());
			logger.error(e.toString());
		}
		
		return result;
	}
	
	private int updateEmail(int userId, String email) {
		String updateEmployeeString = "UPDATE ERS_USERS SET U_EMAIL = ? WHERE U_ID = ?";
		int result = 0;
		try {
			PreparedStatement updateQuery = connection.prepareStatement(updateEmployeeString);
			updateQuery.setString(1, email);
			updateQuery.setInt(2, userId);
			result = updateQuery.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("Error in: " + this.getClass());
			logger.error(e.toString());
		}
		
		return result;
	}
	
	private int updateUserName(int userId, String userName) {
		String updateEmployeeString = "UPDATE ERS_USERS SET U_USERNAME = ? WHERE U_ID = ?";
		int result = 0;
		try {
			PreparedStatement updateQuery = connection.prepareStatement(updateEmployeeString);
			updateQuery.setString(1, userName);
			updateQuery.setInt(2, userId);
			result = updateQuery.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("Error in: " + this.getClass());
			logger.error(e.toString());
		}
		
		return result;
	}
	
	private int updatePassword(int userId, String password) {
		String updateEmployeeString = "UPDATE ERS_USERS SET U_PASSWORD = ? WHERE U_ID = ?";
		int result = 0;
		try {
			PreparedStatement updateQuery = connection.prepareStatement(updateEmployeeString);
			updateQuery.setString(1, password);
			updateQuery.setInt(2, userId);
			result = updateQuery.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("Error in: " + this.getClass());
			logger.error(e.toString());
		}
		
		return result;
	}
	
	//inner class to JSONify
	@SuppressWarnings("unused")
	private class Request {
		float amount; 
		String type; 
		String description; 
		String status; 
		Timestamp timeStampRequest; 
		String resolver; 
		Timestamp timeStampApprove;
		String receipt;
		
		Request (float amount, String type, String description, 
				String status, Timestamp timeStampRequest, String resolver, 
				Timestamp timeStampApprove, String receipt) {
			this.amount = amount;
			this.description = description;
			this.status = status;
			this.timeStampRequest = timeStampRequest;
			this.resolver = resolver;
			this.timeStampApprove = timeStampApprove;
			this.receipt = receipt;
			this.type = type;
			
		}
	}
}
