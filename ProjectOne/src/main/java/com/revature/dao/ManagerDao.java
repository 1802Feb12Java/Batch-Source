package com.revature.dao;

import java.sql.SQLException;

public interface ManagerDao {
	
	public void getManagerAccount(String username, String password) throws SQLException;
	
	public void getAllReimbursements() throws SQLException;
	
	public void getEmployeeReimbursements(int employeeId) throws SQLException;
	
	public void getReimbursementsByStatus(int statusId) throws SQLException;
	
	public void updateReimbursement(int reimbursementId, int statusId) throws SQLException;
	
	public void getAllEmployees() throws SQLException;
	
	

}
