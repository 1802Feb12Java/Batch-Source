package com.revature.ers.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.ers.db.ConnFactory;
import com.revature.ers.interfaces.ERSReimbursementsInterface;
import com.revature.ers.model.Reimbursements;


public class ERSReimbursementsDAO implements ERSReimbursementsInterface{

	private ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public List<Reimbursements> getAllReimbursements() throws SQLException {
		List<Reimbursements> reimburseList = new LinkedList<>();
		try {
			String rStr = "SELECT R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, RT_TYPE, RS_STATUS, "
					+ "E_ID, AUTHOR_FIRSTNAME, AUTHOR_LASTNAME, " + 
					"M_ID, APPROVER_FIRSTNAME, APPROVER_LASTNAME " + 
					"FROM view_reimbursements";
			Connection conn = cf.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(rStr);
			
			while(rs.next()) {
				Reimbursements reim = new Reimbursements();
				reim.setR_id(rs.getInt("R_ID"));
				reim.setR_amount(rs.getDouble("R_AMOUNT"));
				reim.setR_receipt(rs.getBlob("R_RECEIPT"));;
				reim.setDescription(rs.getString("R_DESCRIPTION"));
				reim.setRt_type(rs.getInt("RT_TYPE"));
				reim.setRt_status(rs.getInt("RS_STATUS"));
				
				reim.setR_submitted(rs.getTimestamp("R_SUBMITTED"));
				reim.setR_resolved(rs.getTimestamp("R_RESOLVED"));
				
				reim.setU_id_author(rs.getInt("E_ID"));
				reim.setAuthor_firstname(rs.getString("AUTHOR_FIRSTNAME"));
				reim.setAuthor_lastname(rs.getString("AUTHOR_LASTNAME"));
				
				reim.setU_id_resolver(rs.getInt("M_ID"));
				reim.setResolver_firstname(rs.getString("APPROVER_FIRSTNAME"));
				reim.setResolver_lastname(rs.getString("APPROVER_LASTNAME"));
				
				reimburseList.add(reim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimburseList;
	}

	@Override
	public void submitReimbursements(Reimbursements re) throws SQLException {
		Connection connection = null;
		CallableStatement ps = null;
		try {
			connection = cf.getConnection();
			String sql = "{CALL insert_reimburse_procedure (?,?,?,?,?,?,?,?,?)}";
			ps = connection.prepareCall(sql);
		
			ps.setDouble(1, re.getR_amount());
			ps.setString(2, re.getDescription());
			ps.setBlob(3, re.getR_receipt());
			ps.setTimestamp(4, re.getR_submitted());
			ps.setTimestamp(5, re.getR_resolved());
			ps.setInt(6, re.getU_id_author());
			ps.setInt(7, re.getU_id_resolver());
			ps.setInt(8, re.getRt_type());
			ps.setInt(9, re.getRt_status());
			
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Reimbursements selectReimbursements(int r_id) throws SQLException {
		String sql = "SELECT R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, RT_TYPE, RS_STATUS, "
				+ "E_ID, AUTHOR_FIRSTNAME, AUTHOR_LASTNAME, " + 
				"M_ID, APPROVER_FIRSTNAME, APPROVER_LASTNAME " + 
				"FROM view_reimbursements";
		try {
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Reimbursements reim = new Reimbursements();
				reim.setR_id(rs.getInt("R_ID"));
				reim.setR_amount(rs.getDouble("R_AMOUNT"));
				reim.setDescription(rs.getString("R_DESCRIPTION"));
				reim.setR_receipt(rs.getBlob("R_RECEIPT"));
				reim.setR_submitted(rs.getTimestamp("R_SUBMITTED"));
				//reim.setR_resolved(rs.getTimestamp("R_RESOLVED"));
				reim.setRt_type(rs.getInt("RT_TYPE"));
				reim.setRt_status(rs.getInt("RS_STATUS"));
				reim.setU_id_author(rs.getInt("E_ID"));
				reim.setAuthor_firstname(rs.getString("AUTHOR_FIRSTNAME"));
				reim.setAuthor_lastname(rs.getString("AUTHOR_LASTNAME"));
				
				return reim;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateReimbursement(Reimbursements re) throws SQLException {
		String sql = "UPDATE ERS_REIMBURSEMENTS SET "
				+ "U_ID_RESOLVER = ?, "
				+ "RS_STATUS = ? "
				+ "WHERE R_ID = ? ";
		try {
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, re.getU_id_resolver());
			ps.setInt(2, re.getRt_status());
			ps.setInt(3, re.getR_id());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	

}
