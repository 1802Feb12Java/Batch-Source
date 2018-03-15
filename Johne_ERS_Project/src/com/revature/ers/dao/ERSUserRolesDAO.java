package com.revature.ers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.revature.ers.db.ConnFactory;
import com.revature.ers.interfaces.ERSUserRolesInterface;
import com.revature.ers.model.User_Roles;

public class ERSUserRolesDAO implements ERSUserRolesInterface{

	private ConnFactory cf = ConnFactory.getInstance();
	
	public List<User_Roles> getAllUserRoles() throws SQLException {
		List<User_Roles> usersList = new LinkedList<User_Roles>();
		try {
			String getUsersStr = "SELECT UR_ID, UR_ROLE FROM ERS_USER_ROLES";
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getUsersStr);
			
			while(rs.next()) {
				User_Roles user = new User_Roles();
				user.setUr_id(rs.getInt("UR_ID"));
				user.setUr_role(rs.getString("UR_ROLE"));
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all Users");
		}
		return usersList;
	}

	public List<User_Roles> getAllEmployees() throws SQLException {
		List<User_Roles> employeesList = new ArrayList<User_Roles>();
		try {
			String employeesStr = "SELECT UR_ID, UR_ROLE "
					+ "FROM ERS_USER_ROLES WHERE UR_ROLE = 'EMPLOYEE'";
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement(employeesStr);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User_Roles employee = new User_Roles();
				employee.setUr_id(rs.getInt("UR_ID"));
				employee.setUr_role(rs.getString("UR_ROLE"));
				employeesList.add(employee);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all Employees");
		}
		return employeesList;
	}

	public List<User_Roles> getAllManagers() throws SQLException {
		List<User_Roles> managersList = new ArrayList<User_Roles>();
		try {
			String managersStr = "SELECT UR_ID, UR_ROLE "
					+ "FROM ERS_USER_ROLES WHERE UR_ROLE = 'MANAGER'";
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement(managersStr);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User_Roles manager = new User_Roles();
				manager.setUr_id(rs.getInt("UR_ID"));
				manager.setUr_role(rs.getString("UR_ROLE"));
				managersList.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all managers");
		}
		return managersList;
	}

	public void createUserRole(User_Roles userRole) throws SQLException {
		String createStr = "INSERT INTO ERS_USER_ROLES (UR_ID, UR_ROLE) "
				+ "VALUES (?, ?)";
		try {
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement(createStr);
			
			ps.setInt(1, userRole.getUr_id());
			ps.setString(2, userRole.getUr_role());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error create user");
		}
	}

	public boolean exists(int id) throws SQLException {
		
		String idStr = "Select UR_ID FROM ERS_USER_ROLES ORDER BY UR_ID DESC";
		boolean exist = true;
		try {
			Connection conn = cf.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(idStr);
			
			while(rs.next()) {
				if(id == Integer.parseInt("UR_ID")) {
					exist = false;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("error checking if user exist");
		}
		
		return exist;
	}

	@Override
	public void updateUserRole(User_Roles user) throws SQLException {
		try {
			String updateUserRole = "UPDATE ERS_USER_ROLES SET UR_ROLE = ? "
					+ "WHERE UR_ID = ?";
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement(updateUserRole);
			ps.setString(1, user.getUr_role());
			ps.setInt(2, user.getUr_id());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
