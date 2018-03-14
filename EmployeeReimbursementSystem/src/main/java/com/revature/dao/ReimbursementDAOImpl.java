package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Reimbursement;
import com.revature.utility.ConnectionFactory;


public class ReimbursementDAOImpl implements ReimbursementDAO{

	static ConnectionFactory conn = ConnectionFactory.getInstance();
	
	public ArrayList<Reimbursement> getReimbursements() {
		// TODO Auto-generated method stub
		
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
				
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS");
			System.out.println("Executing");
			ResultSet rs = ps.executeQuery();
			System.out.println("Finished executing");
			while(rs.next()) {
				System.out.println("Retrieving");
				Reimbursement reimbursement = new Reimbursement();
				
				reimbursement.setReiumbursementID(rs.getInt(1));
				reimbursement.setAmount(rs.getDouble(2));
				reimbursement.setDescription(rs.getString(3));
				//reimbursement.setReciept(rs.getString(4));
				//reimbursement.setSubmitted(rs.getString(5));
				reimbursement.setUserIDAuthor(rs.getInt(6));
				reimbursement.setUserIDResolver(rs.getInt(7));
				reimbursement.setReimbursementTypeID(rs.getInt(8));
				reimbursement.setReimbursementStatusID(rs.getInt(9));
				
				reimbursements.add(reimbursement);
				System.out.println("retrieved reimbursement");
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed retrieving reimbursement");
			}
				
		return reimbursements;
	}
	
	public ArrayList<Reimbursement> getReimbursementsByUserID(int userID) {
		// TODO Auto-generated method stub
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
				
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR = ?");
			
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
					
			while(rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				
				reimbursement.setReiumbursementID(rs.getInt(1));
				reimbursement.setAmount(rs.getDouble(2));
				reimbursement.setDescription(rs.getString(3));
				//reimbursement.setReciept(rs.getString(4));
				//reimbursement.setSubmitted(rs.getString(5));
				reimbursement.setUserIDAuthor(rs.getInt(6));
				reimbursement.setUserIDResolver(rs.getInt(7));
				reimbursement.setReimbursementTypeID(rs.getInt(8));
				reimbursement.setReimbursementStatusID(rs.getInt(9));
				
				reimbursements.add(reimbursement);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			}
				
		return reimbursements;
	}
	
	public ArrayList<Reimbursement> getReimbursementsString() {
		// TODO Auto-generated method stub
		
		String query = "select ERS_REIMBURSEMENTS.R_AMOUNT, ERS_REIMBURSEMENTS.R_DESCRIPTION, ERS_REIMBURSEMENTS.R_RECEIPT, ERS_REIMBURSEMENTS.R_SUBMITTED, ERS_USERS.U_USERNAME AS U_ID_AUTHOR, "
				+ "ERS_REIMBURSEMENT_TYPE.RT_TYPE AS RT_TYPE, ERS_REIMBURSEMENT_STATUS.RS_STATUS AS RT_STATUS "
				+ "from ers_reimbursements "
				+ "left join ers_users "
				+ "on u_id_author = ers_users.u_id "
				+ "left join ers_reimbursement_type "
				+ "on ers_reimbursements.rt_type = ers_reimbursement_type.rt_id "
				+ "left join ers_reimbursement_status "
				+ "on ers_reimbursements.rt_status = ers_reimbursement_status.rs_id";
		
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
				
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			System.out.println("Executing");
			ResultSet rs = ps.executeQuery();
			System.out.println("Finished executing");
			while(rs.next()) {
				System.out.println("Retrieving");
				Reimbursement reimbursement = new Reimbursement();
				
				reimbursement.setAmount(rs.getDouble(1));
				reimbursement.setDescription(rs.getString(2));
				reimbursement.setReciept("none");
				reimbursement.setSubmitted("DATE");
				reimbursement.setUserIDAuthorString(rs.getString(5));
				reimbursement.setReimbursementTypeIDString(rs.getString(6));
				reimbursement.setUserIDResolverString(rs.getString(7));
//				reimbursement.setReimbursementStatusID(rs.getInt(9));
				
				reimbursements.add(reimbursement);
				System.out.println("retrieved reimbursement");
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed retrieving reimbursement");
			}
				
		return reimbursements;
	}

	public void addReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ERS_REIMBURSEMENTS Values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
					
			ps.setInt(1, reimbursement.getReiumbursementID());
			ps.setDouble(2, reimbursement.getAmount());
			ps.setString(3, reimbursement.getDescription());
			ps.setString(4, reimbursement.getReciept());
			ps.setString(5, reimbursement.getSubmitted());
			ps.setInt(6, reimbursement.getUserIDResolver());
			ps.setInt(7, reimbursement.getUserIDAuthor());
			ps.setInt(8, reimbursement.getReimbursementTypeID());
			ps.setInt(9, reimbursement.getReimbursementStatusID());
					
			ps.execute();
			System.out.println("Successfully added Reimbursement");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to add Reimbursement");
		}
		
	}
	
	//temporary
	public void addReimbursementNoImage(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ERS_REIMBURSEMENTS Values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			ps.setInt(1, reimbursement.getReiumbursementID());
			ps.setDouble(2, reimbursement.getAmount());
			ps.setString(3, reimbursement.getDescription());
			ps.setString(4, null);
			ps.setString(5, null);
			ps.setInt(6, reimbursement.getUserIDResolver());
			ps.setInt(7, reimbursement.getUserIDAuthor());
			ps.setInt(8, reimbursement.getReimbursementTypeID());
			ps.setInt(9, reimbursement.getReimbursementStatusID());
				
			System.out.println("EXECUTE");
			ps.execute();
			System.out.println("Successfully added Reimbursement");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to add Reimbursement");
		}
		
	}

	public void deleteReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		
	}

	public void updateReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE ERS_USERS R_ID = ?, R_AMOUNT = ?, R_DESCRIPTION = ?, R_RECIEPT = ?,"
					+ " R_SUBMITTED = ?, R_RESOLVED = ?, U_ID_AUTHOR = ?, U_ID_RESOLVER = ?, RT_TYPE = ?, RT_STATUS = ?");
			
			ps.setInt(1, reimbursement.getReiumbursementID());
			ps.setDouble(2, reimbursement.getAmount());
			ps.setString(3, reimbursement.getDescription());
			ps.setString(4, reimbursement.getReciept());
			ps.setString(5, reimbursement.getSubmitted());
			ps.setString(6, reimbursement.getResolved());
			ps.setInt(7, reimbursement.getUserIDAuthor());
			ps.setInt(8, reimbursement.getUserIDResolver());
			ps.setInt(9, reimbursement.getReimbursementTypeID());
			ps.setInt(10, reimbursement.getReimbursementStatusID());
				
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
