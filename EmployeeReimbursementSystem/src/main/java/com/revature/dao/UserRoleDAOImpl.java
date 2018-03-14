package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.UserRole;
import com.revature.utility.ConnectionFactory;

public class UserRoleDAOImpl implements UserRoleDAO{

	public ArrayList<UserRole> getUsers() {
		// TODO Auto-generated method stub
		ArrayList<UserRole> userRoles = new ArrayList<UserRole>();
		
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_USER_ROLES");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				UserRole userRole = new UserRole();
				
				userRole.setUserRoleID(rs.getInt(1));
				userRole.setRole(rs.getString(2));
				
				userRoles.add(userRole);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			}
		
		return userRoles;
		
	}

	public void addUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ERS_USER_ROLES VALUES(?, ?)");
			
			ps.setInt(1, userRole.getUserRoleID());
			ps.setString(2, userRole.getRole());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			}
		
	}

	public void deleteUserRole(UserRole user) {
		// TODO Auto-generated method stub
		
	}

	public void updateUserRole(UserRole user) {
		// TODO Auto-generated method stub
		
	}

}
