package com.revature.expensereimbursement.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.expensereimbursement.model.ERSReimbursementStatus;

public interface InterfaceERSReimbursementStatusDAO {
	//CRUD
	public boolean createNewStatus(ERSReimbursementStatus status) throws SQLException;
	public List<ERSReimbursementStatus> getAllStatuses() throws SQLException;
	public ERSReimbursementStatus getStatusById(int id) throws SQLException;
	public List<ERSReimbursementStatus> getAllStatusByStatus(String status) throws SQLException;
	public boolean updateStatus(ERSReimbursementStatus status) throws SQLException;
	public boolean deleteStatus(int id) throws SQLException;
}
