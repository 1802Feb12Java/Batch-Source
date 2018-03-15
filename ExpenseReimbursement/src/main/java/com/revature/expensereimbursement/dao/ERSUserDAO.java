package com.revature.expensereimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.expensereimbursement.DAOUtilities;
import com.revature.expensereimbursement.model.ERSUser;

public class ERSUserDAO implements InterfaceERSUsersDAO {
	private static Connection  connection = null;
	private static PreparedStatement stmt = null;
	private List<ERSUser> getUsers() throws SQLException {
		try{
			List<ERSUser> users = new ArrayList<ERSUser>();
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				users.add(new ERSUser(results.getInt("U_ID"),
									results.getString("U_USERNAME"), results.getString("U_PASSWORD"),
									results.getString("U_FIRSTNAME"), results.getString("U_LASTNAME"),
									results.getString("U_EMAIL"), results.getString("UR_ROLE")));
			}
			return (users.size()>0 ) ? users: null;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
		
	}
	public List<ERSUser> getAllUsers() throws SQLException {
		connection = DAOUtilities.getConnection();
		String sqlQuery = "SELECT * FROM ERS_USERS_VIEW";
		ERSUserDAO.stmt = connection.prepareStatement(sqlQuery);
		return getUsers();
	}
	public ERSUser getUserByUserId(int id) throws SQLException {
		List<ERSUser> users = null;
		connection = DAOUtilities.getConnection();
		String sqlQuery = "SELECT * FROM ERS_USERS_VIEW WHERE U_ID = ?";
		stmt = connection.prepareStatement(sqlQuery);
		stmt.setInt(1, id);
		users = getUsers();
		return (users.size()!=0) ?users.get(0): null;
	}

	public ERSUser getUserByUsername(String username) throws SQLException {
		List<ERSUser> users = null;
		connection = DAOUtilities.getConnection();
		String sqlQuery = "SELECT * FROM ERS_USERS_VIEW WHERE U_USERNAME = ?";
		stmt = connection.prepareStatement(sqlQuery);
		stmt.setString(1, username);
		users = getUsers();
		return (users != null) ? users.get(0): null;
	}

	public boolean addUser(ERSUser user) throws SQLException {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "INSERT INTO ERS_USERS (U_ID, U_USERNAME, U_PASSWORD,U_FIRSTNAME, U_LASTNAME, UR_ID, U_EMAIL) "
					+ "VALUES( ?, ?, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, 0);
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getFirstName());
			stmt.setString(5, user.getLastName());
			stmt.setInt(6, user.getUserRoleId());
			stmt.setString(7, user.getEmail());
			return ( stmt.executeUpdate() != 0) ? true: false;
		} finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public boolean updateUser(ERSUser user) throws SQLException {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "UPDATE ERS_USERS SET U_USERNAME= ?, U_PASSWORD=?, "
					+ "U_FIRSTNAME=?, U_LASTNAME= ?, U_EMAIL= ? WHERE U_ID=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getUserId());
			return ( stmt.executeUpdate() != 0) ? true: false;
		} finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public boolean deleteUser(ERSUser user) {
		// TODO Auto-generated method stub
		return false;
	}

}
