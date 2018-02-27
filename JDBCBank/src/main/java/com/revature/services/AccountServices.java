package com.revature.services;

import java.sql.*;
import java.util.ArrayList;
import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.util.ConnFactory;


public class AccountServices implements AccountDAO{

	/*
	 * Insert New Record for Accounts
	*/
	public void insertNewRecord(Account a) throws SQLException {
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();
		
		//Prepared statement for inserting user and getting SEQ
		PreparedStatement insertPrepared = null;
		String insertString = "INSERT INTO accounts VALUES (?,?,?,?,?,?,?)";
		String getSeq = "SELECT ACCOUNT_ID.NEXTVAL FROM DUAL";
		int account_id = 0;
		//request sequence from DB
		insertPrepared = conn.prepareStatement(getSeq);
		ResultSet rs = insertPrepared.executeQuery();
		if(rs.next()) {
			account_id = rs.getInt(1);
		}
		//set up statement for insert
		insertPrepared= conn.prepareStatement(insertString);
		//set all fields for insert
		//set account_id
		insertPrepared.setInt(1,account_id);
		//set owner
		insertPrepared.setInt(2, a.getOwner().getUserID());
		//set account number
		insertPrepared.setString(3, a.getAccountNumber());
		//set balance
		insertPrepared.setDouble(4, a.getBalance());
		//set is joint & joint owner
		if(a.isJoint()) {
			insertPrepared.setInt(5, 1);
			insertPrepared.setInt(6, a.getJointOwner().getUserID());
		}
		else {
			insertPrepared.setInt(5, 0);
			insertPrepared.setInt(6,0); //FIX THIS?
		}
		//set status
		insertPrepared.setInt(7,1);
		
		//execute insert
		insertPrepared.executeUpdate();
		
		//close connection
		conn.close();
	}
	
	/*
	 * Update a record in Accounts
	*/
	public void updateRecord(Account a) throws SQLException {
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();

		//Prepared statement for updating user info
		PreparedStatement updatePrepared = null;
		String updateString = "UPDATE accounts SET owner=?, ACCNUMBER=?, balance=?, isjoint=?, jointowner=?, status=? WHERE accountid=?";
		
		//set up statement for update
		updatePrepared= conn.prepareStatement(updateString);
		//set all fields for update
		//set owner
		updatePrepared.setInt(1, a.getOwner().getUserID());
		//set account number
		updatePrepared.setString(2, a.getAccountNumber());
		//set balance
		updatePrepared.setDouble(3, a.getBalance());
		//set is joint & joint owner
		if(a.isJoint()) {
			updatePrepared.setInt(4, 1);
			updatePrepared.setInt(5, a.getJointOwner().getUserID());
		}
		else {
			updatePrepared.setInt(4, 0);
			updatePrepared.setInt(5,0); //FIX THIS?
		}
		//set status
		updatePrepared.setInt(6,1);
		//set for WHERE clause
		updatePrepared.setInt(7, a.getAccountID());
		updatePrepared.executeUpdate();
		//close connection
		conn.close();
	}

	//helper method for making new Account from ResultSet 
	private Account setAccountFromRS(ResultSet rs) throws SQLException {
		Account a = new Account();
		//set all account info
		//set account id
		a.setAccountID(rs.getInt(1));
		//owner, need to do a look up
		UserServices us = new UserServices();
		User owner = us.retrieveRecordByID(rs.getInt(2));
		a.setOwner(owner);
		//account number
		a.setAccountNumber(rs.getString(3));
		//balance
		a.setBalance(rs.getDouble(4));
		//is joint
		if(rs.getInt(5) == 1) {
			a.setJoint(true);
			User jOwner = us.retrieveRecordByID(rs.getInt(6));
			a.setJointOwner(jOwner);
		}
		else {
			a.setJoint(false);
			a.setJointOwner(null);
		}
		return a;
	}
	
	//retrieve record from DB by account id 
	public Account retrieveRecordByID(int accID) throws SQLException {
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();

		//Prepared statement for select user by id
		PreparedStatement selectPrepared = null;
		String selectString = "SELECT * FROM accounts WHERE accountid=?";
		
		//set up statement for update
		selectPrepared= conn.prepareStatement(selectString);
		//fill in account id
		selectPrepared.setInt(1, accID);
		ResultSet rs = selectPrepared.executeQuery();
		
		Account a = null;
		if(rs.next())
			a = setAccountFromRS(rs);
		
		//close connection
		conn.close();
		return a;
	}
	
	//retrieve record from DB by account number 
	public Account retrieveRecordByAccountNumber(String accNum) throws SQLException {
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();

		//Prepared statement for select user by id
		PreparedStatement selectPrepared = null;
		String selectString = "SELECT * FROM accounts WHERE ACCNUMBER=?";
		
		//set up statement for update
		selectPrepared= conn.prepareStatement(selectString);
		//fill in account id
		selectPrepared.setString(1, accNum);
		ResultSet rs = selectPrepared.executeQuery();
		
		Account a = null;
		if(rs.next())
			a = setAccountFromRS(rs);
		
		//close connection
		conn.close();
		return a;
	}

	//retrieve record from DB by user
	public ArrayList<Account> retrieveAllRows(User u) throws SQLException {

		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();

		//Prepared statement for select user by id
		PreparedStatement selectPrepared = null;
		String selectString = "SELECT * FROM accounts WHERE owner=?";
		String getCurSeq = "SELECT ACCOUNT_ID.NEXTVAL FROM DUAL";
		//setup ps for current sequence
		selectPrepared = conn.prepareStatement(getCurSeq);
		
		ResultSet rs = selectPrepared.executeQuery();
		
		int tableSize = 0;
		if(rs.next())
			tableSize = rs.getInt(1);
		//set up statement for select
		selectPrepared = conn.prepareStatement(selectString);
		selectPrepared.setInt(1, u.getUserID());
		rs = selectPrepared.executeQuery();
		
		ArrayList<Account> accountList = new ArrayList<Account>(tableSize);
		while(rs.next()) {
			accountList.add(setAccountFromRS(rs));
		}
		//close connection
		conn.close();
		return accountList;
	}

	//deleting account by account id
	public void deleteRecord(int acc_id) throws SQLException {
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();
		
		//Prepared statements
		PreparedStatement deletePrepared = null;
		String deleteString = "DELETE FROM accounts WHERE accountid=?";
		//setup statement for delete
		deletePrepared = conn.prepareStatement(deleteString);
		deletePrepared.setInt(1, acc_id);
		//execute delete
		deletePrepared.executeUpdate();
		
		//close connection
		conn.close();
	}
	
	//deleting accounts by owner id
	public void deleteRecordByOwner(int owner_id) throws SQLException {
		//Connection from ConnFactory
		Connection conn = ConnFactory.getInstance().getConnection();
		
		//Prepared statements
		PreparedStatement deletePrepared = null;
		String deleteString = "DELETE FROM accounts WHERE owner=?";
		//setup statement for delete
		deletePrepared = conn.prepareStatement(deleteString);
		deletePrepared.setInt(1, owner_id);
		//execute delete
		deletePrepared.executeUpdate();
		
		//close connection
		conn.close();
	}

}
