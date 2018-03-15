package com.revature.dao;

import java.sql.SQLException;

public interface ReimbursementDAO {
	// Create
	void submitReimbursement(String username, double amount, String description, int type) throws SQLException;

	// Read
	String getReimbursementsAll() throws SQLException;

	String getReimbursements(String user) throws SQLException;

	// Update
	void uploadImage() throws SQLException;

	void approveDenyApplication(int appId, int approveDeny, String approver) throws SQLException;
}