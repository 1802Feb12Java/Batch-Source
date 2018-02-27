package com.revature.banking.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.jdbc.model.Admin;
import com.revature.banking.jdbc.utilities.DAOUtilities;

public class AdminDAO extends EmployeeDAO implements InterfaceAdminDAO {
	private static Connection  connection = null;
	private static PreparedStatement stmt = null;
	public List<Admin> getAllAdmins() {
		List<Admin> admins= new ArrayList<Admin>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKADMIN";
			stmt = connection.prepareStatement(sqlQuery);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Admin admin = new Admin(results.getInt("adminId"), results.getString("username"), results.getInt("password"),
						results.getString("email"));
				admins.add(admin);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAllAdmins");
			e.printStackTrace();
		}		
		return admins;
	}
	public Admin getAdminByAdminId(int adminId) {
		Admin admin = null;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKADMIN where adminId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, Integer.toString(adminId));
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				admin = new Admin(results.getInt("adminId"), results.getString("username"), results.getInt("password"),
						results.getString("email"));
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAdminByAdminId");
			e.printStackTrace();
		}
		return admin;
	}
	public Admin getAdminByUsername(String username) {
		Admin admin = null;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKADMIN where username=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, username);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				admin = new Admin(results.getInt("adminId"), results.getString("username"), results.getInt("password"),
						results.getString("email"));
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAdminByAdminUsername");
			e.printStackTrace();
		}
		return admin;
	}
	public List<Admin> getAdminByEmail(String email) {
		List<Admin> admins = new ArrayList<Admin>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKADMIN where email=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, email);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Admin admin = new Admin(results.getInt("adminId"), results.getString("username"), results.getInt("password"),
						results.getString("email"));
				admins.add(admin);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAdminByAdminEmail");
			e.printStackTrace();
		}
		return admins;
	}
	public static Admin getAdmin(int adminId) {
		Admin admin = null;
	
		return admin;
	}

	public boolean updateAdmin(Admin admin) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "UPDATE BANKADMIN SET username=?, password=?, email=? WHERE adminId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, admin.getUsername());
			stmt.setInt(2, admin.getPassword());
			stmt.setString(3, admin.getEmail());
			stmt.setInt(4, admin.getUserId());
			if( stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: updateAdmin");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public boolean addAdmin(Admin admin) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "INSERT INTO BANKADMIN (username, "
					+ "password, email)"
					+ "VALUES(?, ?, ?)";
			stmt = connection.prepareStatement(sqlQuery, new String[] {"adminId"});
			stmt.setString(1, admin.getUsername());
			stmt.setInt(2, admin.getPassword());
			stmt.setString(3, admin.getEmail());
			if( stmt.executeUpdate() != 0) {
				admin = getAdminByUsername(admin.getUsername());
				return true;
			}else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: addAdmin");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public boolean deleteAdmin(Admin admin) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "DELETE FROM BANKADMIN WHERE adminId=?, username=?, "
					+ "password=?, email=?)";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, admin.getUserId());
			stmt.setString(2, admin.getUsername());
			stmt.setInt(3, admin.getPassword());
			stmt.setString(4, admin.getEmail());
			if( stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: deleteAdmin");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public int getNumAdmins() {
		int count=0;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT count(*) FROM BANKADMIN";
			stmt = connection.prepareStatement(sqlQuery);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				count = results.getInt(1);
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getNumAdmins");
			e.printStackTrace();
		}
		
		return count;
	}

	
}
