package com.revature.dao;

import java.sql.Date;
import java.sql.SQLException;

public interface EmployeeDao {
	
	public void getInfo(int employeeId) throws SQLException;
	
	public void updateInfo(int employeeId, String password, String email);
	
	public void createReimbursementRequest(double amount, String description, String receipt, Date submittedTime, int authorId, int typeId) throws SQLException;
	
	public void getAllReimbursements(int employeeId) throws SQLException;
	
	public void getAllReimbursementsByStatus(int employeeId, int statusId) throws SQLException;

}
