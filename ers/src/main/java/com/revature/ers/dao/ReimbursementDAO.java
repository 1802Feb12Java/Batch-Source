package com.revature.ers.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.ers.reimbursements.Reimbursement;

public interface ReimbursementDAO {
	//CRUD operations
	public void addReimbursement(Reimbursement reimbursement)throws SQLException;
	public Reimbursement getReimbursement()throws SQLException;
	public void updateReimbursement(Reimbursement reimbursement)throws SQLException;
	public void deleteReimbursement(int r_id)throws SQLException;
	public List<Reimbursement> getPendingReimbursements()throws SQLException;
	public List<Reimbursement> getResolvedReimbursements()throws SQLException;
}
