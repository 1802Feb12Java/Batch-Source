package com.revature.dao;

import com.revature.beans.*;
import java.sql.*;
import java.util.ArrayList;

public interface AccountDAO {
	//insert into DB
	public void insertNewRecord(Account a) throws SQLException;
	
	//update DB record
	public void updateRecord(Account a) throws SQLException;
	
	//retrieve record from DB by userID - returns one
	public Account retrieveRecordByID(int accID) throws SQLException;
	
	//retrieve record from DB by account number - returns one
	public Account retrieveRecordByAccountNumber(String accNum) throws SQLException;
	
	//retrieve all Account records from DB by user - multiple
	public ArrayList<Account> retrieveAllRows(User u) throws SQLException;
	
	//delete records by account id
	public void deleteRecord(int acc_id) throws SQLException;

	//delete by owner id
	public void deleteRecordByOwner(int owner_id) throws SQLException;
}
