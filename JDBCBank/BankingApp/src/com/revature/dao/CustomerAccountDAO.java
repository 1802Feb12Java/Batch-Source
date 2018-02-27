package com.revature.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.accounts.CustomerAccount;

public interface CustomerAccountDAO {
		
	public ArrayList<CustomerAccount> getCustomers();
	public void addCustomer(CustomerAccount customer);
	public void deleteCustomer(CustomerAccount customer);
	public void updateCustomer(CustomerAccount customer);
	
}
