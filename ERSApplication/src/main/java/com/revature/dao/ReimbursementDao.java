package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Reimbursement;

/**
 * CRUD Ops for Employee POJO
 */
public interface ReimbursementDao {

	public void addReimbursement(Reimbursement reimbursement) throws SQLException;

	public Reimbursement getReimbursement(int reimbursementId) throws SQLException;

	public void updateReimbursement(Reimbursement reimbursement) throws SQLException;

	public void deleteReimbursement(int reimbursementId) throws SQLException;

	public List<Reimbursement> getAllReimbursement() throws SQLException;
}
