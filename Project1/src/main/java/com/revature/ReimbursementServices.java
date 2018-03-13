package com.revature;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ReimbursementServices implements ReimbursementDAO {
	
	ConnectionFlexion cf = new ConnectionFlexion();
	Connection conn = cf.getConnection();

	public void addReimbursement(Reimbursement r) throws SQLException {
		String str = "{CALL NEW_REIMBURSEMENT(?,?,?,?)}";
		CallableStatement cs = conn.prepareCall(str);
		cs.setDouble(1, r.getAmount());
		cs.setString(2, r.getDescription());
		cs.setInt(3, r.getAuthor());
		cs.setInt(4, r.getType());
		cs.execute();
	}

	public Reimbursement getReimbursement(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateReimbursement(Reimbursement r) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void deleteReimbursement(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Reimbursement> getAllReimbursements() throws SQLException {
		String str = "SELECT * FROM ERS_REIMBURSEMENTS";
		PreparedStatement ps = conn.prepareStatement(str);
		ResultSet rs = ps.executeQuery();
		ArrayList<Reimbursement> rems = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement r = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(5), 
					rs.getInt(7), rs.getInt(9), rs.getInt(10));
			r.setDescription(rs.getString(3));
			r.setResolver(rs.getInt(8));
			r.setTimeResolved(rs.getString(6));
			r.setReceipt(rs.getBlob(4));
			rems.add(r);
		}
		
		return rems;
	}
	
	public ArrayList<Reimbursement> getAllPending() throws SQLException {
		String str = "SELECT * FROM ERS_REIMBURSEMENTS WHERE RT_STATUS=1";
		PreparedStatement ps = conn.prepareStatement(str);
		ResultSet rs = ps.executeQuery();
		ArrayList<Reimbursement> rems = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement r = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(5), 
					rs.getInt(7), rs.getInt(9), rs.getInt(10));
			r.setDescription(rs.getString(3));
			r.setReceipt(rs.getBlob(4));
			rems.add(r);
		}
		
		return rems;
	}
	
}

	
	

