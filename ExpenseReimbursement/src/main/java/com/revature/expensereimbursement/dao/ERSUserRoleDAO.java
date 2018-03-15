package com.revature.expensereimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.expensereimbursement.DAOUtilities;
import com.revature.expensereimbursement.model.ERSReimbursementType;
import com.revature.expensereimbursement.model.ERSUserRole;

public class ERSUserRoleDAO implements InterfaceERSUserRoleDAO {

	private static Connection  connection = null;
	private static PreparedStatement stmt = null;
	public List<ERSUserRole> getAllRoles() throws SQLException {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM ERS_USER_ROLES";
			ERSUserRoleDAO.stmt = connection.prepareStatement(sqlQuery);
			List<ERSUserRole> roles = new ArrayList<ERSUserRole>();
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				roles.add(new ERSUserRole(results.getInt("UR_ID"), results.getString("UR_ROLE")));
			}
			return (roles.size()>0) ? roles: null;
		} finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

}
