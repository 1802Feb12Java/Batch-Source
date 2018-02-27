package com.revature.services;

import java.sql.*;
import java.util.ArrayList;
import com.revature.beans.User;
import com.revature.dao.UserDAO;
import com.revature.util.ConnFactory;
import javafx.util.Pair;

/*
 * User Services class
 * DAO implementation for the User abstract class
 * 		works for all subclasses
*/
public class UserServices implements UserDAO{
	
	/*
	 * Insert New Record for Users
	*/
	public void insertNewRecord(User u,String username, String password) throws SQLException {
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();
		
		//Prepared statement for inserting user and getting SEQ
		PreparedStatement insertPrepared = null;
		String insertString = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		String getSeq = "SELECT USER_ID.NEXTVAL FROM DUAL";
		int user_id = 0;
		//request sequence from DB
		insertPrepared = conn.prepareStatement(getSeq);
		ResultSet rs = insertPrepared.executeQuery();
		if(rs.next()) {
			user_id = rs.getInt(1);
		}
		//set up statement for insert
		insertPrepared= conn.prepareStatement(insertString);
		//set all fields for insert
		//set user_id
		insertPrepared.setInt(1,user_id);
		//access level should default to 0
		//only admins should be able to change this from another method
		insertPrepared.setInt(2,0);
		//set name, first then last
		insertPrepared.setString(3, u.getName().getKey());
		insertPrepared.setString(4, u.getName().getValue());
		//set username and password
		insertPrepared.setString(5, username);
		insertPrepared.setString(6, password);
		//set addresses
		//address, city, state, zip, country
		String fullAddr = u.getAddress();
		String[] subAddr = fullAddr.split(", ");
		insertPrepared.setString(7, subAddr[0]);  //address
		insertPrepared.setString(8, subAddr[1]);  //city
		insertPrepared.setString(10, subAddr[2]); //state
		insertPrepared.setString(9, subAddr[3]);  //zip
		insertPrepared.setString(11, subAddr[4]); //country
		//set phone number
		insertPrepared.setString(12, u.getPhone());

		//execute insert
		insertPrepared.executeUpdate();
		
		//close connection
		conn.close();
	}

	/*
	 * Update an existing record for Users
	*/
	public void updateRecord(User u) throws SQLException {
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();

		//Prepared statement for updating user info
		PreparedStatement updatePrepared = null;
		String updateString = "UPDATE users SET firstname=?, lastname=?, address=?, city=?, zip=?, state=?, country=?, phone=? WHERE userid=?";
		
		//set up statement for update
		updatePrepared= conn.prepareStatement(updateString);
		//set all fields for update
		//set name, first then last
		updatePrepared.setString(1, u.getName().getKey());
		updatePrepared.setString(2, u.getName().getValue());
		//set addresses
		//address, city, state, zip, country
		String fullAddr = u.getAddress();
		String[] subAddr = fullAddr.split(", ");
		updatePrepared.setString(3, subAddr[0]); //address
		updatePrepared.setString(4, subAddr[1]); //city
		updatePrepared.setString(6, subAddr[2]); //state
		updatePrepared.setString(5, subAddr[3]); //zip
		updatePrepared.setString(7, subAddr[4]); //country
		//set phone number
		updatePrepared.setString(8, u.getPhone());
		
		//set for WHERE clause
		updatePrepared.setInt(9, u.getUserID());
		updatePrepared.executeUpdate();
		//close connection
		conn.close();
	}
	
	//helper method for making new user from ResultSet 
	private User setUserFromRS(ResultSet rs) throws SQLException {
		User u = new User();
		//set all user info
		//user id
		u.setUserID(rs.getInt(1));
		//access level
		u.setAccessLevel(rs.getInt(2));
		//name <firstname,lastname>
		u.setName(new Pair<String,String>(rs.getString(3),rs.getString(4)));
		//address, city, state, zip, country
		u.setAddress(rs.getString(7) + ", " + 
					 rs.getString(8) + ", " + 
					 rs.getString(10) + ", " + 
					 rs.getString(9) + ", " +
					 rs.getString(11));
		u.setPhone(rs.getString(12));
		return u;
	}
	
	//retrieve record from DB by userID 
	public User retrieveRecordByID(int userID) throws SQLException {
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();
		
		//Prepared statement for select user by id
		PreparedStatement selectPrepared = null;
		String selectString = "SELECT * FROM users WHERE userid=?";
		//set up statement for select
		selectPrepared= conn.prepareStatement(selectString);
		//fill in user id
		selectPrepared.setInt(1, userID);
		ResultSet rs = selectPrepared.executeQuery();
		
		User u = null;
		if(rs.next()) {//make new user class
			u = setUserFromRS(rs);
		}
		
		//close connection
		conn.close();
		return u;
	}
	
	//retrieve record from DB by username
	public boolean retrieveRecordByUsername(String username) throws SQLException{
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();
		
		//Prepared statement for select user by id
		PreparedStatement selectPrepared = null;
		String selectString = "SELECT * FROM users WHERE username=?";
		//set up statement for select
		selectPrepared= conn.prepareStatement(selectString);
		//fill in user id
		selectPrepared.setString(1, username);
		ResultSet rs = selectPrepared.executeQuery();
		
		boolean exist;
		if(rs.next()) {
			exist = true;
		}
		else
			exist = false;
		
		//close connection
		conn.close();
		return exist;
	}
	
	//retrieve record from DB by username and Password
	public User retrieveRecordByLogin(String username, String password) throws SQLException{
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();
		
		//Prepared statement for select user by id
		PreparedStatement selectPrepared = null;
		String selectString = "SELECT * FROM users WHERE username=? AND password=?";
		//set up statement for select
		selectPrepared= conn.prepareStatement(selectString);
		//fill in user id
		selectPrepared.setString(1, username);
		selectPrepared.setString(2, password);
		ResultSet rs = selectPrepared.executeQuery();
		
		User u = null;
		if(rs.next()) {//make new user class
			u = setUserFromRS(rs);
		}
		
		//close connection
		conn.close();
		return u;
	}

	//retrieve all rows of users from DB - superUser  
	public ArrayList<User> retrieveAllRows() throws SQLException{
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();
		
		//Prepared statements
		PreparedStatement selectPrepared = null;
		String selectString = "SELECT * FROM users";
		String getCurSeq = "SELECT USER_ID.NEXTVAL FROM DUAL";
		//set up getCurSeq
		selectPrepared = conn.prepareStatement(getCurSeq);
		ResultSet rs = selectPrepared.executeQuery();
		int tableSize = 0;
		if(rs.next())
			tableSize = rs.getInt(1);
		//set up statement for select
		selectPrepared = conn.prepareStatement(selectString);
		rs = selectPrepared.executeQuery();
		
		ArrayList<User> userList = new ArrayList<User>(tableSize);
		while(rs.next()) {
			userList.add(setUserFromRS(rs));
		}
		
		//close connection
		conn.close();
		return userList;
	}
	
	//deleting user... take care of referential integrity 
	public void deleteRecord(User u) throws SQLException {
		//delete all related accounts first 
		AccountServices as = new AccountServices();
		as.deleteRecordByOwner(u.getUserID());
		
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();
		//Prepared statements
		PreparedStatement deletePrepared = null;
		String deleteString = "DELETE FROM users WHERE userid=?";
		//setup statement for delete
		deletePrepared = conn.prepareStatement(deleteString);
		deletePrepared.setInt(1, u.getUserID());
		
		//execute delete
		deletePrepared.executeUpdate();
		
		//close connection
		conn.close();
	}
	
}
