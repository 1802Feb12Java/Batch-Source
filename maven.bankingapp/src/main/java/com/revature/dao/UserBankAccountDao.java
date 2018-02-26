package com.revature.dao;

import java.util.List;

import com.revature.beans.UserBankAccount;

public interface UserBankAccountDao {
	
	// insert new record
	public void insertRecord(int customerId, int accountId);
	
	// get record
	public List<UserBankAccount> getRecord(int id);
	
	// delete record
	public void deleteRecord(int id);

}
