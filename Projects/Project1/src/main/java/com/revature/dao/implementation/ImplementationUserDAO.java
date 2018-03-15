package com.revature.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.dao.UserDAO;

public class ImplementationUserDAO implements UserDAO {

	/**
	 * Uses provided username and password to check database if user exists
	 * 
	 * @param username
	 *            username of account being checked
	 * @param password
	 *            password of account being checked
	 * @return True if username/password combination exists
	 * @throws SQLException
	 */
	public boolean logIn(String username, String password) throws SQLException {
		boolean canLogIn = false;
		Connection c0 = ConnFactory.getInstance().getConnection();
		PreparedStatement ps1 = c0.prepareCall("SELECT * FROM USERS WHERE U_USERNAME=? AND U_PASSWORD=?");
		ps1.setString(1, username);
		ps1.setString(2, password);
		ResultSet rs1 = ps1.executeQuery();

		if (rs1.next()) {
			canLogIn = true;
		} else {
			canLogIn = false;
		}

		c0.close();
		return canLogIn;
	}

	/**
	 * Uses provided username to return information from database relating to that
	 * user
	 * 
	 * @param username
	 *            username of user being viewed
	 * @return an array corresponding to the information stored in the row on the
	 *         database relating to the specified user
	 * @throws SQLException
	 */
	public String[] getUserInformation(String username) throws SQLException {
		String[] oneUser = new String[5];
		Connection c4 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT DISTINCT U_USERNAME, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ROLE FROM ERS_USERS, ERS_USER_ROLES WHERE ers_users.ur_id = ERS_USER_ROLES.UR_ID AND U_USERNAME=?";
		PreparedStatement ps1 = c4.prepareStatement(sqlQ);
		ps1.setString(1, username);
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {
			oneUser[0] = rs1.getString(1);
			oneUser[1] = rs1.getString(2);
			oneUser[2] = rs1.getString(3);
			oneUser[3] = rs1.getString(4);
			oneUser[4] = rs1.getString(5);
		}

		c4.close();
		return oneUser;
	}

	public String getRole(String username) throws SQLException {
		String userRole = null;
		Connection c1 = ConnFactory.getInstance().getConnection();
		String qThis = "SELECT UR_ROLE FROM ERS_USER_ROLES, ERS_USERS WHERE ERS_USERS.U_USERNAME=? AND ERS_USERS.UR_ID=ERS_USER_ROLES.UR_ID";
		PreparedStatement ps2 = c1.prepareCall(qThis);
		ps2.setString(1, username);
		ResultSet rs2 = ps2.executeQuery();

		if (rs2.next()) {
			userRole = rs2.getString(1);
		}

		c1.close();
		return userRole;
	}

	public void updateInformation(String oldusername, String newusername, String password, String firstName, String lastName, String email)
			throws SQLException {
		Connection c3 = ConnFactory.getInstance().getConnection();
		PreparedStatement cs3 = c3.prepareCall("{call UPDATE_ERS_USER(?, ?, ?, ?, ?, ?)}");
		cs3.setString(1, oldusername);
		cs3.setString(2, newusername);
		cs3.setString(3, password);
		cs3.setString(4, firstName);
		cs3.setString(5, lastName);
		cs3.setString(6, email);
		cs3.execute();
		c3.close();
	}

	public String getAllUsers() throws SQLException {
		ArrayList<String[]> userList = new ArrayList<String[]>();
		Gson gsonBuilder = new GsonBuilder().create();
		Connection c4 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT DISTINCT U_USERNAME, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ROLE FROM ERS_USERS, ERS_USER_ROLES WHERE ers_users.ur_id = ERS_USER_ROLES.UR_ID ORDER BY U_LASTNAME";
		PreparedStatement ps1 = c4.prepareStatement(sqlQ);
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {
			String[] oneUser = new String[5];
			oneUser[0] = rs1.getString(1);
			oneUser[1] = rs1.getString(2);
			oneUser[2] = rs1.getString(3);
			oneUser[3] = rs1.getString(4);
			oneUser[4] = rs1.getString(5);
			userList.add(oneUser);
		}

		String jsonFromJavaArrayList = gsonBuilder.toJson(userList);
		// System.out.println(jsonFromJavaArrayList);

		/*
		 * for (int j = 0; j < userList.size(); j++) {
		 * System.out.println("--------------------------------------------------");
		 * String[] returnedList = userList.get(j); for (String s : returnedList) {
		 * System.out.println(s); } }
		 */

		c4.close();
		return jsonFromJavaArrayList;
	}

	public String getFirstName(String username) throws SQLException {
		Connection c5 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT DISTINCT U_FIRSTNAME FROM ERS_USERS WHERE U_USERNAME=?";
		PreparedStatement ps2 = c5.prepareStatement(sqlQ);
		ps2.setString(1, username);
		ResultSet rs1 = ps2.executeQuery();
		String firstName = "";
		while (rs1.next()) {
			firstName = rs1.getString(1);
		}
		c5.close();
		return firstName;
	}

}