package com.revature.bankapp.interfaces;

import java.util.List;

import com.revature.bankapp.model.BankAccount;
import com.revature.bankapp.model.BankAdmin;
import com.revature.bankapp.model.Customer;
import com.revature.bankapp.model.User;

public interface BankAdminInterface {
	
	
	/**
	 * login() allows an existing user to login to the application
	 * @param username 
	 * @param password
	 */
	public void login(String username, String password);
	
	/**
	 * allows a bankAdmin to create an account for a new bankAdmin
	 * @return a new bank admin
	 */
	public BankAdmin createBankAdmin(BankAdmin admin);
	
	/**
	 * createCustomer() allows a bank admin to create a new customer
	 * @return a new Customer
	 */
	public Customer createCustomer(Customer customer);
	
	/**
	 * updateUser() allows a user to update their username and password
	 */
	public void updateUser(Customer customer);
	
	/**
	 * user can deactivate the account
	 */
	public void deleteUser(Customer customer);
	
	
	
}
