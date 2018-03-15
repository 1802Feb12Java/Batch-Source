package com.revature.ers.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.revature.ers.model.ReimbursementType;

public interface ERSReimbursementTypeInterface {
	
	/**
	 * get all reimbursement type
	 * @return
	 * @throws SQLException
	 */
	public List<ReimbursementType> getAllReimburseType() throws SQLException;
	
	/**
	 * update the status of the reimbursement
	 * @throws SQLException
	 */
	public void updateStatusType(ReimbursementType rType) throws SQLException;
	
}
