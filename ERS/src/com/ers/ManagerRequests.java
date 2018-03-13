package com.ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ManagerRequests {

	private Connection connection;
	final static Logger logger = Logger.getLogger(ManagerRequests.class);
	private LoginDAO loginDAO;
	
	public ManagerRequests(Connection connection) {
		this.connection = connection;
		this.loginDAO = new LoginDAO(connection);
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
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
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
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
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
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}
		return numberOfRequests;
	}

	public String[] getRequests(String statusParam, String employeeName) {
		int requestId = 0;
		float amount = 0;
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
		sb.append(
				" receipt, U_USERNAME as submitter, dateSubmitted, resolver, dateResolved, requestType, requestStatus");
		sb.append(" FROM requests INNER JOIN ERS_USERS ON requests.submitter = ERS_USERS.U_ID");

		if (statusParam.equals("PENDING")) {
			sb.append(" WHERE requestStatus = 'PENDING'");
		} else if (statusParam.equals("APPROVED")) {
			sb.append(" WHERE requestStatus = 'APPROVED'");
		}

		if (!employeeName.equals("NONE")) {
			sb.append(" WHERE ERS_USERS.U_USERNAME = ?");
		}

		String requestQueryString = sb.toString();
		try {
			PreparedStatement requestsQuery = connection.prepareStatement(requestQueryString);
			if (!employeeName.equals("NONE")) {
				requestsQuery.setString(1, employeeName);
			}
			ResultSet resultSet = requestsQuery.executeQuery();

			// create array of JSON objects
			while (resultSet.next()) {
				requestId = resultSet.getInt(1);
				amount = resultSet.getFloat(2);
				description = resultSet.getString(3);
				receipt = resultSet.getBytes(4);
				requester = resultSet.getString(5);
				timeStampRequest = resultSet.getTimestamp(6);
				resolver = resultSet.getString(7);
				timeStampApprove = resultSet.getTimestamp(8);
				type = resultSet.getString(9);
				status = resultSet.getString(10);

				String encodedReceipt = Base64.encodeBase64String(receipt);
				Request request = new Request(requestId, amount, type, description, status, requester, timeStampRequest,
						resolver, timeStampApprove, encodedReceipt);

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

	public void approveOrDenyRequest(int userId, int requestId, String resolutionType) {
		String updateQueryString = "UPDATE ERS_REIMBURSMENTS SET RT_STATUS = ?, U_ID_RESOLVER = ?, R_RESOLVED = ? WHERE R_ID = ?";
		String emailQueryString = "SELECT DISTINCT U_EMAIL FROM (SELECT * FROM ERS_REIMBURSMENTS INNER JOIN ERS_USERS ON ERS_REIMBURSMENTS.U_ID_AUTHOR = ERS_USERS.U_ID)";

		try {
			PreparedStatement updateQuery = connection.prepareStatement(updateQueryString);
			PreparedStatement emailQuery = connection.prepareStatement(emailQueryString);
			// set approval type
			if (resolutionType.equals("APPROVE")) {
				updateQuery.setInt(1, 2);
				ResultSet resultSet = emailQuery.executeQuery();
				String email = "";
				if (resultSet.next()) {
					email = resultSet.getString(1);
					Mailer.SendMail(email, "Update on your Reimbursement Request",
							"This email is being sent to inform you that one of your recent reimbursement requests has been approved."
									+ "This email was auto-generated. Please do not reply to this email.");
				}
			} else {
				updateQuery.setInt(1, 3);
				ResultSet resultSet = emailQuery.executeQuery();
				String email = "";
				if (resultSet.next()) {
					email = resultSet.getString(1);
					Mailer.SendMail(email, "Update on your Reimbursement Request",
							"This email is being sent to inform you that one of your recent reimbursement requests has been denied."
									+ "This email was auto-generated. Please do not reply to this email.");
				}

			}
			
			updateQuery.setInt(2, userId);
			updateQuery.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			updateQuery.setInt(4, requestId);
			updateQuery.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}

	}

	public String[] getAllEmployees() {
		List<String> results = new ArrayList<String>();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String getEmployeesQueryString = "SELECT U_FIRSTNAME, U_LASTNAME, U_EMAIL, U_USERNAME FROM ERS_USERS WHERE UR_ID = 2"; // UR_ID
																																// =
																																// 2
																																// for
																																// employee
		try {
			PreparedStatement getEmployeesQuery = connection.prepareStatement(getEmployeesQueryString);
			ResultSet resultSet = getEmployeesQuery.executeQuery();
			while (resultSet.next()) {
				String firstName = resultSet.getString(1);
				String lastName = resultSet.getString(2);
				String email = resultSet.getString(3);
				String userName = resultSet.getString(4);

				EmployeeInfo empInfo = new EmployeeInfo(firstName, lastName, userName, email);
				String json = gson.toJson(empInfo);
				results.add(json);

			}
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}

		return results.toArray(new String[results.size()]);
	}

	public String[] getEmployeeNames() {
		List<String> results = new ArrayList<String>();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		String employeeNameQueryString = "SELECT U_USERNAME FROM ERS_USERS WHERE UR_ID = 2";

		try {
			PreparedStatement employeeNameQuery = connection.prepareStatement(employeeNameQueryString);
			ResultSet resultSet = employeeNameQuery.executeQuery();
			while (resultSet.next()) {
				EmployeeInfo info = new EmployeeInfo(resultSet.getString(1));
				String json = gson.toJson(info);
				results.add(json);
			}
		} catch (SQLException e) {
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			logger.error("SQL Error From: " + this.getClass());
			logger.error(e.toString());
		}

		return results.toArray(new String[results.size()]);
	}

	public int registerEmployee(String firstName, String lastName, String email, String userName) {
		
		//generate Random password for new employee
		RandomStringGenerator randomStringGenerator =
		        new RandomStringGenerator.Builder()
		                .withinRange('0', 'z')
		                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
		                .build();
		
		String password = randomStringGenerator.generate(12);
		
		//check for duplicate user name
		if (loginDAO.userNameExists(userName)) {
			//user name exists, return error code
			return -1;
		}
		else if (loginDAO.emailExists(email)) {
			return -2;
		}
		else {
			//add employee to database
			String userInsertQueryString = "INSERT INTO ERS_USERS (U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ID) VALUES(?, ?, ?, ?, ?, 2)";
			try {
				PreparedStatement insertQuery = connection.prepareStatement(userInsertQueryString);
				insertQuery.setString(1, userName);
				insertQuery.setString(2, password);
				insertQuery.setString(3, firstName);
				insertQuery.setString(4, lastName);
				insertQuery.setString(5, email);
				
				int result = insertQuery.executeUpdate();
				//insert successful, return >0 to indicate success
				if (result > 0) {
					
					Mailer.SendMail(email, "Your New ERS Account", "You have sucessfuly been registered for the Revature Employee Reimbursement System\n"
							+ "Your Username is: " + userName + "\n Your Temporary Password is: " + password + 
							"\nYou may wish to change your password when you log in.");
					return result;
				}
				else {
					return -1;
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		//this should not happen
		return -1;
		
	}
	// inner class to JSONify
	@SuppressWarnings("unused")
	private class Request {
		int requestId;
		float amount;
		String type;
		String description;
		String status;
		String requester;
		Timestamp timeStampRequest;
		String resolver;
		Timestamp timeStampApprove;
		String receipt;

		Request(int requestId, float amount, String type, String description, String status, String requester,
				Timestamp timeStampRequest, String resolver, Timestamp timeStampApprove, String receipt) {
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

	@SuppressWarnings("unused")
	private class EmployeeInfo {
		String firstName;
		String lastName;
		String userName;
		String email;

		EmployeeInfo(String firstName, String lastName, String userName, String email) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.userName = userName;
			this.email = email;
		}

		EmployeeInfo(String userName) {
			this.userName = userName;
		}

	}

}
