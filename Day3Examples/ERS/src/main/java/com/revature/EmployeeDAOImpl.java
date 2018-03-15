package com.revature;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.codec.binary.Base64;

import com.revature.beans.Reimbursements;
import com.revature.beans.User;

public class EmployeeDAOImpl implements EmployeeDAO{

	public void submitRequest(double amount, String descrip, int author, int type) throws SQLException{
		Connection connection = null;
		CallableStatement cs = null;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "{CALL SUBMIT_REIMB (?,?,?,?)}";
			cs = connection.prepareCall(sql);
			cs.setDouble(1, amount);
			cs.setString(2, descrip);
			cs.setInt(3, author);
			cs.setInt(4, type);
			
			cs.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (cs != null)
					cs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
	}

	public void uploadReceipt(int r, byte[] b) throws SQLException{ 
		Connection connection = null;
		PreparedStatement ps = null;
		
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ERS_REIMBURSEMENTS SET R_RECEIPT = ? WHERE R_ID = ?";
			ps = connection.prepareStatement(sql);
			ps.setBytes(1, b);  
			ps.setInt(2, r);
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}	
	}
	
	
	public ArrayList<Reimbursements> viewPending(int u) throws SQLException{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Reimbursements> pending = new ArrayList<Reimbursements>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT ERS_REIMBURSEMENTS.R_ID, ERS_REIMBURSEMENTS.R_AMOUNT,ERS_REIMBURSEMENTS.R_DESCRIPTION, ERS_REIMBURSEMENTS.R_RECEIPT, ERS_REIMBURSEMENTS.R_SUBMITTED, ERS_REIMBURSEMENTS.U_ID_AUTHOR, ERS_REIMBURSEMENT_TYPE.RT_TYPE FROM ERS_REIMBURSEMENTS INNER JOIN ERS_REIMBURSEMENT_TYPE ON ERS_REIMBURSEMENTS.RT_TYPE=ERS_REIMBURSEMENT_TYPE.RT_ID WHERE U_ID_AUTHOR = ? AND RT_STATUS =3"; 
			ps = connection.prepareStatement(sql);
			ps.setInt(1, u);
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

				
				pending.add(r);
			}		
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pending;
		
	}

	public ArrayList<Reimbursements> viewResolved(int u) throws SQLException{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Reimbursements> resolved = new ArrayList<Reimbursements>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS INNER JOIN ERS_REIMBURSEMENT_STATUS ON ERS_REIMBURSEMENTS.RT_STATUS = ERS_REIMBURSEMENT_STATUS.RS_ID  WHERE U_ID_AUTHOR = ? AND RT_STATUS !=3"; 
			ps = connection.prepareStatement(sql);
			ps.setInt(1, u);
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
				
				resolved.add(r);
			}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resolved;
		
	}

	public ArrayList<User> viewInfo(int u) throws SQLException{
		Connection connection = null;				
		PreparedStatement ps = null;
		ResultSet rs = null;	
		ArrayList<User> user = new ArrayList<User>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM ERS_USERS WHERE U_ID = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, u);
			rs = ps.executeQuery();

			while(rs.next()) {
				User info = new User();
				info.setUserID(rs.getInt("U_ID"));
				info.setUsername(rs.getString("U_USERNAME"));
				info.setPassword(rs.getString("U_PASSWORD"));
				info.setFirstname(rs.getString("U_FIRSTNAME"));
				info.setLastname(rs.getString("U_LASTNAME"));
				info.setEmail(rs.getString("U_EMAIL"));
				info.setRoleID(rs.getInt("UR_ID"));

				user.add(info);
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
		return user;
		
	}

	public void updateInfo(int u, String un, String pw, String fn, String ln, String em) throws SQLException{ //unfinished; saved for later
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ERS_USERS SET U_USERNAME = ?, U_PASSWORD=?, U_FIRSTNAME=?, U_LASTNAME=?, U_EMAIL =? WHERE U_ID=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, un);
			ps.setString(2, pw);
			ps.setString(3, fn);
			ps.setString(4, ln);
			ps.setString(5, em);
			ps.setInt(6, u);
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
}
