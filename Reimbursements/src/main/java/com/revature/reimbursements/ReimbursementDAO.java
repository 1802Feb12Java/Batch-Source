package com.revature.reimbursements;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReimbursementDAO {
	public void createReimbursement(Connection c, Reimbursement r) throws SQLException;
    public Reimbursement readReimbursement(Connection c, int id) throws SQLException;
    public void updateReimbursement(Connection c, String toUpdate, String newValue, int id) throws SQLException;
    public void deleteReimbursement(Connection c, int id) throws SQLException;
    public ArrayList<Reimbursement> getAllReimbursement() throws SQLException;
}
