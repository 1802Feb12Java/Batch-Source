package com.revature.bank;

import java.sql.SQLException;
import java.util.List;
import com.revature.bank.Account;

public interface AccountDAO {

	public void addAccount();
	public Account getAccount(int id) throws SQLException;
	public void updateAccount(Account a) throws SQLException;
	public void deleteAccount(Account a) throws SQLException;
	public List<Account> getAllAccounts() throws SQLException;
	
}