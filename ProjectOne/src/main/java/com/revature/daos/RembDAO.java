package com.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.connectionfactory.ConnectionManager;
import com.revature.controllers.SystemController;

public class RembDAO implements ReimbursementDAO {

	private static RembDAO instance;
	private Connection connection;
	
	private RembDAO() throws ClassNotFoundException, SQLException {
		super();
		System.out.println("RembDAO constrcutor called, should get connection.");
		this.connection = ConnectionManager.getInstance().getConnection();
	}
	
	public static RembDAO getInstance() throws ClassNotFoundException, SQLException {
		if(instance==null) {
			instance = new RembDAO();
		}
		return instance;
	}
	
	@Override
	public ArrayList<Reimbursement> getReimbursements(User user) throws SQLException {
		ArrayList<Reimbursement> reimb = new ArrayList<Reimbursement>();
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ERS_REIMBURSEMENTS FULL OUTER JOIN ERS_REIMBURSEMENT_STATUS ON ERS_REIMBURSEMENTS.RT_STATUS = ERS_REIMBURSEMENT_STATUS.RS_ID FULL OUTER JOIN ERS_REIMBURSEMENT_TYPE ON ERS_REIMBURSEMENTS.RT_TYPE = ERS_REIMBURSEMENT_TYPE.RT_ID INNER JOIN ERS_USERS ON ERS_REIMBURSEMENTS.U_ID_AUTHOR = ERS_USERS.U_ID";
		//String sql = "SELECT * FROM ERS_REIMBURSEMENTS";
		
		//only returns reimbursements for employee
		if(user.getAccess() < 1) {
			sql = sql + " WHERE U_ID_AUTHOR = ?";
		}
		PreparedStatement ps = connection.prepareStatement(sql);

		//only returns reimbursements for employee
		if(user.getAccess() < 1) {
			ps.setInt(1, user.getEmpId());
		} //else returns all reimbursements for a manager to view
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			//System.out.println("Found reimb, creating new object\n");
			Reimbursement reM = new Reimbursement();
			reM.setId(rs.getInt(1));
			reM.setAmount(rs.getDouble(2));
			reM.setDescript(rs.getString(3));
			reM.setSubmitted(rs.getDate(4));
			reM.setAuthor(rs.getInt(5));
			reM.setAuthor_username(rs.getString(16));
			reM.setResolver(rs.getInt(6));
			if(reM.getResolver().equals(0)) {
				reM.setResolver(null);
			}
			reM.setType(rs.getInt(7));
			reM.setTypeStr(rs.getString(14));
			reM.setStatus(rs.getInt(8));
			reM.setStatusStr(rs.getString(12));
			reM.setResovled(rs.getDate(9));
			reM.setReceipt(rs.getObject(10));
			reM.setAuthor_username(rs.getString(16));
			reM.setAuthor_fullname(rs.getString(18)+" "+rs.getString(19));
			reM.setAuthor_email(rs.getString(20));
//			reM.setResolver_name();
//			System.out.println("resolver: "+reM.getResolver());
		
			reimb.add(reM);
			
		}
		//System.out.println("reimbDAO: "+reimb.toString());
		return reimb;
	}

		
	@Override
	public void resolved(Integer id, Integer resolver, Integer status) throws SQLException {

		
		String sql = "{ call UPDATE_REIMBURSEMENT(?,?,?)}";
		CallableStatement cs = connection.prepareCall(sql);

		cs.setInt(1, id);
		cs.setInt(2, resolver);
		cs.setInt(3, status);
		cs.execute();
		
	}

	@Override
	public void addReimbursement(Reimbursement reimb) throws SQLException {
		
		
		String sql = "INSERT INTO ERS_REIMBURSEMENTS VALUES (NULL,?,?,CURRENT_TIMESTAMP,?, NULL,?,?,NULL,NULL)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setDouble(1, reimb.getAmount());
		ps.setString(2, reimb.getDescript());
		ps.setInt(3, reimb.getAuthor());
		ps.setInt(4, reimb.getType());
		ps.setInt(5, reimb.getStatus());
		
//		if(reimb.getImage()==null) {
//			ps.setBytes(6, null);
//		} else {
//			ps.setBytes(6, reimb.getImage());
//		}
		
		ResultSet rs = ps.executeQuery();
		
//		System.out.println("reimbursement should have been added");
		
	}

	@Override
	public byte[] getReimbursementImage(Integer id) throws SQLException {

		String sql = "SELECT R_RECEIPT FROM ERS_REIMBURSEMENTS WHERE R_ID=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getBytes(1);
		} else {
			return null;
		}
	}

}
