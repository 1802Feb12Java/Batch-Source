package com.revature.banking.fileio.dao;

import java.util.List;

import com.revature.banking.fileio.model.CustomerFileIO;

public interface InterfaceCustomerDAOFileIO {
	public List<CustomerFileIO> getAllCustomers();
	public CustomerFileIO getCustomerByCustomerId(int customerId);
	public List<CustomerFileIO> getCustomersByName(String firstName, String lastName);
	public boolean customerExists(int customerId);
	public boolean updateCustomer(CustomerFileIO customer);
	public boolean addCustomer(CustomerFileIO customer);
	public boolean deleteCustomer(CustomerFileIO customer);
	public int getNumCustomers();
}
