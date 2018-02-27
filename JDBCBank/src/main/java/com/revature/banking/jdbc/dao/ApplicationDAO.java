package com.revature.banking.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.jdbc.model.Application;
import com.revature.banking.jdbc.utilities.DAOUtilities;

public class ApplicationDAO implements InterfaceApplicationDAO{

	private static Connection  connection = null;
	private static PreparedStatement stmt = null;
	public ApplicationDAO() {
	}
	public List<Application> getAllApplications() {
		List<Application> applications= new ArrayList<Application>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKAPPLICATION";
			stmt = connection.prepareStatement(sqlQuery);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Application application = new Application(results.getInt("applicationId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getString("status"), results.getInt("employee"), results.getInt("accountId"), results.getDate("applyDate").toLocalDate(), results.getDate("decisionDate").toLocalDate());
				applications.add(application);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAllApplications");
			e.printStackTrace();
		}
		return applications;
	}
	public Application getApplicationByApplicationId(int applicationId) {
		Application application = null;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKAPPLICATION where applicationId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, Integer.toString(applicationId));
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				application = new Application(results.getInt("applicationId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getString("status"), results.getInt("employee"), results.getInt("accountId"), results.getDate("applyDate").toLocalDate(), results.getDate("decisionDate").toLocalDate());
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getApplicationByApplicationId");
			e.printStackTrace();
		}
		return application;
	}
	public List<Application> getApplicationsByCustomerId(int customerId) {
		List<Application> applications= new ArrayList<Application>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKAPPLICATION where primaryCustomerId=? OR secondaryCustomerID=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, Integer.toString(customerId));
			stmt.setString(2, Integer.toString(customerId));
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Application application = new Application(results.getInt("applicationId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getString("status"), results.getInt("employee"), results.getInt("accountId"), results.getDate("applyDate").toLocalDate(), results.getDate("decisionDate").toLocalDate());
				applications.add(application);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getApplicationsByCustomerId");
			e.printStackTrace();
		}
		
		return applications;
	}
	public List<Application> getAllProcessingApplications() {
		List<Application> applications = new ArrayList<Application>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKAPPLICATION where status=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, "Processing");
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Application application = new Application(results.getInt("applicationId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getString("status"), results.getInt("employee"), results.getInt("accountId"), results.getDate("applyDate").toLocalDate(), results.getDate("decisionDate").toLocalDate());
				applications.add(application);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getApplicationByApplicationId");
			e.printStackTrace();
		}
		return applications;
	}

	public List<Application> getAllApprovedApplications() {
		List<Application> applications = new ArrayList<Application>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKAPPLICATION where status=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, "Approved");
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Application application = new Application(results.getInt("applicationId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getString("status"), results.getInt("employee"), results.getInt("accountId"), results.getDate("applyDate").toLocalDate(), results.getDate("decisionDate").toLocalDate());
				applications.add(application);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getApplicationByApplicationId");
			e.printStackTrace();
		}
		return applications;
	}

	public List<Application> getAllDeniedApplications() {
		List<Application> applications = new ArrayList<Application>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKAPPLICATION where status=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, "Denied");
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Application application = new Application(results.getInt("applicationId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getString("status"), results.getInt("employee"), results.getInt("accountId"), results.getDate("applyDate").toLocalDate(), results.getDate("decisionDate").toLocalDate());
				applications.add(application);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getApplicationByApplicationId");
			e.printStackTrace();
		}
		return applications;
	}
	public Application getApplicationByAccountId(int accountId) {
		Application application = null;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKAPPLICATION where accountId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, Integer.toString(accountId));
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				application = new Application(results.getInt("applicationId"), results.getInt("primaryCustomerId"), 
						results.getInt("secondaryCustomerId"), results.getString("status"), results.getInt("employee"), 
						results.getInt("accountId"), results.getDate("applyDate").toLocalDate(), 
						results.getDate("decisionDate").toLocalDate());
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getApplicationByApplicationId");
			e.printStackTrace();
		}
		return application;
	}
	public boolean updateApplication(Application application) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "UPDATE BANKAPPLICATION SET primaryCustomerId=?, "
					+ "secondaryCustomerId=?, status=?, employee=?, accountId=?, applyDate=?, decisionDate=? WHERE applicationId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, application.getPrimaryCustomerId());
			stmt.setInt(2, application.getSecondaryCustomerId());
			stmt.setString(3, application.getStatus());
			stmt.setInt(4, application.getEmployeeId());
			stmt.setInt(5, application.getAccountId());
			stmt.setDate(6, Date.valueOf(application.getApplyDate()));
			stmt.setDate(7, Date.valueOf(application.getDecisionDate()));
			stmt.setInt(8, application.getApplicationId());
			if( stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: updateApplication");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	public boolean addApplication(Application application) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "INSERT INTO BANKAPPLICATION (primaryCustomerId, "
					+ "secondaryCustomerId, status, employee, accountId, applyDate, decisionDate)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sqlQuery, new String[] {"applicationId"});
			stmt.setString(1, Integer.toString(application.getPrimaryCustomerId()));
			stmt.setString(2, Integer.toString(application.getSecondaryCustomerId()));
			stmt.setString(3, application.getStatus());
			stmt.setString(4, Integer.toString(application.getEmployeeId()));
			stmt.setString(5, Integer.toString(application.getAccountId()));
			stmt.setDate(6, Date.valueOf(application.getApplyDate()));
			stmt.setDate(7, Date.valueOf(application.getDecisionDate()));
			if( stmt.executeUpdate() != 0) {
				return true;
			}else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: addApplication");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public boolean deleteApplication(Application application) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "DELETE FROM BANKAPPLICATION WHERE applicationId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, Integer.toString(application.getApplicationId()));
			if( stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: deleteApplication");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public int getNumApplications() {
		int count=0;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT count(*) FROM BANKAPPLICATION";
			stmt = connection.prepareStatement(sqlQuery);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				count = results.getInt(1);
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getNumApplications");
			e.printStackTrace();
		}
		
		return count;
	}


}
