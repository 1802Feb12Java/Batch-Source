package com.revature.services;

import java.sql.SQLException;

import com.revature.dao.ManagerDao;

public class ManagerServices implements ManagerDao {
	
	private String get;

	public void getAccount(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void getAllReimbursements() throws SQLException {
		
		get = "SELECT * FROM ers_users LEFT JOIN ers_reimbursements ON ers_users.u_id = ers_reimbursements.u_id_author ";
		get += "WHERE ers_users.ur_id = 2 ORDER BY id DESC";
		
	}

}
