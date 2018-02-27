package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.User;

public interface AccountDao {
	
	// insert new user
	public void insertNewAccount(int is_approved, double balance) throws SQLException;
	
	// get user
	public Account getAccount(int id) throws SQLException;
	
	// retrieve users
	public List<Account> retrieveAllAccounts() throws SQLException;
	
	// update user
	public void updateAccount(Account account) throws SQLException;
	
	// delete user
	public void deleteAccount(int accountId) throws SQLException;
	
	// check pending account
	public boolean pendingAccount() throws SQLException;
	
	

}
