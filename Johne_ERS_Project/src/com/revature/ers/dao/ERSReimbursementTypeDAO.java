package com.revature.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.ers.db.ConnFactory;
import com.revature.ers.interfaces.ERSReimbursementTypeInterface;
import com.revature.ers.model.ReimbursementStatus;
import com.revature.ers.model.ReimbursementType;

public class ERSReimbursementTypeDAO implements ERSReimbursementTypeInterface{

	private ConnFactory cf;
	
	@Override
	public List<ReimbursementType> getAllReimburseType() throws SQLException {
		List<ReimbursementType> reimburseTypeList = new LinkedList<>();
		try {
			String rTypeStr = "SELECT RT_ID, RT_TYPE FROM ERS_REIMBURSEMENT_TYPE";
			Connection conn = cf.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(rTypeStr);
			
			while(rs.next()) {
				ReimbursementType rt = new ReimbursementType();
				rt.setRt_id(rs.getInt("RT_ID"));
				rt.setRt_type(rs.getString("RT_TYPE"));
				
				reimburseTypeList.add(rt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimburseTypeList;
	}

	@Override
	public void updateStatusType(ReimbursementType rType) throws SQLException {
		try {
			String rTypeUpdate = "UPDATE ERS_REIMBURSEMENT_TYPE SET RT_TYPE = ? "
					+ "WHERE RT_ID = ?";
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement(rTypeUpdate);
			ps.setString(1, rType.getRt_type());
			ps.setInt(2, rType.getRt_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
