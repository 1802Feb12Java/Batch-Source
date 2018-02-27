package com.revature.bankapp.interfaces;

import java.util.List;

import com.revature.bankapp.model.BankAccount;
import com.revature.bankapp.model.Customer;

public interface CustomerInterface {
	
	
	/**
	 * register() is used for signing up for an account
	 */
	public void register(Customer customer);
	
	/**
	 * user can deactivate the account
	 */
	public void deleteAccount(BankAccount account);
	
	/**
	 * 
	 * @return list of bank accounts
	 */
	public List<BankAccount> viewAccounts();
	
	/**
	 * 
	 * @return
	 */
	public BankAccount createAccount();
}
