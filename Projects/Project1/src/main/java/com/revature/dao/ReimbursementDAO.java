package com.revature.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReimbursementDAO {
	// Create
	void submitReimbursement(String username, double amount, String description, int type) throws SQLException;

	// Read
	String getReimbursementsAll() throws SQLException;

	ArrayList<String[]> getReimbursements(String user) throws SQLException;

	ArrayList<String[]> getPendingReimbursementsAll() throws SQLException;

	ArrayList<String[]> getPendingReimbursements(String user) throws SQLException;

	ArrayList<String[]> getResolvedAll() throws SQLException;

	ArrayList<String[]> getResolved(String user) throws SQLException;

	// Update
	void uploadImage() throws SQLException;

	void approveDenyApplication(int appId, int approveDeny) throws SQLException;
}