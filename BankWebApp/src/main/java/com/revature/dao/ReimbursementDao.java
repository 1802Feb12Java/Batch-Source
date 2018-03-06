package com.revature.dao;

import java.sql.SQLException;

public interface ReimbursementDao {
	
	public void getAllReimbursements(int employeeId) throws SQLException;

}
