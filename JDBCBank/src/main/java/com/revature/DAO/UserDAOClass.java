package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOClass implements UserDAO {
	Connection conn;

	@Override
	public ResultSet readAllUsers() throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select * from usertable");
		return ps.executeQuery();
	}

	@Override
	public ResultSet readUser(int user_id) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("select * from usertable where user_id = ?");
		ps.setInt(1, user_id);
		
		return ps.executeQuery();
	}

	@Override
	public int insertUser(int accesslevel, String username, String password, String fullname) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("Insert into usertable values (?,?,?,?,?)");
		PreparedStatement idps = conn.prepareStatement("select useridsequence.nextval from dual");
		ResultSet rs = idps.executeQuery();
		int myID = 0;
		if(rs.next())
			myID = rs.getInt(1);
		ps.setInt(1, myID);
		ps.setInt(2, accesslevel);
		ps.setString(3, username);
		ps.setString(4, password);
		ps.setString(5, fullname);
		ps.executeQuery();  
		
		
		return myID;
	}

	@Override
	public void deleteUser(int user_id) throws SQLException{
		CallableStatement cs = conn.prepareCall("{call user_delete_procedure(?)}");
		cs.setInt(1, user_id);
		cs.executeQuery();
	}
	
	public UserDAOClass(Connection conn){
		this.conn = conn;
	}

}
