package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import Banking.Customer;

public interface CustomerDAOInterface
{
	public void addCustomer(Customer c) throws SQLException;
	public void deleteCustomer(int id) throws SQLException;
	public ArrayList<Customer> getAllCustomers() throws SQLException;	
	public Customer getCustomers(int id) throws SQLException;
	public void updateCustomer(Customer c) throws SQLException;
	ArrayList<Customer> getCustomers() throws SQLException;
	void addCustomer1(Customer customer);
	void deleteCustomer(Customer customer);
	void updateCustomer1(Customer customer);
}
