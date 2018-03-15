package com.revature.ers.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.revature.ers.model.ReimbursementStatus;

public interface ERSReimbursementStatusInterface {
	
	/**
	 * gets all reimbursement status
	 * @return
	 * @throws SQLException
	 */
	public List<ReimbursementStatus> getAllStatus() throws SQLException;
	
	/**
	 * updates the reimbursement status
	 * @throws SQLException
	 */
	public void updateStatus(ReimbursementStatus rStatus) throws SQLException;
	
}
