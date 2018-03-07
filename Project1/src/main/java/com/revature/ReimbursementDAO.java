package com.revature;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReimbursementDAO {

	public void addReimbursement(Reimbursement r) throws SQLException;
	public Reimbursement getReimbursement(int id) throws SQLException;
	public void updateReimbursement(Reimbursement r) throws SQLException;
	public void deleteReimbursement(int id) throws SQLException;
	public ArrayList<Reimbursement> getAllReimbursements() throws SQLException; 
	
}
