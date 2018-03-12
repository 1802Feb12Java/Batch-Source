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

import com.revature.bean.ReimbursementType;

public class ReimbursementTypeDao {
	private ConnectionManager cm;
	
	/**
	 * Creates a DAO for acquiring info about reimbursement types.
	 * @throws SQLException	@see ConnectionManager.getInstance()
	 * @throws IOException	@see ConnectionManager.getInstance() 
	 */
	public ReimbursementTypeDao() throws SQLException, IOException {
		cm = ConnectionManager.getInstance();
	}
	
	/**
	 * Gets a reimbursement type from the database using the given ID.
	 * @param id	The ID of the reimbursement type to get.
	 * @return	The ReimbursementType represented by id.
	 * 			If the id does not exist, null is returned.
	 * @throws SQLException Error caused by connection or query parameters.
	 */
	public ReimbursementType getReimbursementTypeById(int id) throws SQLException {
		ReimbursementType type = new ReimbursementType();
		CallableStatement cs = null;
		String name = null;
		Connection conn = cm.getConnection();
		
		try {
			cs = conn.prepareCall("{? = call get_reimbursement_type_by_id(?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, id);
			cs.execute();
			
			name = cs.getString(1);
			
			type.setId(id);
			type.setType(name);
		} finally {
			if(cs != null) { 
				cs.close();
			}
		}
		
		return name != null ? type : null;
	}
	
	/**
	 * Gets a ReimbursementType by name.
	 * @param name	The name of the ReimbursementType (case-insensitive).
	 * @return	The ReimbursementType with the given name.
	 * 			If it does not exist, null is returned.
	 * @throws SQLException	Error caused by connection or query parameters.
	 */
	public ReimbursementType getReimbursementTypeByName(String name) throws SQLException {
		ReimbursementType type = new ReimbursementType();
		CallableStatement cs = null;
		int id = 0;
		
		try {
			Connection conn = cm.getConnection();
			
			cs = conn.prepareCall("{? = call get_reimbursement_type_by_name(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, name);
			cs.execute();
			
			id = cs.getInt(1);
			
			type.setId(id);
			type.setType(name);
		} finally {
			if(cs != null) {
				cs.close();
			}
		}
		
		return id != 0 ? type : null;
	}
	
	/**
	 * Gets a list of all reimbursement types.
	 * @return	A list of all found reimbursement types.
	 * @throws SQLException	Error caused by connection.
	 */
	public List<ReimbursementType> getReimbursementTypes() throws SQLException {
		List<ReimbursementType> typeList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Connection conn = cm.getConnection();
			
			ps = conn.prepareStatement("SELECT * FROM get_all_reimbursement_types();"); 
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				typeList.add(new ReimbursementType(id, name));
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			
			if(ps != null) {
				ps.close();
			}
		}
		
		return typeList;
	}

}
