package com.revature.services;

import java.sql.SQLException;

import com.revature.dao.ManagerDao;

public class ManagerServices implements ManagerDao {
	
	static String get;

	public void getManagerAccount(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
	}

	public void getAllReimbursements() throws SQLException {
		
		get = "SELECT * FROM ers_users LEFT JOIN ers_reimbursements ON ers_users.u_id = ers_reimbursements.u_id_author ";
		get += "WHERE ers_users.ur_id = 2 ORDER BY id DESC";
		
	}

	public void getEmployeeReimbursements(int employeeId) throws SQLException {
		
		get = "SELECT * FROM ers_reimbursements WHERE u_id_author = ? ";
		get += "ORDER BY r_id DESC";	
		
	}

	public void getReimbursementsByStatus(int statusId) throws SQLException {
		
		get = "SELECT * FROM ers_reimbursments WHERE rt_status = ? ";
		get += "ORDER BY r_id DESC";
		
	}

	public void updateReimbursement(int reimbursementId, int statusId) throws SQLException {
		
		get = "UPDATE ers_reimbursements SET rt_status = ? ";
		get += "WHERE r_id = ?";
		
	}

	public void getAllEmployees() throws SQLException {
		
		get = "SELECT * FROM ers_users WHERE ur_id = 2 ";
		get += "ORDER BY u_id DESC";
		
	}

}
