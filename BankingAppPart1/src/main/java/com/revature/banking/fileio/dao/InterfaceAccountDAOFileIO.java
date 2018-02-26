package com.revature.banking.dao;
import java.util.List;

import com.revature.banking.model.Account;

public interface InterfaceAccountDAO {
	public List<Account> getAllAccounts();
	public Account getAccountByAccountId(int accountId);
	public List<Account> getAccountsByCustomerId(int customerId);
	public boolean updateAccount(Account account);
	public boolean addAccount(Account account);
	public boolean deleteAccount(Account account);
	public int getNumAccounts();
	public boolean accountExists(int accountId);
	

}
