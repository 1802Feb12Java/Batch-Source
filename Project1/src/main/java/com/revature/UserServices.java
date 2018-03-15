package com.revature;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServices implements UsersDAO{

	ConnectionFlexion cf = new ConnectionFlexion();
	Connection conn = cf.getConnection();
	
	public void addUser(Users u) throws SQLException {
		
		
	}

	public Users getUsers(int id) throws SQLException {
		String str = "SELECT * FROM ERS_USERS WHERE U_ID=?";
		PreparedStatement ps = conn.prepareStatement(str);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Users u = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
			return u;
		}
		return null;
	}

	public void updateUserPassword(Users u) throws SQLException {
		String str = "{CALL CHANGE_PASSWORD(?,?)}";
		CallableStatement cs = conn.prepareCall(str);
		cs.setInt(1, u.getId());
		cs.setString(2, u.getPassword());
		cs.execute();
		
	}

	public void updateUserEmail(Users u) throws SQLException {
		String str = "{CALL CHANGE_EMAIL(?,?)}";
		CallableStatement cs = conn.prepareCall(str);
		cs.setInt(1, u.getId());
		cs.setString(2, u.getEmail());
		cs.execute();
	}

	public void deleteUsers(int id) throws SQLException {
		
		
	}

	public ArrayList<Users> getAllUsers() throws SQLException {
		String str = "SELECT * FROM ERS_USERS";
		PreparedStatement ps = conn.prepareStatement(str);
		ResultSet rs = ps.executeQuery();
		ArrayList<Users> peeps = new ArrayList<Users>();
		while(rs.next()) {
			Users u = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
			peeps.add(u);
		}
		
		return peeps;
	}

	public Users login(String user, String pass) throws SQLException {
		String str = "SELECT * FROM ERS_USERS WHERE U_USERNAME=? AND U_PASSWORD=?";
		PreparedStatement ps = conn.prepareStatement(str);
		ps.setString(1, user);
		ps.setString(2, pass);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Users u = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
			return u;
		}
		return null;
	}
	
	
	public ArrayList<Users> getAllEmployees() throws SQLException {
		String str = "SELECT * FROM ERS_USERS WHERE UR_ID=2";
		PreparedStatement ps = conn.prepareStatement(str);
		ResultSet rs = ps.executeQuery();
		ArrayList<Users> peeps = new ArrayList<Users>();
		while(rs.next()) {
			Users u = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
			peeps.add(u);
		}
		
		return peeps;
	}
	
	public void approve(Users u, int id) throws SQLException {
		String str = "{CALL RESOLVE_R(?,?,?)}";
		CallableStatement cs = conn.prepareCall(str);
		cs.setInt(1, id);
		cs.setInt(2, u.getId());
		cs.setInt(3, 2);
		cs.execute();
	}
	
	public void deny(Users u, int id) throws SQLException {
		String str = "{CALL RESOLVE_R(?,?,?)}";
		CallableStatement cs = conn.prepareCall(str);
		cs.setInt(1, id);
		cs.setInt(2, u.getId());
		cs.setInt(3, 3);
		cs.execute();
	}
	
	public void contest(int id) throws SQLException {
			String str = "{CALL CONTEST_RESOLVE(?)}";
			CallableStatement cs = conn.prepareCall(str);
			cs.setInt(1, id);
			cs.execute();
		
	}
	
	public ArrayList<Reimbursement> getPending(Users u) throws SQLException {
		String str = "SELECT * FROM ERS_REIMBURSEMENTS WHERE RT_STATUS=1 AND U_ID_AUTHOR = ?";
		PreparedStatement ps = conn.prepareStatement(str);
		ps.setInt(1, u.getId());
		ResultSet rs = ps.executeQuery();
		ArrayList<Reimbursement> rems = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement r = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(5), 
					rs.getInt(7), rs.getInt(9), rs.getInt(10));
			r.setDescription(rs.getString(3));
			rems.add(r);
		}
		
		return rems;
	}
	
	//Gets all reimbursements from a specific user that have been resolved.
	//Includes denied and approved
	public ArrayList<Reimbursement> getApproved(Users u) throws SQLException {
		String str = "SELECT * FROM ERS_REIMBURSEMENTS WHERE (RT_STATUS=2 OR RT_STATUS=3) AND U_ID_AUTHOR = ?";
		PreparedStatement ps = conn.prepareStatement(str);
		ps.setInt(1, u.getId());
		ResultSet rs = ps.executeQuery();
		ArrayList<Reimbursement> rems = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement r = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(5), 
					rs.getInt(7), rs.getInt(9), rs.getInt(10));
			r.setDescription(rs.getString(3));
			r.setResolver(rs.getInt(8));
			r.setTimeResolved(rs.getString(6));
			rems.add(r);
		}
		
		return rems;
	}

	public ArrayList<Reimbursement> getAll(Users u) throws SQLException {
		String str = "SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR = ?";
		PreparedStatement ps = conn.prepareStatement(str);
		ps.setInt(1, u.getId());
		ResultSet rs = ps.executeQuery();
		ArrayList<Reimbursement> rems = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement r = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(5), 
					rs.getInt(7), rs.getInt(9), rs.getInt(10));
			r.setDescription(rs.getString(3));
			r.setResolver(rs.getInt(8));
			r.setTimeResolved(rs.getString(6));
			rems.add(r);
		}
		
		return rems;
	}
	
	public ArrayList<Reimbursement> getPending() throws SQLException {
		String str = "SELECT * FROM ERS_REIMBURSEMENTS WHERE RT_STATUS=1";
		PreparedStatement ps = conn.prepareStatement(str);	
		ResultSet rs = ps.executeQuery();
		ArrayList<Reimbursement> rems = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement r = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(5), 
					rs.getInt(7), rs.getInt(9), rs.getInt(10));
			r.setDescription(rs.getString(3));
			rems.add(r);
		}
		
		return rems;
	}
	
	public ArrayList<Reimbursement> getApproved() throws SQLException {
		String str = "SELECT * FROM ERS_REIMBURSEMENTS WHERE (RT_STATUS=2 OR RT_STATUS=3)";
		PreparedStatement ps = conn.prepareStatement(str);
		ResultSet rs = ps.executeQuery();
		ArrayList<Reimbursement> rems = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement r = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(5), 
					rs.getInt(7), rs.getInt(9), rs.getInt(10));
			r.setDescription(rs.getString(3));
			r.setResolver(rs.getInt(8));
			r.setTimeResolved(rs.getString(6));
			rems.add(r);
		}
		
		return rems;
	}
	
}
