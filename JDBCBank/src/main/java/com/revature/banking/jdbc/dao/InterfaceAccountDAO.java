package com.revature.banking.jdbc.dao;
import java.util.List;

import com.revature.banking.jdbc.model.Account;
import com.revature.banking.jdbc.model.Application;

public interface InterfaceAccountDAO {
	public List<Account> getAllAccounts();
	public Account getAccountByAccountId(int accountId);
	public List<Account> getAccountsByCustomerId(int customerId);
	public boolean updateAccount(Account account);
	public boolean addAccount(Account account, Application application);
	public boolean deleteAccount(Account account);
	public int getNumAccounts();
	public boolean accountExists(int accountId);
	

}
