package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Reimbursement;

/**
 * CRUD Ops for Employee POJO
 */
public interface ReimbursementDao {

	public void addReimbursement(Connection con, Reimbursement reimbursement) throws SQLException;

	public Reimbursement getReimbursement(Connection con, int reimbursementId) throws SQLException;

	public void updateReimbursement(Connection con, Reimbursement reimbursement) throws SQLException;

	public void deleteReimbursement(Connection con, int reimbursementId) throws SQLException;

	public List<Reimbursement> getAllReimbursements(Connection con) throws SQLException;
}
