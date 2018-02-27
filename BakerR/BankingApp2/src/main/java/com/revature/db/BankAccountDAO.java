package com.revature.db;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.BankAccount;
import com.revature.model.Customer;

public interface BankAccountDAO {
	
	/**
	 * Registers an account.
	 * @param owner	The owner of the account
	 * @return The registered bank account.
	 */
	public BankAccount createAccount(Customer owner) throws SQLException;
	
	/**
	 * Gets the specified account.
	 * @param acctId	ID number of the account to get.
	 * @return	The account corresponding to the ID.
	 */
	public BankAccount getAccount(int acctId) throws SQLException;
	
	/**
	 * Gets the updated balance of the given account.
	 * @param acct	Account to retrieve the balance of.
	 * @return	The balance of the account.
	 */
	public double getBalance(BankAccount acct) throws SQLException;
	
	/**
	 * Gets all accounts.
	 * @return	Gets all registered accounts.
	 */
	public List<BankAccount> getAllAccounts() throws SQLException;
	
	/**
	 * Get all accounts belonging to the given customer.
	 * @param owner	The owner of the accounts to retrieve.
	 * @return	All accounts belonging to owner.
	 */
	public List<BankAccount> getAllUserAccounts(Customer owner) throws SQLException;
	
	/**
	 * Activate the account.
	 * @param acct	The account to activate.
	 */
	public void activateAccount(BankAccount acct) throws SQLException;
	
	/**
	 * Deactivate the account.
	 * @param acct	The account to deactivate.
	 */
	public void deactivateAccount(BankAccount acct) throws SQLException;
	
	/**
	 * Update the info of the account in the database.
	 * @param acct	The account to update.
	 */
	public void updateAccount(BankAccount acct) throws SQLException;
	
	/**
	 * Withdraw from the account.
	 * @param acct	The account to withdraw from.
	 * @param amount	The amount to withdraw. 
	 */
	public void withdraw(BankAccount acct, double amount) throws SQLException;
	
	/**
	 * Deposit into the account.
	 * @param acct	The account to deposit into.
	 * @param amount	The amount to deposit.
	 */
	public void deposit(BankAccount acct, double amount) throws SQLException;
	
	/**
	 * Transfer money from one account to another.
	 * @param to	Account to transfer to.
	 * @param from	Account to transfer from.
	 * @param amount	The amount to transfer.
	 */
	public void transfer(BankAccount to, BankAccount from, double amount) throws SQLException;
}
