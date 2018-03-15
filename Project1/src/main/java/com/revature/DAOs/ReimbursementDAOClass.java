package com.revature.DAOs;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;

import com.revature.beans.Reimbursement;

public class ReimbursementDAOClass implements ReimbursementDAO {
	private static Connection conn = null;

	public void createReimbursement(Reimbursement r) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("insert into ERS_REIMBURSEMENTS values (?,?,?,?,?,?,?,?,?,?)");
		PreparedStatement idps = conn.prepareStatement("select reimbursementidsequence.nextval from dual");
		ResultSet idrs = idps.executeQuery();
		int nextId = 0;
		if(idrs.next()) {
			nextId = idrs.getInt(1);
			r.setId(nextId);
		}
		PreparedStatement currTimePS = conn.prepareStatement("select current_timestamp from dual");
		ResultSet currTimeRS = currTimePS.executeQuery();
		Timestamp ts = null;
		if(currTimeRS.next()) {
			ts = currTimeRS.getTimestamp(1);
			r.setSubmitted(ts);
		}
		ps.setInt(1, nextId);
		ps.setDouble(2, r.getAmount());
		ps.setString(3, r.getDescription());
		
		if(!r.getBase64receipt().equals("")) {
			Blob b = conn.createBlob();
			b.setBytes(1, Base64.decodeBase64(r.getBase64receipt()));
			ps.setBlob(4, b);
		}
		else {
			ps.setNull(4, java.sql.Types.BLOB);	//set blob to null so if they didn't upload a picture
		}		
		
		ps.setTimestamp(5, ts);
		ps.setTimestamp(6, r.getResolved());	//this'll be null
		ps.setInt(7, r.getAuthorId());
		ps.setNull(8, java.sql.Types.INTEGER);	//set resolverId to null so it won't show up in the table
		ps.setInt(9, r.getTypeId());
		ps.setInt(10, r.getStatusID());
		
		ps.executeQuery();
	}

	public Reimbursement readReimbursement(int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS WHERE r_id = (?)");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		double amount = 0.00;
		String description = "";
		Blob receipt = null;
		Timestamp submitted = null;
		Timestamp resolved = null;
		int author = 0;
		int resolver = 0;
		int type = 0;
		int status = 0;
		
		String base64receipt = "";
		
		if(rs.next()) {
			amount = rs.getDouble(2);
			description = rs.getString(3);
			
			
			receipt = rs.getBlob(4);
			if(receipt != null) {
				int bloblength = (int) receipt.length();
				byte[] receiptBytes = receipt.getBytes(1, bloblength);
				base64receipt = Base64.encodeBase64String(receiptBytes);
			}
			else {
				base64receipt = null;
			}
			
			
			submitted = rs.getTimestamp(5);
			resolved = rs.getTimestamp(6);
			author = rs.getInt(7);
			resolver = rs.getInt(8);
			type = rs.getInt(9);
			status = rs.getInt(10);
		}
		return new Reimbursement(id, amount, description, base64receipt, submitted, resolved, author, resolver, type, status);
	}

	public void updateReimbursementStatus(int r_id, int newStatus, int resolverUid) throws SQLException {
		CallableStatement cs = conn.prepareCall("{call update_reimbursement_status(?,?,?)}");
		cs.setInt(1, r_id);
		cs.setInt(2, newStatus);
		cs.setInt(3, resolverUid);
		cs.executeQuery();
	}
	
	public void updateReimbursementString(String attribute, String newVal, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update ERS_REIMBURSEMENTS set (?) = (?) where r_id = (?) ");
		ps.setString(1, attribute);
		ps.setString(2, newVal);
		ps.setInt(3, id);
		ps.executeQuery();
	}

	public void updateReimbursementTimestamp(String attribute, Timestamp newVal, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update ERS_REIMBURSEMENTS set (?) = (?) where r_id = (?) ");
		ps.setString(1, attribute);
		ps.setTimestamp(2, newVal);
		ps.setInt(3, id);
		ps.executeQuery();		
	}
	
    public void updateReimbursementInt(String attribute, int newVal, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update ERS_REIMBURSEMENTS set (?) = (?) where r_id = (?) ");
		ps.setString(1, attribute);
		ps.setInt(2, newVal);
		ps.setInt(3, id);
		ps.executeQuery();
    }
	
	public ArrayList<Reimbursement> getAllReimbursements() throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS ORDER BY R_ID DESC");
		ResultSet rs = ps.executeQuery();
		ArrayList<Reimbursement> allReimbursements = new ArrayList<Reimbursement>();
		while (rs.next()) {
			int id = rs.getInt(1);
			double amount = rs.getDouble(2);
			String description = rs.getString(3);
			
			
			Blob receipt = rs.getBlob(4);
			String base64receipt = "";
			if(receipt != null) {
				int bloblength = (int) receipt.length();
				byte[] receiptBytes = receipt.getBytes(1, bloblength);
				base64receipt = Base64.encodeBase64String(receiptBytes);
			}
			else {
				base64receipt = null;
			}
			
			
			Timestamp submitted = rs.getTimestamp(5);
			Timestamp resolved = rs.getTimestamp(6);
			int author = rs.getInt(7);
			int resolver = rs.getInt(8);
			int type = rs.getInt(9);
			int status = rs.getInt(10);
			allReimbursements.add(new Reimbursement(id, amount, description, base64receipt, submitted, resolved, author, resolver, type, status));
		}
		
		return allReimbursements;
	}
		
	public ReimbursementDAOClass(Connection c){
		if(conn == null) {
			ReimbursementDAOClass.conn = c;
		}
	}

}
