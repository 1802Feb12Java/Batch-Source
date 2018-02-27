package com.revature.bank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginServices implements LoginDAO{

	ConnectionFlexion cf = new ConnectionFlexion();
	Connection conn = cf.getConnection();
	
	@Override
	public void addLogin(String u, String p, String t) throws SQLException {
		String str ="{CALL NEW_LOGIN(?,?,?)}";
		CallableStatement cs2 = conn.prepareCall(str);
		cs2.setString(1, u);
		cs2.setString(2, p);
		cs2.setString(3, t);
		cs2.execute();
		
	}

	@Override
	public String getLogin(String u, String p) throws SQLException {
		String str = "SELECT * FROM LOGIN_INFO";
		PreparedStatement ps = conn.prepareStatement(str);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String user = rs.getString(1);
			String pass = rs.getString(2);
			if(user.equals(u) && pass.equals(p)) {
				return rs.getString(3);
			}
		}
		System.out.println("That is not a valid login.");
		System.out.println("Make sure you are trying to login to the right portal.");
		return null;
	}

	@Override
	public void updateLogin(String u, String p) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLogin(String username) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> getAllLogin() throws SQLException {
		String str = "SELECT * FROM LOGIN_INFO";
		PreparedStatement ps = conn.prepareStatement(str);
		ResultSet rs = ps.executeQuery();
		ArrayList<String> info = new ArrayList<String>();
		while(rs.next()) {
			String user = rs.getString(1);
			String pass = rs.getString(2);
			String complete = "Username: " + user + ", Password: " + pass;
			info.add(complete);
		}
		return info;
	}

}
