package com.revature.one.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.one.beans.User;
import com.revature.one.dao.UserDao;
import com.revature.one.database.ConnectionFactory;

public class UserDaoImpl implements UserDao {

	public void addUser(User user) throws SQLException {
		Connection con = null;
		CallableStatement s = null;
		String statement = "{CALL INS_REC(?,?)}";

		// CommonStatements.InsertIntoFill.apply(TABLE_NAME, TABLE_COLS.length);
		System.out.println("Created statement = " + statement + "\nwith user " + user);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareCall(statement);
			s.setInt(1, user.getId());
			s.setString(2, user.getName());

			s.execute();
			System.out.println("User " + user.getName() + " added!");
		} catch (SQLException e) {
			System.out.println("An SQL exception occurred 503");
		} finally {
			if (s != null)
				s.close();
			if (con != null)
				con.close();
		}

	}

	public User getUser(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement s = null;
		ResultSet rs = null;

		String statement = "SELECT * FROM ONEHOUR " + " WHERE ID=?" + " ";
		System.out.println("Created SQL Statement: " + statement + " With ID " + userId);

		try {
			con = ConnectionFactory.getInstance().getConnection();
			s = con.prepareStatement(statement);
			s.setInt(1, userId);

			rs = s.executeQuery();
			System.out.println("Fetched User with userId: " + userId + " from Database");

			if (rs.next())
				return processRow(rs);
		} finally {
			if (s != null)
				s.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		}
		return null;
	}

	private User processRow(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("ID"));
		user.setName(rs.getString("NAME"));
		return user;

	}

}
