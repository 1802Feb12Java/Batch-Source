package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.ReimbursementStatus;

public class ReimbursementStatusDao {
	private ConnectionManager cm;
	
	/**
	 * Creates a DAO for acquiring info about reimbursement statuses.
	 * @throws SQLException	@see ConnectionManager.getInstance()
	 * @throws IOException	@see ConnectionManager.getInstance() 
	 */
	public ReimbursementStatusDao() throws SQLException, IOException {
		cm = ConnectionManager.getInstance();
	}
	
	/**
	 * Gets a reimbursement status from the database using the given ID.
	 * @param id	The ID of the reimbursement status to get.
	 * @return	The ReimbursementStatus represented by id.
	 * 			If the id does not exist, null is returned.
	 * @throws SQLException Error caused by connection or query parameters.
	 */
	public ReimbursementStatus getReimbursementStatusById(int id) throws SQLException {
		CallableStatement cs = null;
		ReimbursementStatus status = new ReimbursementStatus();
		String name = null;
		
		try {
			Connection conn = cm.getConnection();
			
			cs = conn.prepareCall("{? = call get_reimbursement_status_by_id(?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, id);
			cs.execute();
			
			name = cs.getString(1);
			
			status.setId(id);
			status.setStatus(name);
		} finally {
			if(cs != null) {
				cs.close();
			}
		}
		
		
		
		return name != null ? status : null;
	}
	
	/**
	 * Gets a ReimbursementStatus by name.
	 * @param name	The name of the ReimbursementStatus (case-insensitive).
	 * @return	The ReimbursementStatus with the given name.
	 * 			If it does not exist, null is returned.
	 * @throws SQLException	Error caused by connection or query parameters.
	 */
	public ReimbursementStatus getReimbursementStatusByName(String name) throws SQLException {
		CallableStatement cs = null;
		ReimbursementStatus status = new ReimbursementStatus();
		int id = 0;
		
		try {
			Connection conn = cm.getConnection();
			
			cs = conn.prepareCall("{? = call get_reimbursement_status_id_by_name(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, name);
			cs.execute();
			
			id = cs.getInt(1);
			status.setId(id);
			status.setStatus(name);
		} finally {
			if(cs != null) {
				cs.close();
			}
		}
		
		return id != 0 ? status : null;
	}
	
	/**
	 * Gets a list of all reimbursement statuses.
	 * @return	A list of all found reimbursement statuses.
	 * @throws SQLException	Error caused by connection.
	 */
	public List<ReimbursementStatus> getReimbursementStatuses() throws SQLException {
		List<ReimbursementStatus> statusList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Connection conn = cm.getConnection();
			
			ps = conn.prepareStatement("SELECT * FROM get_all_reimbursement_statuses();"); 
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				statusList.add(new ReimbursementStatus(id, name));
			}
		} finally {
			// Statement cleanup.
			if(rs != null) {
				rs.close();				
			}
			
			if(ps != null) {
				ps.close();
			}
		}
		
		return statusList;
	}
}
