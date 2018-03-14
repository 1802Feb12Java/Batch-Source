package com.revature.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.codec.binary.Base64;

import org.apache.log4j.Logger;

//import com.revature.beans.Reimbursement;

public class ReimbursementDAOImp {
	
	private static Logger log = Logger.getLogger(ReimbursementDAOImp.class.getName());
	static ConnectionFactory cf = ConnectionFactory.getInstance();
	
	public static void createReimbursementRequest(int u_id_author, double amount, String desc, int r_type, byte[] receipt ) throws SQLException {
		//auto sets receipt, resolved, and resolver to null
		//need to set r_submitted to current time?
		//auto sets status to pending
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO ERS_REIMBURSEMENTS (R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED,"
				+ " R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS)"
				+ " VALUES (GET_NEW_R_ID(), ?, ?, ?, ?, null, ?, null, ?, 0)";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, amount);
		statement.setString(2, desc);
		statement.setBytes(3, receipt);
		statement.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
		statement.setInt(5, u_id_author);
		statement.setInt(6, r_type);
		
		int rowsInserted = statement.executeUpdate();
		if(rowsInserted > 0) {
			System.out.println("Record Successfully Added.");
			log.info("New Reimbursement Record Created Successfully.");
		} else {
			System.out.println("Error: Record Not Added.");
			log.debug("ERROR: There was a problem with creating a new reimbursement record.");
		}
		
	}
	
	public static void updateRequestStatus(int r_id, int new_r_status, int u_resolver) throws SQLException {
		//1- approve, 2-deny
		Connection conn = cf.getConnection();
		String sql = "UPDATE ERS_REIMBURSEMENTS SET RT_STATUS = ?, R_RESOLVED = ?, U_ID_RESOLVER = ? WHERE R_ID = ?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, new_r_status);
		statement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
		statement.setInt(3, u_resolver);
		statement.setInt(4, r_id);
		
		int rowsUpdated = statement.executeUpdate();
		if(rowsUpdated > 0) {
			System.out.println("Status for reimbursement request #" + r_id + " has been successfully updated.");
			log.info("Status for Reimbursement Request #" + r_id + " Updated Successfully.");
		} else {
			System.out.println("Error: Request Status Not Updated.");
			log.debug("ERROR: There was a problem updating a reimbursement request status.");
		}
		
		
	}//end updateRequestStatus
	
	public static ArrayList<String> viewAllPendingRequests() throws SQLException {
		
		//view pending requests, by user name
				Connection conn = cf.getConnection();
				
				//String sql = "SELECT * FROM ERS_USERS WHERE U_USERNAME = ?";
				String sql = "SELECT * FROM ERS_REIMBURSEMENTS"
						+ " LEFT JOIN ERS_USERS ON ERS_REIMBURSEMENTS.U_ID_AUTHOR = ERS_USERS.U_ID"
						+ " WHERE RT_STATUS = 0";
				PreparedStatement statement = conn.prepareStatement(sql);
				
				
				ArrayList<String> pendRequestList = new ArrayList<String>();
				
				ResultSet result = statement.executeQuery();
				while(result.next()) {
					int r_id = result.getInt(1); //may need to change this get
					double r_amount = result.getDouble(2);
					String r_description = result.getString(3);
					byte[] r_receipt = result.getBytes(4);
					String encodedReceipt = Base64.encodeBase64String(r_receipt);
					Date r_submitted = result.getDate(5);
					int rt_type = result.getInt(9);
					//below is user info from the join that I will want to display along with the reimbursement info
					String u_firstname = result.getString("U_FIRSTNAME");
					String u_lastname = result.getString("U_LASTNAME");
					String u_author = u_firstname + " " + u_lastname;
					String type;
					
					switch(rt_type) {
					case 1:
						type = "Travel";
						break;
					case 2:
						type = "Supplies";
						break;
					case 3:
						type = "Client Relations";
						break;
					case 4:
						type = "Meals";
						break;
					case 5:
						type = "Other";
						break;
					default:
						type = "Not Specified";
						break;
					}
					
					
					pendRequestList.add(r_id + ":" + r_amount + ":"+ r_description + ":" + encodedReceipt + ":" + r_submitted + ":" + u_author + ":" + type);
					
				}//end while
		
				return pendRequestList;
	}//end viewAllPendingRequests
	
