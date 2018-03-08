package com.revature.ers.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.ers.reimbursements.Reimbursement;

public interface ReimbursementDAO {
	//CRUD operations
	public boolean addReimbursement(Reimbursement reimbursement)throws SQLException;
	public Reimbursement getReimbursement()throws SQLException;
	public boolean updateReimbursement(Reimbursement reimbursement)throws SQLException;
	public boolean deleteReimbursement(int r_id)throws SQLException;
	public List<Reimbursement> getPendingReimbursements()throws SQLException;
	public List<Reimbursement> getResolvedReimbursements()throws SQLException;
}
