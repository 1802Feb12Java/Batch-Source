package com.revature.dao;

import com.revature.accounts.Account;
import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
	//CRUD operations
	public void addAccount();
	public Account getAccount(int id)throws SQLException;
	public void updateAccount(Account account)throws SQLException;
	public void deleteAccount(Account account)throws SQLException;
	public List<Account> getAllCustomers()throws SQLException;
}
