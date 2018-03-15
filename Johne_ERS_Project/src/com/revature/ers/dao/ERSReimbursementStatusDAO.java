package com.revature.ers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.ers.db.ConnFactory;
import com.revature.ers.interfaces.ERSReimbursementStatusInterface;
import com.revature.ers.model.ReimbursementStatus;

public class ERSReimbursementStatusDAO implements ERSReimbursementStatusInterface{

	private ConnFactory cf = ConnFactory.getInstance(); 
	@Override
	public List<ReimbursementStatus> getAllStatus() throws SQLException {
		List<ReimbursementStatus> reimburseList = new LinkedList<>();
		try {
			String rStatusStr = "SELECT RS_ID, RS_STATUS FROM ERS_REIMBURSEMENT_STATUS";
			Connection conn = cf.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(rStatusStr);
			
			while(rs.next()) {
				ReimbursementStatus rst = new ReimbursementStatus();
				rst.setRs_id(rs.getInt("RS_ID"));
				rst.setRs_status(rs.getString("RS_STATUS"));
				
				reimburseList.add(rst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimburseList;
	}

	@Override
	public void updateStatus(ReimbursementStatus rStatus) throws SQLException {

		try {
			String rStatusUpdate = "UPDATE ERS_REIMBURSEMENTS_STATUS SET RS_STATUS = ? "
					+ "WHERE RS_ID = ?";
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement(rStatusUpdate);
			ps.setString(1, rStatus.getRs_status());
			ps.setInt(2, rStatus.getRs_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
}
