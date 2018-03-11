package com.revature.ers.reimbursements;

import java.sql.SQLException;
import java.util.List;

import com.revature.ers.dao.ReimbursementDAO;

public class ReimbursementServices implements ReimbursementDAO{

	public boolean addReimbursement(Reimbursement reimbursement) throws SQLException {
		// TODO Au
		return false;
	}

	public Reimbursement getReimbursement() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateReimbursement(Reimbursement reimbursement) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteReimbursement(int r_id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Reimbursement> getPendingReimbursements() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> getResolvedReimbursements() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
