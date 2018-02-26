package com.revature.banking.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.model.Account;
import com.revature.banking.utilities.DAOUtilities;

public class AccountDAO implements InterfaceAccountDAO {

	public AccountDAO() {
	}
	public static Account getAccount(int accountNumber) {
		Account account = null;
		account = (Account) DAOUtilities.objectRead(DAOUtilities.accountsFolder + File.separator + accountNumber);
		return account;
	}
	public static boolean saveAccount(Account account) {
		return DAOUtilities.objectWrite(DAOUtilities.accountsFolder + File.separator + account.getAccountId(), account);		
	}
	public List<Account> getAllAccounts() {
		List <Account> accounts = new ArrayList<Account>();
		for (File file : new File(DAOUtilities.accountsFolder).listFiles()){
			Account account = (Account) DAOUtilities.objectReadFile(file);
			if (account!=null)
				accounts.add(account);
		}
		return accounts;
	}
	public List<Account> getAccountsByCustomerId(int customerId) {
		List <Account> accounts = new ArrayList<Account>();
		for (File file : new File(DAOUtilities.accountsFolder).listFiles()){
			Account account = (Account) DAOUtilities.objectReadFile(file);
			if (account!=null)
				if (account.getPrimaryCustomerId()==customerId || account.getSecondaryCustomerId() == customerId)
					accounts.add(account);
		}
		return accounts;
	}
	public Account getAccountByAccountId(int accountId) {
		Account account = AccountDAO.getAccount(accountId);
		return account;
	}
	public boolean updateAccount(Account account) {
		return AccountDAO.saveAccount(account);
	}
	public boolean addAccount(Account account) {
		return AccountDAO.saveAccount(account);
	}
	public boolean deleteAccount(Account account) {
		return DAOUtilities.deleteObject(DAOUtilities.accountsFolder + File.separator + account.getAccountId());
	}
	public int getNumAccounts() {
		return new File(DAOUtilities.accountsFolder).listFiles().length;
	}
	public boolean accountExists(int accountId) {
		for (File file : new File(DAOUtilities.accountsFolder).listFiles()){
			Account account = (Account) DAOUtilities.objectReadFile(file);
			if (account!=null)
				if(account.getAccountId()==accountId)
				return true;
		}
		return false;
	}
}
