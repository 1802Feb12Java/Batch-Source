package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import Banking.Customer;

public abstract interface CustomerDAOInterface
{
	public abstract void addCustomer(Customer c) throws SQLException;
	public abstract void deleteCustomer(int id) throws SQLException;
	public abstract ArrayList<Customer> getAllCustomers() throws SQLException;	
	public abstract Customer getCustomer(int id) throws SQLException;
	public abstract void updateCustomer(Customer c) throws SQLException;
	ArrayList<Customer> getCustomers() throws SQLException;
	void addCustomer1(Customer customer);
	void deleteCustomer(Customer customer);
	void updateCustomer1(Customer customer);
}