	public static ArrayList<String> viewAllRequests() throws SQLException {
		
				Connection conn = cf.getConnection();
				
				String sql = "SELECT * FROM ERS_REIMBURSEMENTS"
						+ " LEFT JOIN ERS_USERS ON ERS_REIMBURSEMENTS.U_ID_AUTHOR = ERS_USERS.U_ID"
						+ " LEFT JOIN ERS_USERS ON ERS_REIMBURSEMENTS.U_ID_RESOLVER = ERS_USERS.U_ID";
				PreparedStatement statement = conn.prepareStatement(sql);
				
				
				ArrayList<String> requestList = new ArrayList<String>();
				
				ResultSet result = statement.executeQuery();
				while(result.next()) {
					int r_id = result.getInt(1); //may need to change this get
					double r_amount = result.getDouble(2);
					String r_description = result.getString(3);
					byte[] r_receipt = result.getBytes(4);
					String encodedReceipt = Base64.encodeBase64String(r_receipt);
					Date r_submitted = result.getDate(5);
					Date r_resolved = result.getDate(6);
					int rt_type = result.getInt(9);
					int rt_status = result.getInt(10);
					//below is user info from the join that I will want to display along with the reimbursement info
					String u_firstname = result.getString("U_FIRSTNAME");
					String u_lastname = result.getString("U_LASTNAME");
					String r_firstname = result.getString(21);
					String r_lastname = result.getString(22);
					String u_resolver;
					if(r_firstname == null) {
						u_resolver = null;
					} else {
						u_resolver = r_firstname + " " + r_lastname;
					}
					String u_author = u_firstname + " " + u_lastname;
					String type;
					String status;
					switch(rt_type) {
					case 1:
						type = "Travel";
						break;
					case 2:
						type = "Supplies";
						break;
					case 3:
						type = "Client Relations";
						break;
					case 4:
						type = "Meals";
						break;
					case 5:
						type = "Other";
						break;
					default:
						type = "Not Specified";
						break;
					}
					
					switch(rt_status) {
					case 0: 
						status = "Pending";
						break;
					case 1:
						status = "Approved";
						break;
					case 2:
						status = "Denied";
						break;
					default:
						status = "Not Specified";
						break;
					}
					requestList.add(r_id + ":" + r_amount + ":"+ r_description + ":" + encodedReceipt + ":" + r_submitted + ":" + u_author + ":" + r_resolved + ":" + u_resolver + ":" + type + ":" + status);
					
				}//end while
		
				return requestList;
	}//end viewAllRequests
	
	public static ArrayList<String> viewUserRequests(int userID, int filter) throws SQLException {
		//view pending requests, by user name
		Connection conn = cf.getConnection();
		String sql;
		if(filter == 1) {
			sql = "SELECT * FROM ERS_REIMBURSEMENTS"
					+ " LEFT JOIN ERS_USERS ON ERS_REIMBURSEMENTS.U_ID_RESOLVER = ERS_USERS.U_ID"
					+ " WHERE U_ID_AUTHOR = ? AND RT_STATUS = 0";
		} else if (filter == 2) {
			sql = "SELECT * FROM ERS_REIMBURSEMENTS"
					+ " LEFT JOIN ERS_USERS ON ERS_REIMBURSEMENTS.U_ID_RESOLVER = ERS_USERS.U_ID"
					+ " WHERE U_ID_AUTHOR = ? AND RT_STATUS != 0";
		} else {
			sql = "SELECT * FROM ERS_REIMBURSEMENTS"
					+ " LEFT JOIN ERS_USERS ON ERS_REIMBURSEMENTS.U_ID_RESOLVER = ERS_USERS.U_ID"
					+ " WHERE U_ID_AUTHOR = ?";
		}
		
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, userID);
		
