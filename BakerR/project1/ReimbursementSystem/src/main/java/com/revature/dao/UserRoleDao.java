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

import com.revature.bean.UserRole;

public class UserRoleDao {
	private ConnectionManager cm;
	
	/**
	 * Creates a DAO for acquiring info about user roles.
	 * @throws SQLException	@see ConnectionManager.getInstance()
	 * @throws IOException	@see ConnectionManager.getInstance() 
	 */
	public UserRoleDao() throws SQLException, IOException {
		cm = ConnectionManager.getInstance();
	}
	
	/**
	 * Gets a user role from the database using the given ID.
	 * @param id	The ID of the user to get.
	 * @return	The UserRole represented by id, if it exists. 
	 * 			If the id does not exist, null is returned.
	 * @throws SQLException	Error caused by connection or query parameters.
	 */
	public UserRole getUserRoleById(int id) throws SQLException {
		UserRole role = new UserRole();
		CallableStatement cs = null;
		String name = null;
		
		try {
			Connection conn = cm.getConnection();
			
			cs = conn.prepareCall("{? = call get_role_by_id(?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, id);
			cs.execute();
			
			name = cs.getString(1);
			
			role.setId(id);
			role.setRole(name);
		} finally {
			if(cs != null) {
				cs.close();
			}
		}
		
		return name != null ? role : null;
	}
	
	/**
	 * Gets a UserRole by name.
	 * @param name	The name of the user role (case-insensitive).
	 * @return	The UserRole with the given name.
	 * 			If it does not exist, null is returned.
	 * @throws SQLException	Error caused by connection or query parameters.
	 */
	public UserRole getUserRoleByName(String name) throws SQLException {
		UserRole role = new UserRole();
		CallableStatement cs = null;
		int id = 0;
		
		try {
			Connection conn = cm.getConnection();
			
			cs = conn.prepareCall("{? = call get_role_id_by_name(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, name);
			cs.execute();
			
			id = cs.getInt(1);
			
			role.setId(id);
			role.setRole(name);
		} finally {
			if(cs != null) {
				cs.close();
			}
		}
		
		return id != 0 ? role : null;
	}
	
	/**
	 * Gets a list of all user roles.
	 * @return	A list of all found user roles.
	 * @throws SQLException	Error caused by connection.
	 */
	public List<UserRole> getUserRoles() throws SQLException {
		List<UserRole> roleList = new ArrayList<>();
		PreparedStatement ps = null; 
		ResultSet rs = null;
		
		try {
			Connection conn = cm.getConnection();
			
			ps = conn.prepareStatement("SELECT * FROM get_all_user_roles();"); 
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String roleName = rs.getString("name");
				
				roleList.add(new UserRole(id, roleName));
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
		}
		
		return roleList;
	}

}
