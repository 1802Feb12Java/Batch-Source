package com.revature.banking.fileio.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.fileio.model.AccountFileIO;
import com.revature.banking.fileio.utilities.DAOUtilitiesFileIO;

public class AccountDAOFileIO implements InterfaceAccountDAOFileIO {

	public AccountDAOFileIO() {
	}
	public static AccountFileIO getAccount(int accountNumber) {
		AccountFileIO account = null;
		account = (AccountFileIO) DAOUtilitiesFileIO.objectRead(DAOUtilitiesFileIO.accountsFolder + File.separator + accountNumber);
		return account;
	}
	public static boolean saveAccount(AccountFileIO account) {
		return DAOUtilitiesFileIO.objectWrite(DAOUtilitiesFileIO.accountsFolder + File.separator + account.getAccountId(), account);		
	}
	public List<AccountFileIO> getAllAccounts() {
		List <AccountFileIO> accounts = new ArrayList<AccountFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.accountsFolder).listFiles()){
			AccountFileIO account = (AccountFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (account!=null)
				accounts.add(account);
		}
		return accounts;
	}
	public List<AccountFileIO> getAccountsByCustomerId(int customerId) {
		List <AccountFileIO> accounts = new ArrayList<AccountFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.accountsFolder).listFiles()){
			AccountFileIO account = (AccountFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (account!=null)
				if (account.getPrimaryCustomerId()==customerId || account.getSecondaryCustomerId() == customerId)
					accounts.add(account);
		}
		return accounts;
	}
	public AccountFileIO getAccountByAccountId(int accountId) {
		AccountFileIO account = AccountDAOFileIO.getAccount(accountId);
		return account;
	}
	public boolean updateAccount(AccountFileIO account) {
		return AccountDAOFileIO.saveAccount(account);
	}
	public boolean addAccount(AccountFileIO account) {
		return AccountDAOFileIO.saveAccount(account);
	}
	public boolean deleteAccount(AccountFileIO account) {
		return DAOUtilitiesFileIO.deleteObject(DAOUtilitiesFileIO.accountsFolder + File.separator + account.getAccountId());
	}
	public int getNumAccounts() {
		return new File(DAOUtilitiesFileIO.accountsFolder).listFiles().length;
	}
	public boolean accountExists(int accountId) {
		for (File file : new File(DAOUtilitiesFileIO.accountsFolder).listFiles()){
			AccountFileIO account = (AccountFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (account!=null)
				if(account.getAccountId()==accountId)
				return true;
		}
		return false;
	}
}
