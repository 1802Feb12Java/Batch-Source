package com.revature.reimbursements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReimbursementServices implements ReimbursementDAO {

	public void createReimbursement(Reimbursement r) throws SQLException {
		Connection c = ConnectionFactory.getInstance().getConnection();
		
		System.out.println(c);
		PreparedStatement stmt = c.prepareStatement("insert into ERS_REIMBURSEMENTS values (?,?,?,?,?,?,?,?,?,?)");
		PreparedStatement id = c.prepareStatement("SELECT REIMBURSEMENTIDSEQ.NEXTVAL FROM DUAL");
		ResultSet rs = id.executeQuery();
		int nextId = 0;
		if(rs.next())
		nextId = rs.getInt(1);
		stmt.setInt(1, nextId);
		stmt.setDouble(2, r.getR_amount());
		stmt.setString(3, r.getR_description());
		stmt.setString(4, null); //TODO blob if there is time. 
		stmt.setTimestamp(5, Timestamp.valueOf( LocalDateTime.now()));
		stmt.setTimestamp(6, null);
		stmt.setInt(7, r.getU_id_author());
		stmt.setNull(8, Types.INTEGER);
		stmt.setInt(9, r.getRt_type());
		stmt.setInt(10, 1);
	    stmt.executeQuery();
	}

	public Reimbursement readReimbursement(Connection c, int id) throws SQLException {
		PreparedStatement stmt = c.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS WHERE ID = (?)");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		Reimbursement temp = new Reimbursement();
		temp.setR_id(id);
		temp.setR_amount(rs.getDouble(2));
		temp.setR_description(rs.getString(3));
		//temp.setU_firstname(rs.getString(4));//blob for if time
		temp.setR_submitted(rs.getTimestamp(5));
		temp.setR_resolved(rs.getTimestamp(6));
		temp.setU_id_author(rs.getInt(7));
		temp.setU_id_resolver(rs.getInt(8));
		temp.setRt_type(rs.getInt(9));
		temp.setRt_status(rs.getInt(10));
		return temp;
	}

	public void updateReimbursement(Connection c, String toUpdate, String newValue, int id) throws SQLException {
		PreparedStatement stmt = c.prepareStatement("update ERS_REIMBURSEMENTS set (?) = (?) where R_id = (?) ");
		stmt.setString(1, toUpdate);
		stmt.setString(2, newValue);
		stmt.setInt(3, id);
		stmt.executeQuery();
	}

	public void deleteReimbursement(Connection c, int id) throws SQLException {
		// this functionality must be done by a database admin as deleting data is bad practice. 
		
	}

	public ArrayList<Reimbursement> getAllReimbursement() throws SQLException {
		Connection c = ConnectionFactory.getInstance().getConnection();
		
		System.out.println(c);
		PreparedStatement stmt = c.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS");
		ResultSet rs = stmt.executeQuery();
		ArrayList<Reimbursement> allReimbursements = new ArrayList<Reimbursement>();
		while (rs.next())
		{
			Reimbursement temp = new Reimbursement();
			temp.setR_id(rs.getInt(1));
			temp.setR_amount(rs.getDouble(2));
			temp.setR_description(rs.getString(3));
			//temp.setU_firstname(rs.getString(4));//TODO 
			temp.setR_submitted(rs.getTimestamp(5));
			temp.setR_resolved(rs.getTimestamp(6));
			temp.setU_id_author(rs.getInt(7));
			temp.setU_id_resolver(rs.getInt(8));
			temp.setRt_type(rs.getInt(9));
			temp.setRt_status(rs.getInt(10));
			allReimbursements.add(temp);
		}
		System.out.println(allReimbursements.toString());
		return allReimbursements;
	}

	public ArrayList<Reimbursement> getAll(int toget) throws SQLException {
Connection c = ConnectionFactory.getInstance().getConnection();
		
		System.out.println(c);
		PreparedStatement stmt = c.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR = (?)");
		stmt.setInt(1, toget);
		System.out.println();
		System.out.println();
		System.out.println("stmt:");
		System.out.println(stmt.toString());
		ResultSet rs = stmt.executeQuery();
		ArrayList<Reimbursement> allReimbursements = new ArrayList<Reimbursement>();
		while (rs.next())
		{
			Reimbursement temp = new Reimbursement();
			temp.setR_id(rs.getInt(1));
			temp.setR_amount(rs.getDouble(2));
			temp.setR_description(rs.getString(3));
			//temp.setU_firstname(rs.getString(4));//TODO 
			temp.setR_submitted(rs.getTimestamp(5));
			temp.setR_resolved(rs.getTimestamp(6));
			temp.setU_id_author(rs.getInt(7));
			temp.setU_id_resolver(rs.getInt(8));
			temp.setRt_type(rs.getInt(9));
			temp.setRt_status(rs.getInt(10));
			allReimbursements.add(temp);
		}
		System.out.println(allReimbursements.toString());
		return allReimbursements;
	}

	@Override
	public void createReimbursement(Connection c, Reimbursement r) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	

}
