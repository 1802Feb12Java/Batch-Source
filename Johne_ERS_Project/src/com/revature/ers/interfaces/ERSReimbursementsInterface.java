package com.revature.ers.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.revature.ers.model.Reimbursements;

public interface ERSReimbursementsInterface {
	
	public List<Reimbursements> getAllReimbursements() throws SQLException;
	
	public Reimbursements selectReimbursements(int r_id) throws SQLException;
	
	public void submitReimbursements(Reimbursements re) throws SQLException;
	
	public void updateReimbursement(Reimbursements re) throws SQLException;
}