		ArrayList<String> requestList = new ArrayList<String>();
		
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			int r_id = result.getInt(1); //may need to change this get
			double r_amount = result.getDouble(2);
			String r_description = result.getString(3);
			byte[] r_receipt = result.getBytes(4);
			String encodedReceipt = Base64.encodeBase64String(r_receipt);
			Date r_submitted = result.getDate(5);
			Date r_resolved = result.getDate(6);
			int rt_type = result.getInt(9);
			int rt_status = result.getInt(10);
			//below is user info from the join that I will want to display along with the reimbursement info
			String r_firstname = result.getString("U_FIRSTNAME");
			String r_lastname = result.getString("U_LASTNAME");
			String u_resolver;
			if(r_firstname == null) {
				u_resolver = null;
			} else {
				u_resolver = r_firstname + " " + r_lastname;
			}
			String type;
			String status;
			switch(rt_type) {
			case 1:
				type = "Travel";
				break;
			case 2:
				type = "Supplies";
				break;
			case 3:
				type = "Client Relations";
				break;
			case 4:
				type = "Meals";
				break;
			case 5:
				type = "Other";
				break;
			default:
				type = "Not Specified";
				break;
			}
			
			switch(rt_status) {
			case 0: 
				status = "Pending";
				break;
			case 1:
				status = "Approved";
				break;
			case 2:
				status = "Denied";
				break;
			default:
				status = "Not Specified";
				break;
			}
			
			requestList.add(r_id + ":" + r_amount + ":"+ r_description + ":" + encodedReceipt + ":" + r_submitted + ":" + r_resolved + ":" + u_resolver + ":" + type + ":" + status);
			
		}//end while
		return requestList;
	}//end viewUserRequests method
	
	public static int countUserReqs(int userID, int type) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "";
		if(type == 0) {
			sql = "SELECT COUNT(R_ID) FROM ERS_REIMBURSEMENTS"
					+ " WHERE U_ID_AUTHOR = ? AND RT_STATUS = 0";
		} else if (type == 1) {
			sql = "SELECT COUNT(R_ID) FROM ERS_REIMBURSEMENTS"
					+ " WHERE U_ID_AUTHOR = ?";
		}
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, userID);
		
		int count = 0;
		
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			count = result.getInt(1);
		}
		
		return count;
		
	}//end countUserReqs
	
	public static int getReimbursementID() throws SQLException {
		//just grabs the most recent reimbursement id
		//return ID
		Connection conn = cf.getConnection();
		String sql = "SELECT MAX(R_ID) FROM ERS_REIMBURSEMENTS";
		PreparedStatement statement = conn.prepareStatement(sql);
		
		int r_id = 0;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			r_id = result.getInt(1);
		}//end while
		
		if(r_id == 0) {
			log.debug("ERROR: Reimbursement ID Was Not Accessed Successfully.");
		}
		
		return r_id;
	}//end getReimbursementID
	
	public static Date getRequestSubmitDate(int r_id) throws SQLException {
		//just grabs the most recent reimbursement id
		//return ID
		Connection conn = cf.getConnection();
		String sql = "SELECT R_SUBMITTED FROM ERS_REIMBURSEMENTS WHERE R_ID = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, r_id);
		
		Date subDate = null;
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			subDate = result.getDate(1);
		}//end while
		
		if(subDate == null) {
			log.debug("ERROR: Reimbursement Submission Date Was Not Accessed Successfully.");
		}
		
		return subDate;
	}//end getReimbursementID
	
	public static void updateReceiptImage(int r_id, byte[] image_url) throws SQLException {
		
		Connection conn = cf.getConnection();
		String sql = "UPDATE ERS_REIMBURSEMENTS SET R_RECEIPT = ? WHERE R_ID = ?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setBytes(1, image_url);
		statement.setInt(2, r_id);
		
		int rowsUpdated = statement.executeUpdate();
		if(rowsUpdated > 0) {
			System.out.println("Receipt image for reimbursement request #" + r_id + " has been successfully updated.");
			log.info("Receipt Image for Reimbursement Request #" + r_id + " Updated Successfully.");
		} else {
			System.out.println("Error: Request Receipt Image Not Updated.");
			log.debug("ERROR: There was a problem updating a reimbursement request receipt image.");
		}
		
		
	}//end updateReceiptImage
	
}//end class
