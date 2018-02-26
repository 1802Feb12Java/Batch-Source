package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.BankAccount;

public interface BankAccountDao {

	public void addBankAccount(BankAccount account) throws SQLException;

	public BankAccount getBankAccount(int accountId) throws SQLException;

	public void updateBankAccount(BankAccount account) throws SQLException;

	public void deleteBankAccount(int accountId) throws SQLException;

	public List<BankAccount> getAllBankAccounts() throws SQLException;
}
