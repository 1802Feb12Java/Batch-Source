package com.revature.ers.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.ers.reimbursements.Reimbursement;

public interface ReimbursementDAO {
	//CRUD operations
	public void addReimbursement(Reimbursement reimbursement)throws SQLException;
	public Reimbursement getReimbursement(int r_id)throws SQLException;
	public void updateReimbursement(Reimbursement reimbursement)throws SQLException;
	public void deleteReimbursement(int r_id)throws SQLException;
	public List<Reimbursement> getPendingReimbursements(int ur_id, int ur_role)throws SQLException;
	public List<Reimbursement> getResolvedReimbursements(int ur_id, int ur_role)throws SQLException;
}
