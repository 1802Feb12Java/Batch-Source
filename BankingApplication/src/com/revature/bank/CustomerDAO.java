package com.revature.bank;

import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.bank.Customer;

public interface CustomerDAO {

	public void addCustomer(Customer c) throws SQLException;
	public Customer getCustomer(int id) throws SQLException;
	public void updateCustomer(Customer c) throws SQLException;
	public void deleteCustomer(int id) throws SQLException;
	public ArrayList<Customer> getAllCustomers() throws SQLException;
	
}
