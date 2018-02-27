package com.revature.dao;

import java.util.List;

import com.revature.beans.UserBankAccount;

public interface UserBankAccountDao {
	
	// insert new record
	public void insertRecord(int customerId, int accountId);
	
	// get record
	public UserBankAccount getRecordByCustId(int id);
	
	// delete record
	public void deleteRecord(int id);
	
	public List<UserBankAccount> retrieveAllRecords();
	
	// get second person on account
	public UserBankAccount getRecordExcludingCustId(int cust_id, int account_id);

}
