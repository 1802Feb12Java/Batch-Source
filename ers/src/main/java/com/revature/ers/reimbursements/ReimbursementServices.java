package com.revature.ers.reimbursements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.ers.dao.ReimbursementDAO;
import com.revature.ers.util.ConnFactory;

public class ReimbursementServices implements ReimbursementDAO{

	public void addReimbursement(Reimbursement reimbursement) throws SQLException {
		//establish the connection
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		CallableStatement cs = null;
		
		//prepare the call
		String sql = "{CALL ADD_REIMBURSEMENT(?,?,?,?,?,?,?)}";
		cs = conn.prepareCall(sql);
		
		//populate the fields
		cs.setDouble(1, reimbursement.getR_amount());
		cs.setString(2, reimbursement.getR_description());
		cs.setBlob(3, reimbursement.getR_receipt());
		cs.setTimestamp(4,  reimbursement.getR_submitted());
		cs.setInt(5, reimbursement.getU_ID_Author());
		cs.setInt(6, reimbursement.getRt_Type());
		cs.setInt(7, reimbursement.getRt_Status());
		
		//execute the call
		cs.executeUpdate();
		
	}

	public Reimbursement getReimbursement(int r_id) throws SQLException {
		Reimbursement reimbursement = new Reimbursement();
		
		//establish the connection
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//prepare the statement
		String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE R_ID=?";
		ps = conn.prepareStatement(sql);
		
		ps.setInt(1, r_id);
		
		//execute the query
		rs = ps.executeQuery();
		
		if(!rs.next()) {
			return null;			
		}
		
		else {
			reimbursement.setR_amount(rs.getDouble("R_AMOUNT"));
			reimbursement.setR_description(rs.getString("R_DESCRIPTION"));
			reimbursement.setR_receipt(rs.getBlob("R_RECEIPT"));
			reimbursement.setR_submitted(rs.getTimestamp("R_SUBMITTED"));
			reimbursement.setR_resolved(rs.getTimestamp("R_RESOLVED"));
			reimbursement.setU_ID_Author(rs.getInt("U_ID_AUTHOR"));
			reimbursement.setU_ID_Resolver(rs.getInt("U_ID_RESOLVER"));
			reimbursement.setRt_Type(rs.getInt("RT_TYPE"));
			reimbursement.setRt_Status(rs.getInt("RT_STATUS"));
			return reimbursement;
		}
	}

	public void updateReimbursement(Reimbursement reimbursement) throws SQLException {
		//establish the connection
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		CallableStatement cs = null;
		
		//prepare the call
		String sql = "{CALL UPDATE_REIMBURSEMENT(?,?,?,?)}";
		cs = conn.prepareCall(sql);
		
		cs.setInt(1, reimbursement.getR_id());
		cs.setTimestamp(2, reimbursement.getR_resolved());
		cs.setInt(3, reimbursement.getU_ID_Resolver());
		cs.setInt(4,  reimbursement.getRt_Status());
		
		//execute the update
		cs.executeUpdate();
	}

	public void deleteReimbursement(int r_id) throws SQLException {
		// TODO Auto-generated method stub
	}

	public ArrayList<Reimbursement> getPendingReimbursements(int ur_id, int ur_role) throws SQLException {
		//establish the connection
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		ArrayList <Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reimbursement reimbursement = new Reimbursement();
	
		//prepare the statement
		if(ur_role == 1) {
			//view all pending for managers
			System.out.println("Hello manager");
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE RT_STATUS=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
		}
		else {
			//review pending for employee
			System.out.println("Hello employee");
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR=? AND RT_STATUS=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,ur_id);
			ps.setInt(2, 1);			
		}
		
		//execute the query
		rs = ps.executeQuery();

		if(!rs.next()) {
			return null;
		}
		while(rs.next()) {
			reimbursement.setR_id(rs.getInt("R_ID"));
			reimbursement.setR_amount(rs.getDouble("R_AMOUNT"));
			reimbursement.setR_description(rs.getString("R_DESCRIPTION"));
			reimbursement.setR_receipt(rs.getBlob("R_RECEIPT"));
			reimbursement.setR_submitted(rs.getTimestamp("R_RESOLVED"));
			reimbursement.setR_resolved(rs.getTimestamp("R_RESOLVED"));
			reimbursement.setRt_Type(rs.getInt("RT_TYPE"));
			reimbursement.setRt_Status(rs.getInt("RT_STATUS"));
			reimbursements.add(reimbursement);
			System.out.println(reimbursement.getR_id());
		}
		return reimbursements;
	}

	public ArrayList<Reimbursement> getResolvedReimbursements(int ur_id, int ur_role) throws SQLException {
		//establish the connection
		ConnFactory cf = new ConnFactory();
		Connection conn = cf.getConnection();
		ArrayList <Reimbursement> reimbursements = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reimbursement reimbursement = new Reimbursement();
		
		//prepare the statement
		if(ur_role == 1) {
			//view all pending for managers
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE RT_STATUS=? AND RT_STATUS=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 2);
			ps.setInt(2, 3);
		}
		else {
			//review pending for employee
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR=? AND RT_STATUS=? AND RT_STATUS=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ur_id);
			ps.setInt(2, 2);
			ps.setInt(2, 3);
		}
				
		//execute the query
		rs = ps.executeQuery();

		while(rs.next()) {
			reimbursement.setR_id(rs.getInt("R_ID"));
			reimbursement.setR_amount(rs.getDouble("R_AMOUNT"));
			reimbursement.setR_description(rs.getString("R_DESCRIPTION"));
			reimbursement.setR_receipt(rs.getBlob("R_RECEIPT"));
			reimbursement.setR_submitted(rs.getTimestamp("R_RESOLVED"));
			reimbursement.setR_resolved(rs.getTimestamp("R_RESOLVED"));
			reimbursement.setRt_Type(rs.getInt("RT_TYPE"));
			reimbursement.setRt_Status(rs.getInt("RT_STATUS"));
			reimbursements.add(reimbursement);
			System.out.println(reimbursement.getR_id());
		}
		return reimbursements;
	}
}
