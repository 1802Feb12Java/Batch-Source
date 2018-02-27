package com.revature.dao;

import com.revature.beans.*;
import java.sql.*;
import java.util.ArrayList;

public interface UserDAO {
	//insert into DB
	public void insertNewRecord(User u,String username, String password) throws SQLException;
	
	//update DB record
	public void updateRecord(User u) throws SQLException;
	
	//retrieve record from DB by userID 
	public User retrieveRecordByID(int userID) throws SQLException;
	
	//retrieve record from DB by username
	public boolean retrieveRecordByUsername(String username) throws SQLException;
	
	//retrieve record from DB by username and Password
	public User retrieveRecordByLogin(String username, String password) throws SQLException;
	
	//retrieve all User records from DB - superuser 
	public ArrayList<User> retrieveAllRows() throws SQLException;
	
	//delete record by user need to delete accounts too
	public void deleteRecord(User u) throws SQLException;

}
