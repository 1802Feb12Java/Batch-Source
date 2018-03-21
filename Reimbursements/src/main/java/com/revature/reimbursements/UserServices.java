package com.revature.reimbursements;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserServices implements UserDAO { 

//	private UserServices(){
//		super();	
//	}
//	
//	private static UserServices us = null;
//
//	public static synchronized UserServices getInstance(){
//		
//		if(us == null){	
//			us = new UserServices();	
//		}
//		return us;
//	}
	
	private Connection cf = ConnectionFactory.getInstance().getConnection();
	
	public void createUser(Connection c, User u) throws SQLException {
		PreparedStatement stmt = c.prepareStatement("insert into ERS_USERS values (?,?,?,?,?,?,?)");
		PreparedStatement id = c.prepareStatement("SELECT USERIDSEQ.NEXTVAL FROM DUAL");
		ResultSet rs = id.executeQuery();
		int nextId = 0;
		if(rs.next())
			nextId = rs.getInt(1);
		stmt.setInt(1, nextId);
		stmt.setString(2, u.getU_username());
		stmt.setString(3, u.getU_password());
		stmt.setString(4, u.getU_firstname());
		stmt.setString(5, u.getU_lastname());
		stmt.setString(6, u.getU_email());
		stmt.setInt(7, u.getUr_id());
	    stmt.executeQuery();
	}

	public User readUser(int id) throws SQLException {
		Connection c = ConnectionFactory.getInstance().getConnection();
		
		PreparedStatement stmt = c.prepareStatement("SELECT * FROM ERS_USERS WHERE U_ID = (?)");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		User temp = new User();
		if(rs.next()) {
		temp.setU_id(id);
		temp.setU_username(rs.getString(2));
		temp.setU_password(rs.getString(3));
		temp.setU_firstname(rs.getString(4));
		temp.setU_lastname(rs.getString(5));
		temp.setU_email(rs.getString(6));
		temp.setUr_id(rs.getInt(7));
		}
		return temp;
	}

	public void updateUser(String toUpdate, String newValue, int id) throws SQLException {
		Connection c = ConnectionFactory.getInstance().getConnection();
		System.out.println(id + " = ID");
		PreparedStatement stmt = c.prepareStatement("update ERS_USERS set U_PASSWORD = (?) where u_id = (?)");
		
		stmt.setString(1, newValue);
		stmt.setInt(2, id);
		//System.out.println(stmt.toString());
		stmt.executeQuery();
	}

	public void deleteUser(Connection c, int id) throws SQLException {
		//this must be done in the database by an admin. 
	}

	public ArrayList<User> getAllUsers() throws SQLException {//returns only last user... needs fixing
		Connection c = ConnectionFactory.getInstance().getConnection();
		
		System.out.println(c);
		PreparedStatement stmt = c.prepareStatement("SELECT * FROM ERS_USERS");
		ResultSet rs = stmt.executeQuery();
		ArrayList<User> allUsers = new ArrayList<User>();
		
		while (rs.next())
		{User temp = new User();
			temp.setU_id(rs.getInt(1));
			temp.setU_username(rs.getString(2));
			temp.setU_password(rs.getString(3));
			temp.setU_firstname(rs.getString(4));
			temp.setU_lastname(rs.getString(5));
			temp.setU_email(rs.getString(6));
			temp.setUr_id(rs.getInt(7));
			allUsers.add(temp);
		}
		
		return allUsers;
		
		
	}

	public void approve(int uID, int reimID) throws SQLException {

		Connection c = ConnectionFactory.getInstance().getConnection();
		System.out.println(c);
		PreparedStatement stmt = c.prepareStatement("update ERS_REIMBURSEMENTS set RT_STATUS = (?), U_ID_RESOLVER = (?), R_RESOLVED = (?) where R_ID = (?)");
		stmt.setInt(1, 2);
		stmt.setInt(2, uID);
		stmt.setTimestamp(3, Timestamp.valueOf( LocalDateTime.now()));
		stmt.setInt(4, reimID);
		stmt.executeQuery();
		
	}

	public void deny(int uID, int reimID) throws SQLException {

		Connection c = ConnectionFactory.getInstance().getConnection();
		System.out.println(c);
		PreparedStatement stmt = c.prepareStatement("update ERS_REIMBURSEMENTS set RT_STATUS = (?), U_ID_RESOLVER = (?) where R_ID = (?)");
		stmt.setInt(1, 3);
		stmt.setInt(2, uID);
		stmt.setInt(3, reimID);
		stmt.executeQuery();
	}

	@Override
	public void updateUser(Connection c, String toUpdate, String newValue, int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
