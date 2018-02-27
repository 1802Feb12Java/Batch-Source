package com.revature.banking.jdbc.dao;

import java.util.List;

import com.revature.banking.jdbc.model.Customer;

public interface InterfaceCustomerDAO {
	public List<Customer> getAllCustomers();
	public Customer getCustomerByCustomerId(int customerId);
	public List<Customer> getCustomersByName(String firstName, String lastName);
	public boolean updateCustomer(Customer customer);
	public boolean addCustomer(Customer customer);
	public boolean deleteCustomer(Customer customer);
	public int getNumCustomers();
}
