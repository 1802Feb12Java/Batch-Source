package com.revature.expensereimbursement.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.revature.expensereimbursement.model.ERSReimbursement;

public interface InterfaceERSReimubursementDAO {
	//Create/insert
	public boolean addReimbursement(ERSReimbursement reimbursement) throws SQLException;
	
	//READ/GET methods
	public List<ERSReimbursement> getAllReimbursements() throws SQLException;
	public ERSReimbursement getReimbursementsById(int reimbursementId) throws SQLException;
	public List<ERSReimbursement> getReimbursementsByAmount(double min, double max) throws SQLException;
	public List<ERSReimbursement> getReimbursementsByAuthor(int userId) throws SQLException;
	public List<ERSReimbursement> getReimbursementsByResolver(int userId) throws SQLException;
	public List<ERSReimbursement> getReimbursementsByType(int typeId) throws SQLException;
	public List<ERSReimbursement> getReimbursementsBySubmittedDate(Timestamp timestamp) throws SQLException;
	public List<ERSReimbursement> getReimbursementsByResolvedDate(Timestamp timestamp) throws SQLException;
	public List<ERSReimbursement> getReimbursementsByStatus(int statusId) throws SQLException;
	
	//Update
	public boolean updateReimbursement(ERSReimbursement reimbursement) throws SQLException;
	
	//Delete
	public boolean deleteReimbursement(int reimbursementId) throws SQLException;
}
