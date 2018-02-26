package com.revature.dao;

import com.revature.users.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
	//CRUD operations
	public void addCustomer(Customer customer) throws SQLException;
	public Customer getCustomer(String uName, Customer customer)throws SQLException;
	public void updateCustomer(Customer customer)throws SQLException;
	public void deleteCustomer(Customer customer)throws SQLException;
	public List<Customer> getAllCustomers()throws SQLException;

}
