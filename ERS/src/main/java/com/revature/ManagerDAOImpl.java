package com.revature;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;

import com.revature.beans.Reimbursements;
import com.revature.beans.User;

public class ManagerDAOImpl implements ManagerDAO{

	public void approveRequest(int r, int u) throws SQLException{
		Connection connection = null;
		CallableStatement cs = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "{CALL APPROVE_REIMB (?,?)}";
			cs = connection.prepareCall(sql);
			cs.setInt(1, r);
			cs.setInt(2, u);
			
			cs.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (cs != null)
					cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void denyRequest(int r, int u) throws SQLException{
		Connection connection = null;
		CallableStatement cs = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "{CALL DENY_REIMB (?,?)}";
			cs = connection.prepareCall(sql);
			cs.setInt(1, r);
			cs.setInt(2, u);
			
			cs.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (cs != null)
					cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public ArrayList<Reimbursements> viewAllPending() throws SQLException{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Reimbursements> requests = new ArrayList<Reimbursements>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT ERS_REIMBURSEMENTS.R_ID, ERS_REIMBURSEMENTS.R_AMOUNT,ERS_REIMBURSEMENTS.R_DESCRIPTION, ERS_REIMBURSEMENTS.R_RECEIPT, ERS_REIMBURSEMENTS.R_SUBMITTED, ERS_REIMBURSEMENTS.U_ID_AUTHOR, ERS_REIMBURSEMENT_TYPE.RT_TYPE FROM ERS_REIMBURSEMENTS INNER JOIN ERS_REIMBURSEMENT_TYPE ON ERS_REIMBURSEMENTS.RT_TYPE = ERS_REIMBURSEMENT_TYPE.RT_ID WHERE RT_STATUS = 3";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursements r = new Reimbursements();
				byte[] receipt = rs.getBytes("R_RECEIPT");
				String encodedReceipt = Base64.encodeBase64String(receipt);
				r.setR_id(rs.getInt("R_ID"));
				r.setAmount(rs.getDouble("R_AMOUNT"));
				r.setDescription(rs.getString("R_DESCRIPTION"));
				r.setReceipt(encodedReceipt);
				r.setSubmitted(rs.getDate("R_SUBMITTED"));
				r.setU_id_author(rs.getInt("U_ID_AUTHOR"));
				r.setType(rs.getString("RT_TYPE"));
				
				requests.add(r);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return requests;
		
	}

	public ArrayList<String> viewImages() throws SQLException{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> receipts = new ArrayList<String>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT R_RECEIPT FROM ERS_REIMBURSMENTS";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String rcpt = rs.getString("R_RECEIPT");
				receipts.add(rcpt);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return receipts;
	}
	

	public ArrayList<Reimbursements> viewAllResolved() throws SQLException{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Reimbursements> rbmnts = new ArrayList<Reimbursements>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS INNER JOIN ERS_REIMBURSEMENT_STATUS ON ERS_REIMBURSEMENTS.RT_STATUS = ERS_REIMBURSEMENT_STATUS.RS_ID WHERE RT_STATUS != 3"; //might want to do a join here
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursements r = new Reimbursements();
				byte[] receipt = rs.getBytes("R_RECEIPT");
				String encodedReceipt = Base64.encodeBase64String(receipt);
				r.setR_id(rs.getInt("R_ID"));
				r.setAmount(rs.getDouble("R_AMOUNT"));
				r.setDescription(rs.getString("R_DESCRIPTION"));
				r.setReceipt(encodedReceipt);
				r.setSubmitted(rs.getDate("R_SUBMITTED"));
				r.setResolved(rs.getDate("R_RESOLVED"));
				r.setU_id_author(rs.getInt("U_ID_AUTHOR"));
				r.setU_id_resolver(rs.getInt("U_ID_RESOLVER"));
				r.setRt_type(rs.getInt("RT_TYPE"));
				r.setType(rs.getString("RS_STATUS"));
				
				rbmnts.add(r);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rbmnts;
		
	}
	
													
	public ArrayList<User> viewAllEmps() throws SQLException{ 
		Connection connection = null;		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<User> emps = new ArrayList<User>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT U_ID, U_USERNAME, U_FIRSTNAME, U_LASTNAME, U_EMAIL FROM ERS_USERS WHERE UR_ID = 1";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				User u = new User();
				u.setUserID(rs.getInt("U_ID"));
				u.setUsername(rs.getString("U_USERNAME"));
				u.setFirstname(rs.getString("U_FIRSTNAME"));
				u.setLastname(rs.getString("U_LASTNAME"));
				u.setEmail(rs.getString("U_EMAIL"));
				
				emps.add(u);
			}		
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emps;
	}
	
	public ArrayList<User> getAllUsers() throws SQLException{ 
		Connection connection = null;		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<User> emps = new ArrayList<User>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM ERS_USERS";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				User u = new User();
				u.setUserID(rs.getInt("U_ID"));
				u.setUsername(rs.getString("U_USERNAME"));
				u.setPassword(rs.getString("U_PASSWORD"));
				u.setFirstname(rs.getString("U_FIRSTNAME"));
				u.setLastname(rs.getString("U_LASTNAME"));
				u.setEmail(rs.getString("U_EMAIL"));
				u.setRoleID(rs.getInt("UR_ID"));
				
				emps.add(u);
			}		
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emps;
	}

		

	public ArrayList<Reimbursements> viewSingleRequest(int u) throws SQLException{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Reimbursements> rbmnts = new ArrayList<Reimbursements>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS INNER JOIN ERS_REIMBURSEMENT_STATUS ON ERS_REIMBURSEMENTS.RT_STATUS = ERS_REIMBURSEMENT_STATUS.RS_ID WHERE U_ID_AUTHOR = ?"; 
			ps = connection.prepareStatement(sql);
			ps.setInt(1, u);
			rs=ps.executeQuery();
			while(rs.next()) {
				Reimbursements r = new Reimbursements();
				byte[] receipt = rs.getBytes("R_RECEIPT");
				String encodedReceipt = Base64.encodeBase64String(receipt);
				r.setR_id(rs.getInt("R_ID"));
				r.setAmount(rs.getDouble("R_AMOUNT"));
				r.setDescription(rs.getString("R_DESCRIPTION"));
				r.setReceipt(encodedReceipt);
				r.setSubmitted(rs.getDate("R_SUBMITTED"));
				r.setResolved(rs.getDate("R_RESOLVED"));
				r.setU_id_author(rs.getInt("U_ID_AUTHOR"));
				r.setU_id_resolver(rs.getInt("U_ID_RESOLVER"));
				r.setRt_type(rs.getInt("RT_TYPE"));
				r.setType(rs.getString("RS_STATUS"));
					
				rbmnts.add(r);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rbmnts;
	}
	
}
