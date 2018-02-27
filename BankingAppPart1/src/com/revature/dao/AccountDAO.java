package com.revature.dao;

import com.revature.accounts.Account;
import com.revature.users.User;
import java.sql.SQLException;
import java.util.ArrayList;


public interface AccountDAO {
	//CRUD operations
	public void addAccount(Account account)throws SQLException;
	public Account getAccount(int accountNumber)throws SQLException;
	public void updateAccount(Account account)throws SQLException;
	public void deleteAccount(Account account)throws SQLException;
	public ArrayList<Account> getPendingAccounts()throws SQLException;
	public ArrayList<Account> getCustomerAccounts(User user)throws SQLException;
}
