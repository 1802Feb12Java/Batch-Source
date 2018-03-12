package com.revature.DAOs;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.revature.beans.Reimbursement;

public interface ReimbursementDAO {
	public void createReimbursement(Reimbursement r) throws SQLException;
    public Reimbursement readReimbursement(int id) throws SQLException;
    public void updateReimbursementString(String attribute, String newVal, int id) throws SQLException;
    public void updateReimbursementStatus(int newStatus, int r_id, int resolverUid) throws SQLException;
    public void updateReimbursementTimestamp(String attribute, Timestamp newVal, int id) throws SQLException;
    public void updateReimbursementInt(String attribute, int newVal, int id) throws SQLException;
    public ArrayList<Reimbursement> getAllReimbursements() throws SQLException;
}
