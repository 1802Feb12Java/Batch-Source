package com.revature.dao.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.controllers.User;
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
	@Override
	public boolean logIn(String username, String password) throws SQLException {
		PreparedStatement ps1 = DatabaseConnection.conn
				.prepareCall("SELECT * FROM USERS WHERE U_USERNAME=? AND U_PASSWORD=?");
		ps1.setString(1, username);
		ps1.setString(2, password);
		ResultSet rs1 = ps1.executeQuery();

		return rs1.next();
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
	@Override
	public String[] getUserInformation(String username) throws SQLException {
		String[] returnThis = new String[7];
		PreparedStatement ps1 = DatabaseConnection.conn.prepareCall("SELECT * FROM USERS WHERE U_USERNAME=?");
		ps1.setString(1, username);
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {
			for (int i = 0; i < returnThis.length; i++) {
				returnThis[i] = rs1.getString(i);
			}
		}

		return returnThis;
	}

	@Override
	public String getRole() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInformation(String username) throws SQLException {
		// TODO Auto-generated method stub

	}

}