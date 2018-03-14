package com.revature.dao;

import java.util.ArrayList;

import com.revature.beans.Reimbursement;

public interface ReimbursementDAO {
	
	public ArrayList<Reimbursement> getReimbursements();
	public void addReimbursement(Reimbursement reimbursement);
	public void deleteReimbursement(Reimbursement reimbursement);
	public void updateReimbursement(Reimbursement reimbursement);
	
}
