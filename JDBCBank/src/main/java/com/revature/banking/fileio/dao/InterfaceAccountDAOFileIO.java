package com.revature.banking.fileio.dao;
import java.util.List;

import com.revature.banking.fileio.model.AccountFileIO;

public interface InterfaceAccountDAOFileIO {
	public List<AccountFileIO> getAllAccounts();
	public AccountFileIO getAccountByAccountId(int accountId);
	public List<AccountFileIO> getAccountsByCustomerId(int customerId);
	public boolean updateAccount(AccountFileIO account);
	public boolean addAccount(AccountFileIO account);
	public boolean deleteAccount(AccountFileIO account);
	public int getNumAccounts();
	public boolean accountExists(int accountId);
	

}
