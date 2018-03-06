package com.revature.dao;

import java.sql.SQLException;

public interface ManagerDao {
	
	public void getAccount(String username, String password) throws SQLException;
	
	public void getAllReimbursements() throws SQLException;

}
