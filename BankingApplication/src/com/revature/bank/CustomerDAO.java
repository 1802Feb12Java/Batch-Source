package com.revature.bank;

import java.sql.SQLException;
import java.util.List;
import com.revature.bank.Customer;

public interface CustomerDAO {

	public void addCustomer();
	public Customer getCustomer(int id) throws SQLException;
	public void updateCustomer(Customer c) throws SQLException;
	public void deleteCustomer(Customer c) throws SQLException;
	public List<Customer> getAllCustomers() throws SQLException;
	
}
