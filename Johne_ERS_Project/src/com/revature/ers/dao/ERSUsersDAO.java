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
import com.revature.ers.interfaces.ERSUsersInterface;
import com.revature.ers.model.Users;

public class ERSUsersDAO implements ERSUsersInterface{

	private ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public List<Users> getAllUsers() throws SQLException{
		List<Users> usersList = new LinkedList<Users>();
		try {
			String getUsersStr = "SELECT U_ID, U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ID "
					+ "FROM ERS_USERS";
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getUsersStr);
			
			while(rs.next()) {
				Users user = new Users();
				user.setU_id(rs.getInt("U_ID"));
				user.setU_username(rs.getString("U_USERNAME"));
				user.setU_password(rs.getString("U_PASSWORD"));
				user.setU_firstName(rs.getString("U_FIRSTNAME"));
				user.setU_lastName(rs.getString("U_LASTNAME"));
				user.setU_email(rs.getString("U_EMAIL"));
				user.setUr_id(rs.getInt("UR_ID"));
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all Users");
		}
		return usersList;
	}
	
	@Override
	public List<Users> viewAllUsers() throws SQLException {
		List<Users> usersList = new LinkedList<Users>();
		
		try {
			String sql = "SELECT * FROM VIEWUSERS";
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Users user = new Users();
				user.setU_id(rs.getInt("USER_ID"));
				user.setU_username(rs.getString("USERNAME"));
				user.setU_firstName(rs.getString("FIRSTNAME"));
				user.setU_lastName(rs.getString("LASTNAME"));
				user.setU_email(rs.getString("EMAIL_ADDRESS"));
				user.setUr_role(rs.getString("UR_ROLE"));
				usersList.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	@Override
	public List<Users> getUserFirstName() throws SQLException {
		List<Users> usersList = new LinkedList<Users>();
		try {
			String getUsersStr = "SELECT U_ID, U_FIRSTNAME "
					+ "FROM ERS_USERS";
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getUsersStr);
			
			while(rs.next()) {
				Users user = new Users();
				user.setU_id(rs.getInt("U_ID"));
				user.setU_firstName(rs.getString("U_FIRSTNAME"));
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all Users");
		}
		return usersList;
	}

	@Override
	public List<Users> getUserLastName() throws SQLException {
		List<Users> usersList = new LinkedList<Users>();
		try {
			String getUsersStr = "SELECT U_ID, U_LASTNAME "
					+ "FROM ERS_USERS";
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getUsersStr);
			
			while(rs.next()) {
				Users user = new Users();
				user.setU_id(rs.getInt("U_ID"));
				user.setU_lastName(rs.getString("U_LASTNAME"));
				
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all Users");
		}
		return usersList;
	}

	@Override
	public List<Users> getUserEmail() {
		List<Users> usersList = new LinkedList<Users>();
		try {
			String getUsersStr = "SELECT U_ID, U_EMAIL "
					+ "FROM ERS_USERS";
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getUsersStr);
			
			while(rs.next()) {
				Users user = new Users();
				user.setU_id(rs.getInt("U_ID"));
				user.setU_email(rs.getString("U_EMAIL"));
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all Users");
		}
		return usersList;
	}

	@Override
	public List<Users> getUserUsernameAndPassword() throws SQLException {
		List<Users> usersList = new LinkedList<Users>();
		try {
			String getUsersStr = "SELECT U_ID, U_USERNAME, U_PASSWORD "
					+ "FROM ERS_USERS";
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getUsersStr);
			
			while(rs.next()) {
				Users user = new Users();
				user.setU_id(rs.getInt("U_ID"));
				user.setU_username(rs.getString("U_USERNAME"));
				user.setU_username(rs.getString("U_PASSWORD"));
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all Users");
		}
		return usersList;
	}

	@Override
	public void updatePassword(Users user) throws SQLException {
		try {
			String updatePWStr = "UPDATE ERS_USERS SET U_PASSWORD = ? "
					+ "WHERE U_ID = ?";
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement(updatePWStr);
			ps.setString(1, user.getU_password());
			ps.setInt(2, user.getU_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUsername(Users user) throws SQLException {
		try {
			String updateUsernameStr = "UPDATE ERS_USERS SET U_USERNAME = ? "
					+ "WHERE U_ID = ?";
			Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
			PreparedStatement ps = conn.prepareStatement(updateUsernameStr);
			ps.setString(1, user.getU_username());
			ps.setInt(2, user.getU_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmail(Users user) throws SQLException {
		try {
			String updateUsernameStr = "UPDATE ERS_USERS SET U_EMAIL = ? "
					+ "WHERE U_ID = ?";
			Connection conn = cf.getConnection();
			PreparedStatement ps = conn.prepareStatement(updateUsernameStr);
			ps.setString(1, user.getU_email());
			ps.setInt(2, user.getU_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getAllPasswords() throws SQLException {
		List<String> usersList = new LinkedList<String>();
		try {
			String getUsersStr = "SELECT U_PASSWORD "
					+ "FROM ERS_USERS";
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getUsersStr);
			
			while(rs.next()) {
				Users user = new Users();
				user.setU_password(rs.getString("U_PASSWORD"));
				usersList.add(user.getU_password());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all passwords");
		}
		return usersList;
	}

	@Override
	public List<String> getAllUsernames() throws SQLException {
		List<String> usersList = new LinkedList<String>();
		try {
			String getUsersStr = "SELECT U_USERNAME "
					+ "FROM ERS_USERS";
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getUsersStr);
			
			while(rs.next()) {
				Users user = new Users();
				user.setU_username(rs.getString("U_USERNAME"));
				usersList.add(user.getU_username());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all Usernames");
		}
		return usersList;
	}

	@Override
	public List<Integer> getUserRoleID() throws SQLException {
		List<Integer> roleIDList = new LinkedList<>();
		try {
			String userRoleStr = "SELECT UR_ID FROM ERS_USERS";
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(userRoleStr);
			
			while(rs.next()) {
				Users user = new Users();
				user.setUr_id(rs.getInt("UR_ID"));
				roleIDList.add(user.getUr_id());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return roleIDList;
	}

	
}
